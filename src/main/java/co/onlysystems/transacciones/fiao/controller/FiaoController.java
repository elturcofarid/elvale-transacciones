package co.onlysystems.transacciones.fiao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.onlysystems.transacciones.fiao.services.FiaoService;
import co.onlysystems.transacciones.fiao.modelo.dto.AprobacionFiao;
import co.onlysystems.transacciones.fiao.modelo.dto.FiaoRecord;
import co.onlysystems.transacciones.fiao.modelo.entity.Fiao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.onlysystems.transacciones.shared.values.OperacionFio;
import co.onlysystems.transacciones.shared.values.UuidVale;


@RestController 
@RequestMapping("/fiao")
@CrossOrigin(origins = "http://localhost:3000")
public class FiaoController {

    private static Logger log = LoggerFactory.getLogger(FiaoController.class);

    @Autowired
    private FiaoService fiaoService;

    @GetMapping
    public ResponseEntity<Flux<Fiao>> consultarFiaos(){
        log.info(" implementar metodo de consulta ");
        return ResponseEntity.ok(fiaoService.consultarFios());
    }

    @GetMapping("/id/{uuid}")
    public ResponseEntity<Mono<Fiao>> consultarFiao(UuidVale uuidVale){
        log.info(" implementar metodo de consulta ");
        return ResponseEntity.ok(fiaoService.consultarFiao(uuidVale));
    }


    @GetMapping("/cuentaid/{uuid}")
    public ResponseEntity<Flux<Fiao>> consultarFiaoCuenta(UuidVale uuidVale){
        log.info(" implementar metodo de consulta ");
        return ResponseEntity.ok(fiaoService.consultarFiaoCuenta(uuidVale));
    }

    @PostMapping
    public ResponseEntity<String> crearFiao(@RequestBody FiaoRecord body) {
        log.info(" implementar metodo de crear tx");
        try {
            fiaoService.crearFio(body);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(" Fiado creado exitosamente ::: ");
    }

   @PutMapping
public Mono<ResponseEntity<String>> cambiarEstadoFiao(@RequestBody AprobacionFiao body) {
    log.info("Implementar método de actualizar tx");
    System.out.println(body.toString());

    return fiaoService.cambiarEstadoFiao(body, OperacionFio.ACTUALIZAR)
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
    public Mono<ResponseEntity<String>> borrarTx(@RequestBody AprobacionFiao body){
        log.info("Implementar método de actualizar tx");
        System.out.println(body.toString());
    
        return fiaoService.cambiarEstadoFiao(body,OperacionFio.RECHAZAR)
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
