package co.onlysystems.transacciones.usuario.controller;

import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.shared.dtos.UsuarioDto;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import co.onlysystems.transacciones.usuario.modelo.Usuario;
import co.onlysystems.transacciones.usuario.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:8080")
public class UsuarioController {

    private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Flux<Usuario> consultarTxAll(){
        return usuarioService.consultarTx() ;
    }

    @GetMapping("/id/{uid}")
public Mono<ResponseEntity<? extends Object>> consultarCliente(@PathVariable String uid) {
    return usuarioService.consultarUid(uid)
        .flatMap(usuario -> {
            if (usuario != null) {
                return Mono.just(ResponseEntity.ok(usuario)); // Retorna el Usuario si se encuentra
            } else {
                return Mono.just(ResponseEntity.noContent().build()); // Retorna 204 si no se encuentra el Usuario
            }
        })
        .defaultIfEmpty(ResponseEntity.noContent().build()); // Si el Mono está vacío, retorna 204
}
    @GetMapping("/UUID/{uuid}")
    public ResponseEntity<Mono<Usuario>> consultarCliente2(UuidVale uuidVale){
        return ResponseEntity.ok(usuarioService.consultarId(uuidVale));
    }

    @PostMapping
    public Mono<ResponseEntity<String>> crear(@RequestBody UsuarioDto body) {
        return usuarioService.crear(body)
            .map(result -> {
                if (result != null) {
                    return ResponseEntity.status(HttpStatus.CREATED)
                                         .body("Usuario creado exitosamente ::: ");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                         .body("Usuario no creado ::: ");
                }
            })
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                                           .body("Usuario no encontrado ::: "))
            .onErrorResume(e -> {
                // Manejador de errores más específico
                String errorMessage = "Error al crear usuario: " + e.getMessage();
                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    
                // Puedes agregar lógica adicional para manejar diferentes tipos de excepciones
                // if (e instanceof SomeSpecificException) {
                //     status = HttpStatus.BAD_REQUEST; // Por ejemplo
                // }
    
                return Mono.just(ResponseEntity.status(status).body(errorMessage));
            });
    }

   @PutMapping
public Mono<ResponseEntity<String>> actualizarCliente(@RequestBody EdicionCliente body) {
    log.info("Implementar método de actualizar tx");
    System.out.println(body.toString());

    return usuarioService.cambiarEstadoCliente(body, OperacionCliente.ACTUALIZAR)
        .map(result -> {
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body("Fiado actualizado exitosamente :::");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fiado no actualizado exitosamente :::");
            }
        })
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fiado no encontrado :::"))
        .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar fiado :::")); // Manejo de errores
}

    @DeleteMapping
    public Mono<ResponseEntity<String>> estadoCLiente(@RequestBody EdicionCliente body){
        log.info("Implementar método de actualizar tx");
        System.out.println(body.toString());
    
        return usuarioService.cambiarEstadoCliente(new EdicionCliente(body.uuid),OperacionCliente.INACTIVAR)
            .map(result -> {
                if (result) {
                    return ResponseEntity.status(HttpStatus.OK).body("Fiado eliminado exitosamente :::");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error eliminando Fiado :::");
                }
            })
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fiado no encontrado :::"))
            .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar fiado :::")); // Manejo de errores
    }
}
