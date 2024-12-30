package co.onlysystems.transacciones.establecimiento.repository;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import reactor.core.publisher.Mono;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EstablecimientoRepository extends ReactiveCrudRepository<Establecimiento, UUID> {

    public Mono<Establecimiento> findByNit(String nit);
}
