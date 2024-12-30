package co.onlysystems.transacciones.fiao.modelo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import co.onlysystems.transacciones.shared.values.*;

@Data
@Setter
@Getter
public class FiaoRecord{
   public Cuenta cuenta;
   public Monto valorFio; 
   public String tipo; 
   public String observaciones; 
   public String estado;
   public Usuario usuarioGestor;
   public Fecha fechaGestion;
   public Usuario usuarioAprobador;
   public Fecha fechaAprobacion;
    

   
   public FiaoRecord(Cuenta cuenta, Monto valorFio, String tipo,  
                  String observaciones, Usuario usuarioGestor, Fecha fechaGestion, 
                  Usuario usuarioAprobador, Fecha fechaAprobacion) {
        this.cuenta = cuenta;
        this.valorFio = valorFio;
        this.tipo = tipo;
        this.observaciones = observaciones;
        this.usuarioGestor = usuarioGestor;
        this.fechaGestion = fechaGestion;
        this.usuarioAprobador = usuarioAprobador;
        this.fechaAprobacion = fechaAprobacion;
    }
}