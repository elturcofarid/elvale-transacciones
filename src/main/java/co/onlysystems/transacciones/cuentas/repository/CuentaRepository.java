package co.onlysystems.transacciones.cuentas.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;

public interface CuentaRepository extends ReactiveCrudRepository<CuentaEntity, String> {}
