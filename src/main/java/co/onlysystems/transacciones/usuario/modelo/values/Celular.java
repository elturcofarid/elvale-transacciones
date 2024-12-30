package co.onlysystems.transacciones.usuario.modelo.values;

import java.util.Objects;
import java.util.regex.Pattern;

public class Celular {
    private static final Pattern CELULAR_PATTERN = Pattern.compile(
            "^\\s*3\\d{2}\\s*\\d{3}\\s*\\d{4}$");

    private final String numero;

    public Celular(String numero) {
        if (numero == null || !esValido(numero)) {
            throw new IllegalArgumentException("Número de celular inválido");
        }
        this.numero = numero;
    }

    private boolean esValido(String numero) {
        return CELULAR_PATTERN.matcher(numero).matches();
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Celular)) return false;
        Celular celular = (Celular) o;
        return Objects.equals(numero, celular.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
