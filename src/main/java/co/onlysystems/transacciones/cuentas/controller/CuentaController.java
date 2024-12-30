package co.onlysystems.transacciones.cuentas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaDto;
import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import co.onlysystems.transacciones.cuentas.services.CuentaService;
import co.onlysystems.transacciones.shared.values.UuidVale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController 
@RequestMapping("/cuenta")
@CrossOrigin(origins = "http://localhost:8080")
public class CuentaController {

    private static Logger log = LoggerFactory.getLogger(CuentaController.class);

    @Autowired(required=true)
    private CuentaService cuentaService;

    @GetMapping
    public Flux<CuentaEntity> consultarCuentasAll(){
        log.info(" implementar metodo de consulta ");
        return cuentaService.consultarTx() ;
    }


    @GetMapping("/id/{uuid}")
    public Flux<CuentaEntity> consultarCuentasEstablecimiento(UuidVale uuidVale){
        log.info(" implementar metodo de consulta estableciemitnto ");
        return cuentaService.consultarTxPorEstablecimiento(uuidVale) ;
    }

   

     @PostMapping
   public Mono<ResponseEntity<String>> crearTxAll(@RequestBody CuentaDto body){
    System.out.println("crearTxAll" + body.toString());
       return cuentaService.crear(body)
            .map(result -> {
                if (result) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creado exitosamente ::: ");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cuenta no creado ::: ");
                }
            })
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrado ::: "))
            .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear cuenta ::: ")); // Manejo de errores
    }

    @PutMapping
    public String actualizarTx(@RequestBody CuentaDto body){
        log.info(" implementar metodo de actualizar tx ");
        return "implementar metodo de actualizar tx" ;
    }

    @DeleteMapping
    public String borrarTx(@RequestBody CuentaDto body){
        log.info(" implementar metodo de borrar tx ");
        return "implementar borrar tx";
    }
}
