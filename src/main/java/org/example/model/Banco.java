package org.example.model;

import java.util.HashSet;

/**
 * Clase que gestiona todas las operaciones relacionadas con las cuentas.
 */
public class Banco {
    /**
     * Almacena las diferentes cuentas del banco.
     */
    private HashSet<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new HashSet<>();
    }

    public boolean abrirCuenta(CuentaBancaria cuenta) {
        return cuentas.add(cuenta);
    }

    public String listadoCuentas() {
        return cuentas.toString();
    }

    public String informacionCuenta(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                return cuenta.toString();
            }
        }
        return null;
    }

    public boolean ingresoCuenta(String iban, double ingreso) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() + ingreso);
                return true;
            }
        }
        return false;
    }

    public boolean retiradaCuenta(String iban, double retirada) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() - retirada);
                return true;
            }
        }
        return false;
    }

    public double obtenerSaldo(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                return cuenta.getSaldo();
            }
        }
        return -1;
    }
}
