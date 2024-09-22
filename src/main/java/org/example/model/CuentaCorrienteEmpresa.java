package org.example.model;

/**
 * Único tipo de cuenta que permiten tener descubiertos.
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente{
    /**
     * Número máximo de descubiertos permitidos.
     */
    private int maxDescubiertos;
    /**
     * Porcentaje de intereses aplicado por descubierto.
     */
    private float interesDescubierto;
    /**
     * Comisión fija por descubierto.
     */
    private float comisionDescubierto;

    public CuentaCorrienteEmpresa(
            Persona titular, double saldo, String iban, String[] entidadesAutorizadas, int maxDescubiertos,
            float interesDescubierto, float comisionDescubierto) {
        super(titular, saldo, iban, entidadesAutorizadas);
        this.maxDescubiertos = maxDescubiertos;
        this.interesDescubierto = interesDescubierto;
        this.comisionDescubierto = comisionDescubierto;
    }
}
