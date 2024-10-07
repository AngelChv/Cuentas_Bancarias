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

    /**
     * Añade la cuenta al banco.
     * @param cuenta a añadir.
     * @return {@code true} si no existe la cuenta.
     */
    public boolean abrirCuenta(CuentaBancaria cuenta) {
        return cuentas.add(cuenta);
    }

    /**
     * @return una cade de caracteres con la información de cada cuenta.
     */
    public String listadoCuentas() {
        return cuentas.toString();
    }

    /**
     * Busca una cuenta en concreto y devuelve su información.
     * @param iban de la cuenta a buscar.
     * @return cadena de caracteres con la información de la cuenta que tenga dicho iban, si no se encuentra ninguna,
     * devuelve null.
     */
    public String informacionCuenta(String iban) {
        if (!cuentas.isEmpty()) {
            for (CuentaBancaria cuenta : cuentas) {
                if (cuenta.getIban().equals(iban)) {
                    return cuenta.toString();
                }
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
