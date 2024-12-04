package co.onlysystems.transacciones.fiao.modelo.dto;

import co.onlysystems.transacciones.shared.values.Fecha;
import co.onlysystems.transacciones.shared.values.Usuario;
import co.onlysystems.transacciones.shared.values.UuidVale;


public class AprobacionFiao extends UuidVale{

   public Usuario usuarioAprobador;
   public Fecha fechaAprobacion;
    
   public AprobacionFiao(String uuid){
    super(uuid);
   }

   public AprobacionFiao(String uuid,Usuario usuario, Fecha fecha){
    super(uuid);
    this.fechaAprobacion= fecha;
    this.usuarioAprobador= usuario;
   }

   @Override
   public String toString() {
       return "AprobacionFiao{" +
               "fechaAprobacion=" + fechaAprobacion +
               ", usuarioAprobador=" + usuarioAprobador +
               '}';
   }
}
