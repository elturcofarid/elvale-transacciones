
package co.onlysystems.transacciones.cuentas.modelo.dto;

import co.onlysystems.transacciones.shared.values.Fecha;
import co.onlysystems.transacciones.shared.values.Monto;
import co.onlysystems.transacciones.shared.values.UuidVale;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CuentaDto{
   public UuidVale idEstablecimiento; 
   public UuidVale idCliente; 
   public Monto montoAprobado; 
   public String tipo; 
   public String observaciones; 
   public String estado;
   public String usuarioGestor;
   public Fecha fechaGestion;
   public String usuarioAprobador;
   public Fecha fechaAprobacion;
    
   public CuentaDto(){} 

   public CuentaDto(UuidVale idEstablecimiento, UuidVale idCliente, Monto montoAprobado, String tipo,   
                  String observaciones, String usuarioGestor) {
                    this.idEstablecimiento = idEstablecimiento;
                    this.idCliente = idCliente;
                    this.montoAprobado = montoAprobado; 
                    this.tipo = tipo;
                    this.observaciones = observaciones;
                    this.usuarioGestor = usuarioGestor;
                }

}