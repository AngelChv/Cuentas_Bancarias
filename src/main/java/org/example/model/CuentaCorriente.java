package org.example.model;

import java.util.HashSet;

/**
 * Cuenta no remunerada destinada a recibir cobros.
 */
public abstract class CuentaCorriente extends CuentaBancaria{
    /**
     * Entidades a las cuales se les permite cobrar recibos domiciliados a la cuenta.
     */
    private HashSet<String> entidadesAutorizadas;

    public CuentaCorriente(Persona titular, double saldo, String iban, HashSet<String> entidadesAutorizadas) {
        super(titular, saldo, iban);
        this.entidadesAutorizadas = entidadesAutorizadas;
    }
}
