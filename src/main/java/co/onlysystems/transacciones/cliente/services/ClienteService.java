package co.onlysystems.transacciones.cliente.services;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

   Flux<Cliente> consultarTx();

    boolean actualizarTx(ClienteRecord tx);

    Mono<Boolean>  valido(UuidVale uuid);

    Mono<Cliente> consultarId(UuidVale uuid);

    Mono<Cliente> consultarTd(String td, String doc);

    Mono<Boolean> crearTx(ClienteRecord tx);

    Mono<Boolean> cambiarEstadoCliente(EdicionCliente cliente, OperacionCliente operacion);

}
