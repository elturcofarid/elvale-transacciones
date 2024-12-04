package co.onlysystems.transacciones.cliente.services;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import reactor.core.publisher.Flux;


public interface ClienteService {

   Flux<Cliente> consultarTx();

   boolean actualizarTx(ClienteRecord tx);

    void crearTx(ClienteRecord tx);

    boolean eliminarTx(ClienteRecord tx);

}
