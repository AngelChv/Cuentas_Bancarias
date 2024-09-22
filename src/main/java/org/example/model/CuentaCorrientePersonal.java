package org.example.model;

/**
 * Cuenta corriente no remunerada a la cual se le aplica un coste fijo de mantenimeinto anual.
 */
public class CuentaCorrientePersonal extends CuentaCorriente {
    /**
     * Comisión de mantenimiento (cantidad fija anual).
     */
    private double comisionMantenimiento;

    public CuentaCorrientePersonal(Persona titular, double saldo, String iban, String[] entidadesAutorizadas, double comisionMantenimiento) {
        super(titular, saldo, iban, entidadesAutorizadas);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    public CuentaCorrientePersonal(Persona titular, double saldo, String iban, double comisionMantenimiento) {
        super(titular, saldo, iban);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String toString() {
        return super.toString() + "Comision Mantenimiento: " + comisionMantenimiento + "\n";
    }
}
