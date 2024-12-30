package co.onlysystems.transacciones.cliente.modelo.values;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final String email;

    public Email(String email) {
        if (email == null || !isValid(email)) {
            throw new IllegalArgumentException("Email Invalido ");
        }
        this.email = email;
    }

    private boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
