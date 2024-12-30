package co.onlysystems.transacciones.cuentas.repository;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaRepository extends ReactiveCrudRepository<CuentaEntity, UUID> {

     Mono<CuentaEntity> findByEstablecimientoAndCliente(UUID establecimiento, UUID cliente);

     Flux<CuentaEntity> findByEstablecimiento(UUID establecimiento);
     
}

