
package co.onlysystems.transacciones.cuentas.modelo.dto;

import co.onlysystems.transacciones.shared.values.Fecha;
import co.onlysystems.transacciones.shared.values.Monto;    


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CuentaRecord{
   public String id; 
   public Monto montoAprobado; 
   public String tipo; 
   public String observaciones; 
   public String estado;
   public String usuarioGestor;
   public Fecha fechaGestion;
   public String usuarioAprobador;
   public Fecha fechaAprobacion;
    

   
   public CuentaRecord(String id, Monto montoAprobado, String tipo,  
                  String observaciones, String usuarioGestor, Fecha fechaGestion, 
                  String usuarioAprobador, Fecha fechaAprobacion) {
        this.id = id;
        this.montoAprobado = montoAprobado;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion;
    }
}