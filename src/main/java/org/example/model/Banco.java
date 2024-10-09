package org.example.model;

import java.util.HashSet;

/**
 * Clase que gestiona todas las operaciones relacionadas con las cuentas.
 */
public class Banco {
    /**
     * Almacena las diferentes cuentas del banco.
     */
    private static final HashSet<CuentaBancaria> cuentas = new HashSet<>();

    /**
     * Constructor privado para evitar la creación de instancias.
     */
    private Banco() {}

    /**
     * Añade la cuenta al banco.
     * @param cuenta a añadir.
     * @return {@code true} si no existe la cuenta.
     */
    public static boolean abrirCuenta(CuentaBancaria cuenta) {
        return cuenta != null && cuentas.add(cuenta);
    }

    /**
     * @return una cade de caracteres con la información de cada cuenta.
     */
    public static String[] listadoCuentas() {
        String[] cuentasLista = new String[cuentas.size()];
        int i = 0;
        for (CuentaBancaria cuenta : cuentas) {
            cuentasLista[i] = cuenta.toString();
            i++;
        }
        return cuentasLista;
    }

    /**
     * Busca una cuenta en concreto y devuelve su información.
     * @param iban de la cuenta a buscar.
     * @return cadena de caracteres con la información de la cuenta que tenga dicho iban, si no se encuentra ninguna,
     * devuelve null.
     */
    public static String informacionCuenta(String iban) {
        if (!cuentas.isEmpty()) {
            for (CuentaBancaria cuenta : cuentas) {
                if (cuenta.getIban().equals(iban.toUpperCase())) {
                    return cuenta.toString();
                }
            }
        }
        return null;
    }

    public static boolean ingresoCuenta(String iban, double ingreso) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() + ingreso);
                return true;
            }
        }
        return false;
    }

    public static boolean retiradaCuenta(String iban, double retirada) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() - retirada);
                return true;
            }
        }
        return false;
    }

    public static double obtenerSaldo(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                return cuenta.getSaldo();
            }
        }
        return -1;
    }
}
