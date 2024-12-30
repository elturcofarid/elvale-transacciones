package co.onlysystems.transacciones.usuario.modelo;

import co.onlysystems.transacciones.cliente.modelo.values.Email;
import co.onlysystems.transacciones.shared.values.UuidVale;

public class EdicionCliente {

    public UuidVale uuid;
    public String nombres; 
    public String apellidos;
    public String direccion;
    public String celular;
    public String telefono;
    public Email email; 


    public EdicionCliente(){}
    
    public EdicionCliente(UuidVale uuidVale){
        this.uuid = uuidVale;
    }
}
