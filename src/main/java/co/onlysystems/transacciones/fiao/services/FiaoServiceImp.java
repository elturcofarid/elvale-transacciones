package co.onlysystems.transacciones.fiao.services;

import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import co.onlysystems.transacciones.cuentas.services.CuentaService;
import co.onlysystems.transacciones.fiao.modelo.dto.AprobacionFiao;
import co.onlysystems.transacciones.fiao.modelo.dto.FiaoRecord;
import co.onlysystems.transacciones.fiao.modelo.entity.Fiao;
import co.onlysystems.transacciones.fiao.repository.FiaoRepository;
import co.onlysystems.transacciones.shared.values.Estados;
import co.onlysystems.transacciones.shared.values.EstadosFio;
import co.onlysystems.transacciones.shared.values.OperacionFio;
import co.onlysystems.transacciones.shared.values.UuidVale;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FiaoServiceImp implements FiaoService{

    @Autowired
    private FiaoRepository fiaoRepository;

    @Autowired
    private CuentaService cuentaService;

    

    @Override
    public Flux<Fiao> consultarFios() {
        return fiaoRepository.findAll();
    }

    @Override
    public Mono<Boolean> cambiarEstadoFiao(AprobacionFiao fiao, OperacionFio operacion) {

        EstadosFio estado = procesarEstadoFiao(operacion);
        return fiaoRepository.findById(fiao.getUuid())
                .flatMap(existingFiao -> {
                    // Actualizar los campos necesarios
                    existingFiao.usuarioAprobador = fiao.usuarioAprobador.getUuid();
                    existingFiao.fechaAprobacion  = fiao.fechaAprobacion.getFecha();
                    existingFiao.estado = estado.toString();
                    // Guardar los cambios
                    return fiaoRepository.save(existingFiao)
                        .then(Mono.just(true));  // Devuelvo true si se guardó correctamente
                })
                .defaultIfEmpty(false) // Si no se encontró el Fiao, devuelve false
                .onErrorReturn(false); // En caso de error, devuelve false
    }



    @Override
   public Mono<ResponseEntity<String>> crearFio(FiaoRecord tx) {
    Mono<Boolean> cuentaValida = validarCuenta(tx.cuenta);
    Mono<Boolean> gestorValido = validarCuenta(tx.cuenta); // Asumido que este es el chequeo correcto del gestor

    return Mono.zip(cuentaValida, gestorValido)
        .flatMap(tuple -> {
            boolean cuentaExists = tuple.getT1();
            boolean gestorExists = tuple.getT2(); // Usa la validación del gestor

            if (!cuentaExists) {
                return Mono.just(ResponseEntity.badRequest().body("La cuenta no es válida o el saldo es insuficiente."));
            }
            if (!gestorExists) {
                return Mono.just(ResponseEntity.badRequest().body("Gestor no válido o no tiene permisos para esta gestión."));
            }

            Estados estado = Estados.CREADO;
            Fiao t = new Fiao(
                tx.cuenta.getUuid(),
                tx.getValorFio().getValor(),
                tx.getTipo(),
                tx.getObservaciones(),
                estado.toString(),
                tx.usuarioGestor.getUuid(),
                LocalDateTime.now()
            );

            return fiaoRepository.save(t)
                .doOnSuccess(saved -> {
                    System.out.println("Guardada: " + saved.id);
                    // Evento opcional para indicar que el registro se ha creado
                    // eventPublisher.publish(new FiaoCreadoEvent(saved));
                })
                .then(Mono.just(ResponseEntity.ok("Fiao creado exitosamente."))) // Mensaje de éxito
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Fiao.")); // Manejo de errores
        })
        .defaultIfEmpty(ResponseEntity.badRequest().body("Error en la validación, ninguna de las cuentas encontradas.")); // Respuesta si no hay validaciones
}

    private EstadosFio procesarEstadoFiao(OperacionFio operacion) {
        switch (operacion) {
            case RECHAZAR:
                return EstadosFio.RECHAZADO;
            case ACTUALIZAR:
                return EstadosFio.APROBADO;
            case PAGAR:
                return EstadosFio.PAGADO;
            default:
                return EstadosFio.CREADO;
        }
    }


    @Override
    public Mono<Fiao> consultarFiao(UuidVale uuid) {
        return fiaoRepository.findById(uuid.getUuid());
    }

    @Override
    public Flux<Fiao> consultarFiaoCuenta(UuidVale uuid) {
        return fiaoRepository.findByCuentaId(uuid.getUuid());
    }

    @Override
    public Mono<Double> consultarBalanceCuenta(UuidVale uuid) {
        return fiaoRepository.findByCuentaId(uuid.getUuid())
            //.filter(fiao -> EstadosFio.CREADO.name().equals(fiao.estado) || EstadosFio.APROBADO.name().equals(fiao.estado)) // Verifica el estado
            .map(fiao -> fiao.valorFio) // Obtiene solo el valorFio
            .reduce(0.0, Double::sum) // Suma todos los valoresFio
            .defaultIfEmpty(0.0); // Retorna 0.0 si no hay registros
    }

    private Mono<Boolean> validarCuenta(UuidVale idCuenta) {
        return consultarBalanceCuenta(idCuenta) // devuelve Mono<Double>
        .flatMap(balance -> cuentaService.validarCuenta(idCuenta, balance));
}
  
}
