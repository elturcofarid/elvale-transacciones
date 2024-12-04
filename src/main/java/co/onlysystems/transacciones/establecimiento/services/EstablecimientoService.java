package co.onlysystems.transacciones.establecimiento.services;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoRecord;
import reactor.core.publisher.Flux;


public interface EstablecimientoService {

   Flux<Establecimiento> consultarTx();

   boolean actualizarTx(EstablecimientoRecord tx);

    void crearTx(EstablecimientoRecord tx);

    boolean eliminarTx(EstablecimientoRecord tx);

}
