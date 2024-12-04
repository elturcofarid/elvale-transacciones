package co.onlysystems.transacciones.establecimiento.controller;

import co.onlysystems.transacciones.establecimiento.modelo.Establecimiento;
import co.onlysystems.transacciones.establecimiento.modelo.EstablecimientoRecord;
import co.onlysystems.transacciones.establecimiento.services.EstablecimientoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/establecimiento")
@CrossOrigin(origins = "http://localhost:3000")
public class EstablecimientoController {

    private static Logger log = LoggerFactory.getLogger(EstablecimientoController.class);

    @Autowired
    private EstablecimientoService establecimientoService;

    @GetMapping
    public Flux<Establecimiento> consultarTxAll(){
        log.info(" implementar metodo de consulta ");
        return establecimientoService.consultarTx() ;
    }

    @PostMapping
    public String crearTxAll(@RequestBody EstablecimientoRecord body){
       // ValidadorRequest.validarTx(body);        
        //Todo
        //Falta implementar metodos de exceptiones personalizadas
        log.info(" implementar metodo de crear tx");
        establecimientoService.crearTx(body);
        return "Termino revisar " ;
    }

    @PutMapping
    public String actualizarTx(@RequestBody EstablecimientoRecord body){
        log.info(" implementar metodo de actualizar tx ");
        return "implementar metodo de actualizar tx" ;
    }

    @DeleteMapping
    public String borrarTx(@RequestBody EstablecimientoRecord body){
        log.info(" implementar metodo de borrar tx ");
        return "implementar borrar tx";
    }
}
