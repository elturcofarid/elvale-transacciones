package co.onlysystems.transacciones.usuario.services;

 
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.shared.dtos.UsuarioDto;
import co.onlysystems.transacciones.shared.values.Estados;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import co.onlysystems.transacciones.usuario.modelo.Usuario;
import co.onlysystems.transacciones.usuario.repository.UsuarioRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Flux<Usuario> consultarTx() {
        return usuarioRepository.findAll();
    }

    @Override
    public Mono<Usuario> consultarId(UuidVale uuid){
        return usuarioRepository.findById(uuid.getUuid());
    }

    @Override
    public Mono<Usuario> consultarUid(String uid){
        return usuarioRepository.findByUid(uid);
    }


    @Override
    public boolean actualizarTx(UsuarioDto tx) {
        return false;
    }

    @Override
    public Mono<Boolean> crearTx(UsuarioDto tx) {
        System.out.println("crearTx" + tx.toString());
        return usuarioRepository.findByTipoIdentificacionAndIdentificacion(tx.getTipoIdentificacion(), tx.getIdentificacion())
            .map(clienteExistente -> {
                System.out.println("usuario Existente: " + clienteExistente.id);
                return false;
            })
            .switchIfEmpty(Mono.defer(() -> {
                System.out.println("usuario No Existente: " + tx.toString());
                Estados estado = Estados.ACTIVO;
                Usuario nuevoCliente = new Usuario(
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
                        LocalDateTime.now(),
                        tx.getEstablecimiento().getUuid(),
                        tx.getRol(),
                        tx.getUid()
                );

                return usuarioRepository.save(nuevoCliente)
                        .map(saved -> {
                            System.out.println("usuario Guardada: " + saved.id);
                            return true; // Retornar true indicando que se guardó con éxito
                        })
                        .onErrorReturn(false); // En caso de error al guardar, retornar false
            })) ; 
}

   @Override
    public Mono<Boolean> cambiarEstadoCliente(EdicionCliente cliente, OperacionCliente operacion) {

        return Mono.just(true);
        /***
        Estados estado = procesarEstadoCliente(operacion);
        return usuarioRepository.findById(cliente.uuid.getUuid())
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
                 */
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
        return usuarioRepository.findById(uuid.getUuid())
            .map(clienteExistente -> {
                return Estados.ACTIVO.toString().equals(clienteExistente.estado);
            })
            .defaultIfEmpty(false) // Si no existe el cliente, consideramos que es válido
            .onErrorReturn(false); // En caso de error, retornar false
    }



    @Override
    public Mono<Usuario> crear(UsuarioDto tx) {
        return usuarioRepository.findByTipoIdentificacionAndIdentificacion(tx.getTipoIdentificacion(), tx.getIdentificacion())
        .flatMap(clienteExistente -> {
            // Si el cliente ya existe, retornamos el cliente existente
            System.out.println("El usuario ya existe: " + clienteExistente.id);
            return Mono.just(clienteExistente); // Retorna el usuario existente
        })
        .switchIfEmpty(Mono.defer(() -> {
            Estados estado = Estados.CREADO;

            Usuario nuevoCliente = new Usuario(
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
                    LocalDateTime.now(),
                    tx.getEstablecimiento().getUuid(),
                    tx.getRol(),
                    tx.getUid()
            );

            return usuarioRepository.save(nuevoCliente)
                    .doOnSuccess(saved -> {
                        System.out.println("Usuario guardado: " + saved.id);
                    })
                    ;
        }));
}
}