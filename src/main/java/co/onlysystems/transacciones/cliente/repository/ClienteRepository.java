package co.onlysystems.transacciones.cliente.repository;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClienteRepository extends ReactiveCrudRepository<Cliente, String> {}
