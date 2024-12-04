package co.onlysystems.transacciones.cuentas.services;


import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import reactor.core.publisher.Flux;
import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaRecord;


public interface CuentaService {

   Flux<CuentaEntity> consultarTx();

   boolean actualizarTx(CuentaRecord tx);

    void crearTx(CuentaRecord tx);

    boolean eliminarTx(CuentaRecord tx);

}
