package co.onlysystems.transacciones.cliente.modelo;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
public class Cliente {
    @Id
    public UUID id;
    public String tipoIdentificacion;
    public String identificacion;
   public String nombres; 
   public String apellidos;
   public String celular;
   public String direccion;
   public String telefono;
   public String email; 
   public String estado;
   public LocalDateTime fechaGestion;

    public Cliente(){}      

    public Cliente(UUID id, String nombres, String apellidos,String celular, String direccion, String telefono, String email, 
                       String estado, LocalDateTime fechaGestion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.fechaGestion = fechaGestion;   
                       }

    public Cliente(UUID id, String tipoIdentificacion, String identificacion, String nombres, String apellidos,String celular, String direccion, String telefono, String email, 
                       String estado, LocalDateTime fechaGestion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.fechaGestion = fechaGestion;   
                       }    
                       
}
