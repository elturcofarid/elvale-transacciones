package co.onlysystems.transacciones.services;

import co.onlysystems.transacciones.modelo.TransaccionDTO;
import co.onlysystems.transacciones.modelo.TransaccionRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionServiceImp implements TransaccionService{


    @Override
    public List<TransaccionDTO> consultarTx() {
        return null;
    }

    @Override
    public boolean actualizarTx(TransaccionRecord tx) {
        return false;
    }

    @Override
    public boolean crearTx(TransaccionRecord tx) {
        return false;
    }

    @Override
    public boolean eliminarTx(TransaccionRecord tx) {
        return false;
    }
}
