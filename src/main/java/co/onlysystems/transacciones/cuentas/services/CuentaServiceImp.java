package co.onlysystems.transacciones.cuentas.services;


import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaRecord;
import co.onlysystems.transacciones.cuentas.repository.CuentaRepository;
import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CuentaServiceImp implements CuentaService{

    @Autowired
    private CuentaRepository cuentaRepository;



    @Override
    public Flux<CuentaEntity> consultarTx() {
        return cuentaRepository.findAll();
    }

    @Override
    public boolean actualizarTx(CuentaRecord tx) {
        return false;
    }

    @Override
    public void crearTx(CuentaRecord tx) {
try {
    CuentaEntity t = new CuentaEntity(  
            "122222",
            tx.getMontoAprobado().getValor(),
            tx.getTipo(),
            tx.getObservaciones(),
            tx.getEstado(),
            tx.getFechaGestion().getFecha(),
            tx.getUsuarioAprobador(),
            tx.getFechaAprobacion()==null?null:tx.getFechaAprobacion().getFecha()        
            );            

    cuentaRepository.save(t)            
            .subscribe(saved -> System.out.println("Guardada: " + saved.getId()),            
                    error -> System.err.println("Error guardando transacción: " + error.getMessage()));
                    

                    
}catch (Exception e){
    e.printStackTrace();
}

    }

    @Override
    public boolean eliminarTx(CuentaRecord tx) {
        return false;
    }
}
