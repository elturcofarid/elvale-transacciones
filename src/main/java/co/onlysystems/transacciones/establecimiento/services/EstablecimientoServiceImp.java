package co.onlysystems.transacciones.establecimiento.services;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoRecord;
import co.onlysystems.transacciones.establecimiento.repository.EstablecimientoRepository;
import reactor.core.publisher.Flux;

@Service
public class EstablecimientoServiceImp implements EstablecimientoService{

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Override
    public Flux<Establecimiento> consultarTx() {
        return establecimientoRepository.findAll();
    }

    @Override
    public boolean actualizarTx(EstablecimientoRecord tx) {
        return false;
    }

    @Override
    public void crearTx(EstablecimientoRecord tx) {
try {
    
    Establecimiento t = new Establecimiento(
            tx.getCuentaOrigen().getUuid(),
            tx.getMonto().getValor(),
            tx.getTipo(),
            tx.getDescripcion(),
            tx.getReferencia(),
            tx.getObservaciones(),
            tx.getEstado(),
            tx.getUsuarioGestor(),
            tx.getFechaGestion().getFecha(),
            tx.getUsuarioAprobador(),
            tx.getFechaAprobacion()==null?null:tx.getFechaAprobacion().getFecha()     
            );

            establecimientoRepository.save(t)
            .subscribe(saved -> System.out.println("Guardada: " + saved.getId()),
                    error -> System.err.println("Error guardando transacción: " + error.getMessage()));


}catch (Exception e){
    e.printStackTrace();
}

    }

    @Override
    public boolean eliminarTx(EstablecimientoRecord tx) {
        return false;
    }
}
