package co.onlysystems.transacciones.shared.values;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fecha {
    private final LocalDateTime fecha;

    public Fecha() {
        this.fecha = null;
    }

    public Fecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Fecha(String fechaStr) {
        this.fecha = parseFecha(fechaStr);
    }

    private LocalDateTime parseFecha(String fechaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha proporcionada no tiene un formato válido: " + fechaStr, e);
        }
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return fecha.toString();
    }
}   