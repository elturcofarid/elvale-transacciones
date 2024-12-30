package co.onlysystems.transacciones.usuario.repository;

import co.onlysystems.transacciones.usuario.modelo.Usuario;
import reactor.core.publisher.Mono;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, UUID> {

    Mono<Usuario> findByTipoIdentificacionAndIdentificacion(String tipoIdentificacion, String identificacion);

    Mono<Usuario> findByUid(String uid);

}
