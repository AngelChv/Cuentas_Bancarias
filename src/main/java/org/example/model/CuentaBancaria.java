package org.example.model;

/**
 * Clase abstracta genérica que engloba las cualidades comunes del resto de cuentas.
 * <li>Cuenta Ahorro.</li>
 * <li>Cuenta Corriente Personal.</li>
 * <li>Cuenta corriente Empresa.</li>
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

    @Override
    public String devolverInfoString() {
        return titular + "\nSaldo: " + saldo + "\nNúmero de cuenta: " + iban + "\n";
    }

    @Override
    public String toString() {
        return devolverInfoString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CuentaBancaria)) return false;

        CuentaBancaria that = (CuentaBancaria) o;
        return iban.equals(that.iban);
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
