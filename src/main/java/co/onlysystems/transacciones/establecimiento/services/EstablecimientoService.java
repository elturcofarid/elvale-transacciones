package co.onlysystems.transacciones.establecimiento.services;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoDto;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EstablecimientoService {

   Flux<Establecimiento> consultarTx();

    Mono<Boolean> actualizar(EstablecimientoDto establecimiento, OperacionCliente operacion);

    Mono<Boolean> valido(UuidVale uuid);

    Mono<Establecimiento> consultarId(UuidVale uuid);

    Mono<Boolean> crear(EstablecimientoDto tx);

    boolean eliminarTx(EstablecimientoDto tx);

}
