package co.onlysystems.transacciones.cliente.controller;

import co.onlysystems.transacciones.cliente.modelo.Cliente;
import co.onlysystems.transacciones.cliente.modelo.ClienteRecord;
import co.onlysystems.transacciones.cliente.services.ClienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Flux<Cliente> consultarTxAll(){
        log.info(" implementar metodo de consulta ");
        return clienteService.consultarTx() ;
    }

    @PostMapping
    public String crearTxAll(@RequestBody ClienteRecord body){
       // ValidadorRequest.validarTx(body);        
        //Todo
        //Falta implementar metodos de exceptiones personalizadas
        log.info(" implementar metodo de crear tx");
        clienteService.crearTx(body);
        return "Termino revisar " ;
    }

    @PutMapping
    public String actualizarTx(@RequestBody ClienteRecord body){
        log.info(" implementar metodo de actualizar tx ");
        return "implementar metodo de actualizar tx" ;
    }

    @DeleteMapping
    public String borrarTx(@RequestBody ClienteRecord body){
        log.info(" implementar metodo de borrar tx ");
        return "implementar borrar tx";
    }
}
