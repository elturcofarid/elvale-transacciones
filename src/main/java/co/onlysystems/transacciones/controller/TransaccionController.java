package co.onlysystems.transacciones.controller;

import co.onlysystems.transacciones.modelo.TransaccionDTO;
import co.onlysystems.transacciones.modelo.TransaccionRecord;
import co.onlysystems.transacciones.services.TransaccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransaccionController {

    private static Logger log = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public String consultarTxAll(){
        log.info(" implementar metodo de consulta ");
        return "contulra todas las Tx" + transaccionService.consultarTx();
    }

    @PostMapping
    public String crearTxAll(@RequestBody TransaccionDTO body){
        log.info(" implementar metodo de crear tx");
        return "implementar metodo de crear tx" + transaccionService.crearTx(new TransaccionRecord());
    }

    @PutMapping
    public String actualizarTx(@RequestBody TransaccionDTO body){
        log.info(" implementar metodo de actualizar tx ");
        return "implementar metodo de actualizar tx" + transaccionService.actualizarTx(new TransaccionRecord());
    }

    @DeleteMapping
    public String borrarTx(@RequestBody TransaccionDTO body){
        log.info(" implementar metodo de borrar tx ");
        return "implementar borrar tx" + transaccionService.eliminarTx(new TransaccionRecord());
    }
}
