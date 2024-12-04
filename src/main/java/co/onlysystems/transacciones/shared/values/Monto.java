package co.onlysystems.transacciones.shared.values;

public class Monto {

    private final double valor;

    public Monto(double valor) {
        if (valor < 0) throw new IllegalArgumentException("El monto no puede ser negativo.");
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
} 
    
