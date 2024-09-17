package org.example.model;

import java.util.HashSet;

/**
 * Cuenta corriente no remunerada a la cual se le aplica un coste fijo de mantenimeinto anual.
 */
public class CuentaCorrientePersonal extends CuentaCorriente {
    /**
     * Comisión de mantenimiento (cantidad fija anual).
     */
    private double comisionMantenimiento;

    public CuentaCorrientePersonal(Persona titular, double saldo, String iban, HashSet<String> entidadesAutorizadas, double comisionMantenimiento) {
        super(titular, saldo, iban, entidadesAutorizadas);
        this.comisionMantenimiento = comisionMantenimiento;
    }
}
