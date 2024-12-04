package co.onlysystems.transacciones.establecimiento.repository;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EstablecimientoRepository extends ReactiveCrudRepository<Establecimiento, String> {}
