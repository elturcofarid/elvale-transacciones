package co.onlysystems.transacciones.shared.values;

public class Monto {

    private final int valor;

    public Monto(int valor) {
        if (valor < 0) throw new IllegalArgumentException("El monto no puede ser negativo.");
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
} 
    
