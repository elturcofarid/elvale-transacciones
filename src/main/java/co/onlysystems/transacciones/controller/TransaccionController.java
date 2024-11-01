package co.onlysystems.transacciones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransaccionController {

    private static Logger log = LoggerFactory.getLogger(TransaccionController.class);

    @GetMapping
    public String consultarTxAll(){
        log.info(" implementar metodo de consulta ");
        return "contulra todas las Tx";
    }
}
