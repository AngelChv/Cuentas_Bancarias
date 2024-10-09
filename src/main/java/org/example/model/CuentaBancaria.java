package org.example.model;

/**
 * Clase abstracta genérica que engloba las cualidades comunes del resto de cuentas.
 * <ul>
 *     <li>{@link CuentaAhorro}</li>
 *     <li>{@link CuentaCorrientePersonal}</li>
 *     <li>{@link CuentaCorrienteEmpresa}</li>
 * </ul>
 */
public abstract class CuentaBancaria implements Imprimible{
    /**
     * Datos del titular.
     */
    private Persona titular;
    /**
     * Dinero en la cuenta.
     */
    private double saldo;
    /**
     * Número de cuenta.
     */
    private String iban;

    public CuentaBancaria(Persona titular, double saldo, String iban) {
        this.titular = titular;
        this.saldo = saldo;
        this.iban = iban;
    }

    /**
     * @return {@code String} con toda la información de la cuenta.
     */
    @Override
    public String devolverInfoString() {
        return "\nTipo de cuenta: " + getClass() + titular + "\nSaldo: " + saldo + "\nNúmero de cuenta: " + iban + "\n";
    }

    @Override
    public String toString() {
        return devolverInfoString();
    }

    /**
     * Las cuentas son consideradas iguales si su iban es el mismo.
     * @param o objeto a comparar.
     * @return {@code true} si son iguales.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuentaBancaria cuenta)) return false;

        return iban.equals(cuenta.iban);
    }

    @Override
    public int hashCode() {
        return iban.hashCode();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }
}
