package co.onlysystems.transacciones.cliente.services;

 
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.cliente.repository.ClienteRepository;
import co.onlysystems.transacciones.shared.values.Estados;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Flux<Cliente> consultarTx() {
        return clienteRepository.findAll();
    }

    @Override
    public Mono<Cliente> consultarId(UuidVale uuid){
        return clienteRepository.findById(uuid.getUuid());
    }

    @Override
    public Mono<Cliente> consultarTd(String td, String doc){
        return clienteRepository.findByTipoIdentificacionAndIdentificacion(td, doc);
    }

    @Override
    public boolean actualizarTx(ClienteRecord tx) {
        return false;
    }

    @Override
    public Mono<Boolean> crearTx(ClienteRecord tx) {
        return clienteRepository.findByTipoIdentificacionAndIdentificacion(tx.getTipoIdentificacion(), tx.getIdentificacion())
            .map(clienteExistente -> {
                return false;
            })
            .switchIfEmpty(Mono.defer(() -> {
                Estados estado = Estados.CREADO;
                Cliente nuevoCliente = new Cliente(
                        UUID.randomUUID(),  
                        tx.getTipoIdentificacion(),
                        tx.getIdentificacion(),
                        tx.getNombres(),
                        tx.getApellidos(),
                        tx.getCelular(),
                        tx.getDireccion(),
                        tx.getTelefono(),
                        tx.getEmail().getEmail(),
                        estado.toString(),
                        LocalDateTime.now()
                );

                return clienteRepository.save(nuevoCliente)
                        .map(saved -> {
                            System.out.println("Guardada: " + saved.id);
                            return true; // Retornar true indicando que se guardó con éxito
                        })
                        .onErrorReturn(false); // En caso de error al guardar, retornar false
            })) ; 
}

   @Override
    public Mono<Boolean> cambiarEstadoCliente(EdicionCliente cliente, OperacionCliente operacion) {

        Estados estado = procesarEstadoCliente(operacion);
        return clienteRepository.findById(cliente.uuid.getUuid())
                .flatMap(existingCliente -> {
                    existingCliente.nombres = cliente.nombres==null?existingCliente.nombres:cliente.nombres;
                    existingCliente.apellidos = cliente.apellidos==null?existingCliente.apellidos:cliente.apellidos;
                   existingCliente.direccion = cliente.direccion==null?existingCliente.direccion:cliente.direccion;
                    existingCliente.celular = cliente.celular==null?existingCliente.celular:cliente.celular;
                    existingCliente.telefono = cliente.telefono==null?existingCliente.telefono:cliente.telefono;
                   existingCliente.estado = estado==null?existingCliente.estado:estado.toString();
                    return clienteRepository.save(existingCliente)
                        .then(Mono.just(true));  // Devuelvo true si se guardó correctamente
                })
                .defaultIfEmpty(false) // Si no se encontró el Fiao, devuelve false
                .onErrorReturn(false); // En caso de error, devuelve false
    }


    


    private Estados procesarEstadoCliente(OperacionCliente operacion) {
        switch (operacion) {
            case INACTIVAR:
                return Estados.INACTIVO;
            case ACTIVAR:
                return Estados.ACTIVO;
            case ELIMINAR:
                return Estados.ELIMINADO;
            default:
                return null;
        }
    }


    public Mono<Boolean> valido(UuidVale uuid) {
        return clienteRepository.findById(uuid.getUuid())
            .map(clienteExistente -> {
                return Estados.ACTIVO.toString().equals(clienteExistente.estado);
            })
            .defaultIfEmpty(false) // Si no existe el cliente, consideramos que es válido
            .onErrorReturn(false); // En caso de error, retornar false
    }
}
