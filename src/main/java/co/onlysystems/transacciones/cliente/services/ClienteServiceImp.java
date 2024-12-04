package co.onlysystems.transacciones.cliente.services;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import co.onlysystems.transacciones.cliente.repository.ClienteRepository;
import reactor.core.publisher.Flux;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Flux<Cliente> consultarTx() {
        return clienteRepository.findAll();
    }

    @Override
    public boolean actualizarTx(ClienteRecord tx) {
        return false;
    }

    @Override
    public void crearTx(ClienteRecord tx) {
try {
    
    Cliente t = new Cliente(
            tx.getCuentaOrigen().getUuid().toString(),
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

    clienteRepository.save(t)
            .subscribe(saved -> System.out.println("Guardada: " + saved.getId()),
                    error -> System.err.println("Error guardando transacción: " + error.getMessage()));


}catch (Exception e){
    e.printStackTrace();
}

    }

    @Override
    public boolean eliminarTx(ClienteRecord tx) {
        return false;
    }
}
