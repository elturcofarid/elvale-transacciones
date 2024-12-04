package co.onlysystems.transacciones.fiao.services;

import co.onlysystems.transacciones.fiao.modelo.dto.AprobacionFiao;
import co.onlysystems.transacciones.fiao.modelo.dto.FiaoRecord;
import co.onlysystems.transacciones.fiao.modelo.entity.Fiao;
import co.onlysystems.transacciones.shared.values.OperacionFio;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FiaoService {

   Flux<Fiao> consultarFios();

   Mono<Fiao> consultarFiao(UuidVale uuid);

   Mono<Boolean> cambiarEstadoFiao(AprobacionFiao fiao, OperacionFio operacion);

    void crearFio(FiaoRecord tx);

    Flux<Fiao> consultarFiaoCuenta(UuidVale uuid) ;

}
