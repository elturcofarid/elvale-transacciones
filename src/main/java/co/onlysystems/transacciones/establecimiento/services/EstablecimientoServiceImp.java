package co.onlysystems.transacciones.establecimiento.services;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoDto;
import co.onlysystems.transacciones.establecimiento.repository.EstablecimientoRepository;
import co.onlysystems.transacciones.shared.values.Estados;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.onlysystems.transacciones.usuario.modelo.Usuario;
import co.onlysystems.transacciones.usuario.services.UsuarioService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EstablecimientoServiceImp implements EstablecimientoService{

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private UsuarioService usuarioService;



    @Override
    public Flux<Establecimiento> consultarTx() {
        return establecimientoRepository.findAll();
    }

    @Override
    public Mono<Establecimiento> consultarId(UuidVale uuid){
        return establecimientoRepository.findById(uuid.getUuid());
    }


    @Override
    public Mono<Boolean> actualizar(EstablecimientoDto establecimiento, OperacionCliente operacion) {
        Estados estado = procesarEstadoCliente(operacion);
        return establecimientoRepository.findById(establecimiento.uuid.getUuid())
                .flatMap(_establecimiento -> {
                 _establecimiento.razonSocial = establecimiento.razonSocial == null?_establecimiento.razonSocial:establecimiento.razonSocial;
                 _establecimiento.direccion = establecimiento.direccion == null?_establecimiento.direccion:establecimiento.direccion;
                 _establecimiento.telefono = establecimiento.telefono == null?_establecimiento.telefono:establecimiento.telefono;
                 _establecimiento.descripcion = establecimiento.descripcion == null?_establecimiento.descripcion:establecimiento.descripcion;
                 _establecimiento.observaciones = establecimiento.observaciones == null?_establecimiento.observaciones:establecimiento.observaciones;
                 _establecimiento.estado = estado == null?_establecimiento.estado:estado.toString();
                    return establecimientoRepository.save(_establecimiento)
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




    @Override
    public Mono<Boolean> crear(EstablecimientoDto tx) {
        // Validar el establecimiento antes de proceder
        return validarEstablecimiento(tx.nit)
            .flatMap(establecimientoExists -> {
                if (establecimientoExists) {
                    return Mono.just(false); // Retornar false si el establecimiento ya existe
                }
    
                Estados estado = Estados.CREADO;
                Establecimiento establecimiento = new Establecimiento(
                    tx.nit,
                    tx.razonSocial,
                    tx.direccion,
                    tx.telefono,
                    tx.descripcion,
                    tx.observaciones,
                    estado.name(),
                    tx.usuarioGestor, // Ajustar para que tenga el UUID del gestor correctamente
                    LocalDateTime.now(), null, null
                );
    
                // Crear el establecimiento primero
                return establecimientoRepository.save(establecimiento)
                    .flatMap(savedEstablecimiento -> {

                        tx.usuarioAdmin.establecimiento = new UuidVale (savedEstablecimiento.id.toString());
                        tx.usuarioAdmin.rol = "ADMIN";

                        return usuarioService.crear(tx.usuarioAdmin)
                            .flatMap(usuarioCreado -> {
                                // Asegúrate de que el usuario fue creado
                                if (usuarioCreado == null) {
                                    return Mono.just(false); // Retorna false si hubo problema al crear el usuario
                                }

                             return Mono.just(true);
                            });
                    });
            })
            .defaultIfEmpty(false) // En caso de que la validación del establecimiento no encontrara problema, retornar false
            .doOnError(err -> {
                System.out.println("Error al crear establecimiento ::: " + err);
            })
            .onErrorReturn(false); // En caso de error, retornar false
    }

    

    @Override
    public boolean eliminarTx(EstablecimientoDto tx) {
        return false;
    }

    public Mono<Boolean> valido(UuidVale uuid) {
        return establecimientoRepository.findById(uuid.getUuid())
            .map(clienteExistente -> {
                return Estados.ACTIVO.toString().equals(clienteExistente.estado);
            })
            .defaultIfEmpty(false) // Si no existe el cliente, consideramos que es válido
            .onErrorReturn(false); // En caso de error, retornar false
    }


    private Mono<Boolean> validarEstablecimiento(String nit) {
        // Lógica para validar el establecimiento de un servicio externo (simulación)
        System.out.println("Validar establecimiento :::: " + nit);
        return establecimientoRepository.findByNit(nit)
            .map(establecimientoExistente -> {
                System.out.println("Establecimiento existente ::: ");
                return true;
            })
            .defaultIfEmpty(false) // Si no existe el establecimiento, consideramos que es válido
            .onErrorReturn(false); // En caso de error, retornar false
    }



}

