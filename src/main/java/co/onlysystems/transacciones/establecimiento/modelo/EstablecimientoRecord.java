
package co.onlysystems.transacciones.establecimiento.modelo;

import co.onlysystems.transacciones.shared.values.Cuenta;
import co.onlysystems.transacciones.shared.values.Fecha;
import co.onlysystems.transacciones.shared.values.Monto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EstablecimientoRecord{
   public Cuenta cuentaOrigen; 
   public Monto monto; 
   public String tipo; 
   public String descripcion;
   public String referencia;
   public String observaciones; 
   public String estado;
   public String usuarioGestor;
   public Fecha fechaGestion;
   public String usuarioAprobador;
   public Fecha fechaAprobacion;
    
    
    public EstablecimientoRecord(Cuenta cuentaOrigen, Monto monto, String tipo, String descripcion, String referencia, String observaciones, String estado, String usuarioGestor, Fecha fechaGestion, String usuarioAprobador, Fecha fechaAprobacion) {
        this.cuentaOrigen = cuentaOrigen;
        this.monto = monto;
        this.tipo = tipo;
        this.descripcion = descripcion; 
        this.referencia = referencia;
        this.observaciones = observaciones;
        this.estado = estado;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion;
    }



}