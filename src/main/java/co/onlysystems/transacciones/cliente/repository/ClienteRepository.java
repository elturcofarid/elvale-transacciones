package co.onlysystems.transacciones.cliente.repository;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import reactor.core.publisher.Mono;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClienteRepository extends ReactiveCrudRepository<Cliente, UUID> {

    Mono<Cliente> findByTipoIdentificacionAndIdentificacion(String tipoIdentificacion, String identificacion);
}
