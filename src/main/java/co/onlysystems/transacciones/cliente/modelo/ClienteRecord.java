
package co.onlysystems.transacciones.cliente.modelo;

import co.onlysystems.transacciones.cliente.modelo.values.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ClienteRecord{
    public String tipoIdentificacion;
    public String identificacion;
    public String nombres; 
    public String apellidos;
    public String direccion;
    public String celular;
    public String telefono;
    public Email email; 
    public String estado;
    
    /*
    public ClienteRecord(String nombres, String apellidos, String direccion, String celular,String telefono, Email email, String estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }*/

    public ClienteRecord(String tipoIdentificacion, String identificacion, String nombres, String apellidos, String direccion, String celular,String telefono, Email email, String estado) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }       


}