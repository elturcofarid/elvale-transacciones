package co.onlysystems.transacciones.fiao.repository;

import co.onlysystems.transacciones.shared.values.EstadosFio;
import co.onlysystems.transacciones.shared.values.UuidVale;

import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.onlysystems.transacciones.fiao.modelo.entity.Fiao;

public interface FiaoRepository extends ReactiveCrudRepository<Fiao, UUID> {

    Fiao findByIdAndEstadoNotIn(UUID id, List<EstadosFio> estados);

    Flux<Fiao> findByCuentaId(UUID uuid);

    Flux<Fiao> findByCuentaId(UuidVale uuid);
}
