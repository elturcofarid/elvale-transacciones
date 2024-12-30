package co.onlysystems.transacciones.cuentas.services;

import co.onlysystems.transacciones.cliente.services.ClienteService;
import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaDto;
import co.onlysystems.transacciones.cuentas.repository.CuentaRepository;
import co.onlysystems.transacciones.establecimiento.services.EstablecimientoService;
import co.onlysystems.transacciones.shared.values.Estados;
import co.onlysystems.transacciones.shared.values.UuidVale;
import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CuentaServiceImp implements CuentaService{

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired  
     private EstablecimientoService establecimientoService;



    @Override
    public Flux<CuentaEntity> consultarTx() {
        return cuentaRepository.findAll();
    }

    @Override
    public boolean actualizarTx(CuentaDto tx) {
        return false;
    }

    public  Flux<CuentaEntity>  consultarTxPorEstablecimiento(UuidVale uuid) {
        return cuentaRepository.findByEstablecimiento(uuid.getUuid());
    }

    @Override
    public Mono<Boolean> crear(CuentaDto tx) {

        Mono<Boolean> establecimientoValido = validarEstablecimiento(tx.getIdEstablecimiento());
        Mono<Boolean> clienteValido = validarCliente(tx.getIdCliente());
        return Mono.zip(establecimientoValido, clienteValido)
            .flatMap(tuple -> {
                boolean establecimientoExists = tuple.getT1();
                boolean clienteExists = tuple.getT2();
    
                if (!establecimientoExists || !clienteExists) {
                    return Mono.just(false); // Retornar false si el establecimiento o el cliente no existen
                }
    
                return cuentaRepository.findByEstablecimientoAndCliente(tx.getIdEstablecimiento().getUuid(), tx.getIdCliente().getUuid())
                    .flatMap(cuentaExistente -> {
                        return Mono.just(false); // La cuenta ya existe
                    })
                    .switchIfEmpty(Mono.defer(() -> {
                        Estados estado = Estados.CREADO;
                        CuentaEntity cuenta = new CuentaEntity(
                            tx.getIdEstablecimiento().getUuid(),
                            tx.getIdCliente().getUuid(),
                            tx.getMontoAprobado().getValor(),
                            tx.getTipo(),
                            tx.getObservaciones(),
                            estado.name(),
                            LocalDateTime.now(),
                            null,
                            null
                        );
    
                        return cuentaRepository.save(cuenta)
                            .doOnSuccess(saved -> {
                                System.out.println("Guardada: " + saved.id);
                                // Aquí podrías publicar un evento indicando que la cuenta ha sido creada
                                // eventPublisher.publish(new CuentaCreadaEvent(saved));
                            })
                            .then(Mono.just(true))
                            .onErrorReturn(false);
                    }));
            });
    }
    
    @Override
    public boolean eliminarTx(CuentaDto tx) {
        return false;
    }


    // Métodos para validar la existencia de establecimiento y cliente
private Mono<Boolean> validarEstablecimiento(UuidVale idEstablecimiento) {
    // Lógica para validar el establecimiento de un servicio externo (simulación)
    return establecimientoService.valido(idEstablecimiento);
}

private Mono<Boolean> validarCliente(UuidVale idCliente) {
    // Lógica para validar el cliente de un servicio externo (simulación)
    return clienteService.valido(idCliente);
}

public Mono<Boolean> validarCuenta(UuidVale uuid, Double balance) {
    return cuentaRepository.findById(uuid.getUuid())
        .map(cuentaExistente -> {
            System.out.println("balance: " + balance);
            System.out.println("cuentaExistente.getMontoAprobado(): " + cuentaExistente.montoAprobado);
            return Estados.ACTIVO.toString().equals(cuentaExistente.estado)
            && balance <= cuentaExistente.montoAprobado;
        })
        .defaultIfEmpty(false) // Si no existe el cliente, consideramos que es válido
        .onErrorReturn(false); // En caso de error, retornar false
}
 
}
