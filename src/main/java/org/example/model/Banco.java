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
     * Añade una cuenta al banco.
     * @param cuenta a añadir.
     * @return {@code true} si no existe la cuenta y se ha añadido correctamente.
     */
    public static boolean abrirCuenta(CuentaBancaria cuenta) {
        return cuenta != null && cuentas.add(cuenta);
    }

    /**
     * Lista la información de las cuentas del banco.
     * @return una cadena de caracteres con la información de cada cuenta.
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
                if (cuenta.getIban().equalsIgnoreCase(iban.toUpperCase())) {
                    return cuenta.toString();
                }
            }
        }
        return null;
    }

    /**
     *  Ingresa en una cuenta determinada.
     * @param iban de la cuenta donde se van a ingresar los fondos.
     * @param ingreso cantidad a ingresar.
     * @return {@code true} si el ingreso se ha realizado correctamente.<br>
     * {@code false} si la cuenta no existe.
     */
    public static boolean ingresoCuenta(String iban, double ingreso) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() + ingreso);
                return true;
            }
        }
        return false;
    }

    /**
     * Retira efectivo de una cuenta especificada.
     * @param iban de la cuenta donde se van a retirar los fondos.
     * @param retirada cantidad a retirar.
     * @return {@code true} si la retirada se ha realizado con éxito.<br>
     * {@code false} si la cuenta no existe.
     */
    public static boolean retiradaCuenta(String iban, double retirada) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                cuenta.setSaldo(cuenta.getSaldo() - retirada);
                return true;
            }
        }
        return false;
    }

    /**
     * @param iban de la cuenta que se desea saber el saldo.
     * @return {@code double} con el saldo.
     */
    public static double obtenerSaldo(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                return cuenta.getSaldo();
            }
        }
        return -1;
    }
}
