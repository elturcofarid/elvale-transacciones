package co.onlysystems.transacciones.cuentas.modelo.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cuenta")
public class CuentaEntity {
    @Id
    public UUID id;
    public UUID establecimiento; 
    public UUID  cliente; 
    public double montoAprobado;
    public String tipo;
    public String observaciones;
    public String estado;
    public String usuarioGestor;
    public LocalDateTime fechaGestion;
    public String usuarioAprobador;
    public LocalDateTime fechaAprobacion;
    public double balance;

 
    public CuentaEntity(UUID establecimiento, UUID cliente, double montoAprobado, String estado,
                        String observaciones, String usuarioGestor, LocalDateTime fechaGestion,
                        String usuarioAprobador, LocalDateTime fechaAprobacion, String tipo) {
                            this.id = UUID.randomUUID();
                            this.establecimiento = establecimiento;
                            this.cliente = cliente;
                            this.montoAprobado = montoAprobado;
                            this.estado = estado;
                            this.observaciones = observaciones;
                            this.usuarioGestor = usuarioGestor;
                            this.fechaGestion = fechaGestion;
                            this.usuarioAprobador = usuarioAprobador;
                            this.fechaAprobacion = fechaAprobacion;
                            this.tipo = tipo;
                        }


    @Override
    public String toString() {
        return "CuentaEntity{" +
                "id=" + id +
                ", establecimiento=" + establecimiento +
                ", cliente=" + cliente +
                ", montoAprobado=" + montoAprobado +
                ", tipo='" + tipo + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioGestor='" + usuarioGestor + '\'' +
                ", fechaGestion=" + fechaGestion +
                ", usuarioAprobador='" + usuarioAprobador + '\'' +
                ", fechaAprobacion=" + fechaAprobacion +
                ", balance=" + balance +
                '}';
    }
}