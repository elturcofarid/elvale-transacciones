package co.onlysystems.transacciones.cliente.controller;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import co.onlysystems.transacciones.cliente.modelo.EdicionCliente;
import co.onlysystems.transacciones.cliente.services.ClienteService;
import co.onlysystems.transacciones.shared.values.OperacionCliente;
import co.onlysystems.transacciones.shared.values.UuidVale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController {

    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Flux<Cliente> consultarTxAll(){
        return clienteService.consultarTx() ;
    }

    @GetMapping("/id/{uuid}")
    public ResponseEntity<Mono<Cliente>> consultarCliente(UuidVale uuidVale){
        return ResponseEntity.ok(clienteService.consultarId(uuidVale));
    }

    @GetMapping("/td/{td}/{doc}")
    public ResponseEntity<Mono<Cliente>> consultarClienteTD(@PathVariable String  td,@PathVariable String doc){

        return ResponseEntity.ok(clienteService.consultarTd(td,doc));
    }

    @PostMapping
   public Mono<ResponseEntity<String>> crearTxAll(@RequestBody ClienteRecord body){
       return clienteService.crearTx(body)
            .map(result -> {
                if (result) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente ::: ");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no creado ::: ");
                }
            })
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado ::: "))
            .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear cliente ::: ")); // Manejo de errores
            
    }

   @PutMapping
public Mono<ResponseEntity<String>> actualizarCliente(@RequestBody EdicionCliente body) {
    log.info("Implementar método de actualizar tx");
    System.out.println(body.toString());

    return clienteService.cambiarEstadoCliente(body, OperacionCliente.ACTUALIZAR)
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
    
        return clienteService.cambiarEstadoCliente(new EdicionCliente(body.uuid),OperacionCliente.INACTIVAR)
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
