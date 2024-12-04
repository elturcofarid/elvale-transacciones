package co.onlysystems.transacciones.cuentas.modelo.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "cuenta")
public class CuentaEntity {
    @Id
    private String id;
    private String clienteId;
    private double montoAprobado;
    private String tipo;
    private String observaciones;
    private String estado;
    private String usuarioGestor;
    private LocalDateTime fechaGestion;
    private String usuarioAprobador;
    private LocalDateTime fechaAprobacion;

 
    public CuentaEntity(String clienteId, double montoAprobado, String tipo,  
                  String observaciones, String usuarioGestor, LocalDateTime fechaGestion, 
                  String usuarioAprobador, LocalDateTime fechaAprobacion) {
        this.id = null;
        this.clienteId = clienteId;
        this.montoAprobado = montoAprobado;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getId() { return id; }    
    public String getClienteId() { return clienteId; }    
    public double getMontoAprobado() { return montoAprobado; }    
    public String getTipo() { return tipo; }    
    public String getObservaciones() { return observaciones; }    
    public String getEstado() { return estado; }    
    public String getUsuarioGestor() { return usuarioGestor; }    
    public LocalDateTime getFechaGestion() { return fechaGestion; }    
    public String getUsuarioAprobador() { return usuarioAprobador; }    
    public LocalDateTime getFechaAprobacion() { return fechaAprobacion; }    

    public void setId(String id) { this.id = id; }    
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }    
    public void setMontoAprobado(double montoAprobado) { this.montoAprobado = montoAprobado; }    
    public void setTipo(String tipo) { this.tipo = tipo; }    
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }    
    public void setEstado(String estado) { this.estado = estado; }    
    public void setUsuarioGestor(String usuarioGestor) { this.usuarioGestor = usuarioGestor; }    
    public void setFechaGestion(LocalDateTime fechaGestion) { this.fechaGestion = fechaGestion; }    
    public void setUsuarioAprobador(String usuarioAprobador) { this.usuarioAprobador = usuarioAprobador; }                
    public void setFechaAprobacion(LocalDateTime fechaAprobacion) { this.fechaAprobacion = fechaAprobacion; }   
   
}
