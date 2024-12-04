package co.onlysystems.transacciones.fiao.modelo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import reactor.core.publisher.Flux;

@Document(collection = "fiao")
public class Fiao {

    @Id
    public UUID id;
    public UUID cuentaId;
    public double valorFio; 
    public String tipo;
    public String observaciones;
    public String estado;
    public UUID usuarioGestor;
    public LocalDateTime fechaGestion;
    public UUID usuarioAprobador;
    public LocalDateTime fechaAprobacion;

    public Fiao(){}

    public Fiao(UUID cuentaId, double valorFio, String tipo,  
                  String observaciones, String estado, UUID usuarioGestor, LocalDateTime fechaGestion, 
                  UUID usuarioAprobador, LocalDateTime fechaAprobacion) {



        this.id = UUID.randomUUID();
        this.cuentaId = cuentaId;
        this.valorFio = valorFio;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion;
    }


    public Fiao(UUID cuentaId, double valorFio, String tipo,  
    String observaciones, String estado, UUID usuarioGestor, LocalDateTime fechaGestion) {
        this.id = UUID.randomUUID();
        this.cuentaId = cuentaId;
        this.valorFio = valorFio;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
    }

    /*
    public UUID getId() { return id; }    
    public String getClienteId() { return cuentaId; }    
    public double getMontoAprobado() { return valorFio; }    
    public String getTipo() { return tipo; }    
    public String getObservaciones() { return observaciones; }    
    public String getEstado() { return estado; }    
    public String getUsuarioGestor() { return usuarioGestor; }    
    public LocalDateTime getFechaGestion() { return fechaGestion; }    
    public String getUsuarioAprobador() { return usuarioAprobador; }    
    public LocalDateTime getFechaAprobacion() { return fechaAprobacion; }    

    public void setId(UUID id) { this.id = id; }    
    public void setClienteId(String cuentaId) { this.cuentaId = cuentaId; }    
    public void setMontoAprobado(double valorFio) { this.valorFio = valorFio; }    
    public void setTipo(String tipo) { this.tipo = tipo; }    
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }    
    public void setEstado(String estado) { this.estado = estado; }    
    public void setUsuarioGestor(String usuarioGestor) { this.usuarioGestor = usuarioGestor; }    
    public void setFechaGestion(LocalDateTime fechaGestion) { this.fechaGestion = fechaGestion; }    
    public void setUsuarioAprobador(String usuarioAprobador) { this.usuarioAprobador = usuarioAprobador; }                
    public void setFechaAprobacion(LocalDateTime fechaAprobacion) { this.fechaAprobacion = fechaAprobacion; }   
    */
   
}
