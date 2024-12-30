package co.onlysystems.transacciones.establecimiento.controller;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoDto;
import co.onlysystems.transacciones.establecimiento.services.EstablecimientoService;
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
@RequestMapping("/establecimiento")
@CrossOrigin(origins = "http://localhost:8080")
public class EstablecimientoController {

    private static Logger log = LoggerFactory.getLogger(EstablecimientoController.class);

    @Autowired
    private EstablecimientoService establecimientoService;

    @GetMapping
    public Flux<Establecimiento> consultarTxAll(){
        log.info(" implementar metodo de consulta ");
        return establecimientoService.consultarTx() ;
    }

        @GetMapping("/id/{uuid}")
    public ResponseEntity<Mono<Establecimiento>> consultarCliente(UuidVale uuidVale){
        return ResponseEntity.ok(establecimientoService.consultarId(uuidVale));
    }


      @PostMapping
   public Mono<ResponseEntity<String>> crear(@RequestBody EstablecimientoDto body){
    System.out.println(body.toString());
       return establecimientoService.crear(body)
            .map(result -> {
                if (result) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Establecimiento creado exitosamente ::: ");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Establecimiento no creado ::: ");
                }
            })
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Establecimiento no encontrado ::: "))
            .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Establecimiento ::: ")); // Manejo de errores
        }


    @PutMapping
public Mono<ResponseEntity<String>> actualizar(@RequestBody EstablecimientoDto body) {
    log.info("Implementar método de actualizar tx");
    System.out.println(body.toString());

    return establecimientoService.actualizar(body, OperacionCliente.ACTUALIZAR)
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
        public Mono<ResponseEntity<String>> borrar(@RequestBody EstablecimientoDto body) {
            log.info("Implementar método de actualizar tx");
            System.out.println(body.toString());
        
            return establecimientoService.actualizar(body, OperacionCliente.ELIMINAR)
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
}
