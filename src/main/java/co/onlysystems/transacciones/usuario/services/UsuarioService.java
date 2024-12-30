package co.onlysystems.transacciones.usuario.services;

import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.shared.dtos.UsuarioDto;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import co.onlysystems.transacciones.usuario.modelo.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

   Flux<Usuario> consultarTx();

    boolean actualizarTx(UsuarioDto tx);

    Mono<Boolean>  valido(UuidVale uuid);

    Mono<Usuario> consultarId(UuidVale uuid);

    Mono<Boolean> crearTx(UsuarioDto tx);

    Mono<Usuario> crear(UsuarioDto tx);

    Mono<Usuario> consultarUid(String uid);

    Mono<Boolean> cambiarEstadoCliente(EdicionCliente cliente, OperacionCliente operacion);

}
