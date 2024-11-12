package co.onlysystems.transacciones.services;

import co.onlysystems.transacciones.modelo.TransaccionDTO;
import co.onlysystems.transacciones.modelo.TransaccionRecord;

import java.util.List;

public interface TransaccionService {

   List<TransaccionDTO> consultarTx();

   boolean actualizarTx(TransaccionRecord tx);

    boolean crearTx(TransaccionRecord tx);

    boolean eliminarTx(TransaccionRecord tx);

}
