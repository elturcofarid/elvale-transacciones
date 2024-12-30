
package co.onlysystems.transacciones.shared.dtos;

import co.onlysystems.transacciones.cliente.modelo.values.Email;
import co.onlysystems.transacciones.shared.values.UuidVale;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UsuarioDto{
    public String uid;
    public String tipoIdentificacion;
    public String identificacion;
    public String nombres; 
    public String apellidos;
    public String direccion;
    public String celular;
    public String telefono;
    public Email email; 
    public String estado;
    public UuidVale establecimiento;
    public String rol;
    
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

    public UsuarioDto(String id, String tipoIdentificacion, String identificacion, String nombres, String apellidos, String direccion, String celular,String telefono, Email email, 
    String estado, UuidVale establecimiento, String rol) {
        this.uid = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.establecimiento = establecimiento;
        this.rol = rol;
    }       


}