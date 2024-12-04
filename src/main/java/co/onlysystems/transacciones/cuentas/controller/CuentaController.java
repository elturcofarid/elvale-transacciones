package co.onlysystems.transacciones.cuentas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.onlysystems.transacciones.cuentas.modelo.dto.CuentaRecord;
import co.onlysystems.transacciones.cuentas.modelo.entity.CuentaEntity;
import co.onlysystems.transacciones.cuentas.services.CuentaService;
import reactor.core.publisher.Flux;

@RestController 
@RequestMapping("/cuenta")
@CrossOrigin(origins = "http://localhost:3000")
public class CuentaController {

    private static Logger log = LoggerFactory.getLogger(CuentaController.class);

    @Autowired(required=true)
    private CuentaService cuentaService;

    @GetMapping
    public Flux<CuentaEntity> consultarCuentasAll(){
        log.info(" implementar metodo de consulta ");
        return cuentaService.consultarTx() ;
    }

    @PostMapping
    public String crearTxAll(@RequestBody CuentaRecord body) {
       // ValidadorRequest.validarTx(body);        
        //Todo
        //Falta implementar metodos de exceptiones personalizadas
        log.info(" implementar metodo de crear tx");
        cuentaService.crearTx(body);
        return "Termino revisar " ;
    }

    @PutMapping
    public String actualizarTx(@RequestBody CuentaRecord body){
        log.info(" implementar metodo de actualizar tx ");
        return "implementar metodo de actualizar tx" ;
    }

    @DeleteMapping
    public String borrarTx(@RequestBody CuentaRecord body){
        log.info(" implementar metodo de borrar tx ");
        return "implementar borrar tx";
    }
}
