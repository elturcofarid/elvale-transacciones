package co.onlysystems.transacciones.establecimiento.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "establecimiento")
public class Establecimiento {
    @Id
    private String id;
    private UUID cuentaOrigen;
    private double monto;
    private String tipo;
    private String descripcion;
    private String referencia;
    private String observaciones;
    private String estado;
    private String usuarioGestor;
    private LocalDateTime fechaGestion;
    private String usuarioAprobador;
    private LocalDateTime fechaAprobacion;

    // Constructor
    public Establecimiento(UUID cuentaOrigen, double monto, String tipo, 
                       String descripcion, String referencia, String observaciones, 
                       String estado, String usuarioGestor, LocalDateTime fechaGestion, 
                       String usuarioAprobador, LocalDateTime fechaAprobacion) {
        this.id = null;
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

    // Getters
    public String getId() {
        return id;
    }

    public UUID getCuentaOrigen() {
        return cuentaOrigen;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public String getUsuarioGestor() {
        return usuarioGestor;
    }

    public LocalDateTime getFechaGestion() {
        return fechaGestion;
    }

    public String getUsuarioAprobador() {
        return usuarioAprobador;
    }

    public LocalDateTime getFechaAprobacion() {
        return fechaAprobacion;
    }

    // Setters (si necesitas modificar los valores después de la creación)
    public void setId(String id) {
        this.id = id;
    }

    public void setCuentaOrigen(UUID cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUsuarioGestor(String usuarioGestor) {
        this.usuarioGestor = usuarioGestor;
    }

    public void setFechaGestion(LocalDateTime fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public void setUsuarioAprobador(String usuarioAprobador) {
        this.usuarioAprobador = usuarioAprobador;
    }

    public void setFechaAprobacion(LocalDateTime fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id='" + id + '\'' +
                ", cuentaOrigen='" + cuentaOrigen + '\'' +
                ", monto=" + monto +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", referencia='" + referencia + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioGestor='" + usuarioGestor + '\'' +
                ", fechaGestion='" + fechaGestion + '\'' +
                ", usuarioAprobador='" + usuarioAprobador + '\'' +
                ", fechaAprobacion='" + fechaAprobacion + '\'' +
                '}';
    }
}
