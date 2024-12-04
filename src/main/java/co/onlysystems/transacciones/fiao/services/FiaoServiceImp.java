package co.onlysystems.transacciones.fiao.services;

import co.onlysystems.transacciones.fiao.modelo.dto.AprobacionFiao;
import co.onlysystems.transacciones.fiao.modelo.dto.FiaoRecord;
import co.onlysystems.transacciones.fiao.modelo.entity.Fiao;
import co.onlysystems.transacciones.fiao.repository.FiaoRepository;
import co.onlysystems.transacciones.shared.values.EstadosFio;
import co.onlysystems.transacciones.shared.values.OperacionFio;
import co.onlysystems.transacciones.shared.values.UuidVale;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FiaoServiceImp implements FiaoService{

    @Autowired
    private FiaoRepository fiaoRepository;

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
    public void crearFio(FiaoRecord tx) {
try {

    EstadosFio estado = EstadosFio.CREADO;
   Fiao t = new Fiao(
            tx.cuenta.getUuid(),
            tx.getValorFio().getValor(),            
            tx.getTipo(),            
            tx.getObservaciones(),            
            estado.toString(),
            tx.usuarioGestor.getUuid(),           
            LocalDateTime.now()        
            
   );

    fiaoRepository.save(t)            
            .subscribe(saved -> System.out.println("Fio guardado exitosamente: " + saved.cuentaId),            
                    error -> System.err.println("Error guardando transacción: " + error.getMessage()));
                    
    //
                    
}catch (Exception e){
    e.printStackTrace();
}

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
  
}
