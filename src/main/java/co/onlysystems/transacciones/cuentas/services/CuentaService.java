package co.onlysystems.transacciones.cuentas.services;

import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import co.onlysystems.transacciones.shared.values.Cuenta;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaDto;

public interface CuentaService {

   Flux<CuentaEntity> consultarTx();

   boolean actualizarTx(CuentaDto tx);

    Mono<Boolean> crear(CuentaDto tx);

    boolean eliminarTx(CuentaDto tx);

    Mono<Boolean> validarCuenta(UuidVale uuid, Double balance);

    Flux<CuentaEntity>  consultarTxPorEstablecimiento(UuidVale uuid);

}
