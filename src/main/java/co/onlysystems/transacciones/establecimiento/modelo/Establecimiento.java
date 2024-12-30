package co.onlysystems.transacciones.establecimiento.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "establecimiento")
public class Establecimiento {
    @Id
    public UUID id;
    public String nit;
    public String razonSocial;
    public String direccion;
    public String telefono;
    public String descripcion;
    public String observaciones;
    public String estado;
    public String usuarioGestor;
    public LocalDateTime fechaGestion;
    public String usuarioAprobador;
    public LocalDateTime fechaAprobacion;


    public Establecimiento(String nit, String razonSocial, String direccion, String telefono, String descripcion,
                           String observaciones, String estado, String usuarioGestor, LocalDateTime fechaGestion,
                           String usuarioAprobador, LocalDateTime fechaAprobacion) {

         this.id = UUID.randomUUID();
        this.nit = nit;            
        this.razonSocial = razonSocial;
        this.direccion = direccion;                
        this.telefono = telefono;                
        this.descripcion = descripcion;                
        this.observaciones = observaciones;                
        this.estado = estado;                
        this.usuarioGestor = usuarioGestor;                
        this.fechaGestion = fechaGestion;                
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion; 
                           }
}
