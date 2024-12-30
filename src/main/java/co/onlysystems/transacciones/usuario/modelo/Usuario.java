package co.onlysystems.transacciones.usuario.modelo;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario {
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
   public UUID establecimiento;
   public String rol;
   public String uid;

    public Usuario(){}      

    public Usuario(UUID id, String nombres, String apellidos,String celular, String direccion, String telefono, String email, 
                       String estado, LocalDateTime fechaGestion, UUID establecimiento, String rol, String uid)
     {
        this.id = id;
        this.uid = uid;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular = celular;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.fechaGestion = fechaGestion;   
        this.establecimiento = establecimiento;
        this.rol = rol;
        }

    public Usuario(UUID id, String tipoIdentificacion, String identificacion, String nombres, String apellidos,String celular, String direccion, String telefono, String email, 
                       String estado, LocalDateTime fechaGestion, UUID establecimiento, String rol, String uid) {
        this.id = id;
        this.uid = uid;
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
        this.establecimiento = establecimiento;
        this.rol = rol;
                       }    
                       
}
