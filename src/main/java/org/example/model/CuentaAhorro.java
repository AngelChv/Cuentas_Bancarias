package org.example.model;

/**
 * Cuenta remunerada con determinado tipo de interés.
 */
public class CuentaAhorro extends CuentaBancaria{
    /**
     * Porcentaje de intereses anuales.
     */
    private float interes;

    public CuentaAhorro(Persona titular, double saldo, String iban, float interes) {
        super(titular, saldo, iban);
        this.interes = interes;
    }
}
