package org.example.model;

/**
 * Cuenta no remunerada destinada a recibir cobros.
 */
public abstract class CuentaCorriente extends CuentaBancaria{
    /**
     * Entidades a las cuales se les permite cobrar recibos domiciliados a la cuenta.
     */
    private String[] entidadesAutorizadas;

    public CuentaCorriente(Persona titular, double saldo, String iban, String[] entidadesAutorizadas) {
        super(titular, saldo, iban);
        this.entidadesAutorizadas = entidadesAutorizadas;
    }

    public CuentaCorriente(Persona titular, double saldo, String iban) {
        super(titular, saldo, iban);
    }
}
