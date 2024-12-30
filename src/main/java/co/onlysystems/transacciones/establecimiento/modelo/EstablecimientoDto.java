
package co.onlysystems.transacciones.establecimiento.modelo;

import co.onlysystems.transacciones.establecimiento.modelo.value.RazonSocial;
import co.onlysystems.transacciones.shared.values.UuidVale;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import  co.onlysystems.transacciones.shared.dtos.UsuarioDto;

@Data
@Setter
@Getter
public class EstablecimientoDto{
   public UuidVale uuid; 
   public String nit;
   public String razonSocial;
   public String  direccion; 
   public String telefono; 
   public String descripcion;
   public String referencia;
   public String observaciones; 
   public String estado;
   public String usuarioGestor;
   public UsuarioDto usuarioAdmin;
    
    public EstablecimientoDto(String nit, String razonSocial, String direccion, String telefono, String descripcion,
        String observaciones, String estado, String usuarioGestor,UsuarioDto usuarioAdmin) {
            this.nit = nit;
            this.razonSocial = razonSocial;
            this.direccion = direccion;
            this.telefono = telefono;
            this.descripcion = descripcion;
            this.observaciones = observaciones;
            this.estado = estado;
            this.usuarioGestor = usuarioGestor;
            this.usuarioAdmin = usuarioAdmin;
    }

    public EstablecimientoDto(UuidVale uuidVale){
        this.uuid = uuidVale;
    }

    public EstablecimientoDto(){

}
}