package org.example;

import org.example.model.*;
import org.example.util.Validator;

import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    /**
     * {@link Banco}
     */
    private static final Banco BANCO = new Banco();
    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        do {
            do {
                System.out.print("""
                \n1. Abrir una nueva cuenta.
                2. Ver un listado de las cuentas disponibles.
                3. Obtener los datos de una cuenta concreta.
                4. Realizar un ingreso en una cuenta.
                5. Retirar efectivo de una cuenta.
                6. Consultar el saldo actual de una cuenta.
                0. Salir de la aplicación.
                Elige una opción:\s""");
                while (!SC.hasNextInt()) {
                    System.out.println("\nIntroduce un número del 0 al 6.");
                    SC.next();
                }
                op = SC.nextInt();
                SC.nextLine(); // Limpiar buffer del teclado para eliminar el salto de línea.
            } while (op < 0 || op > 6);

            switch (op) {
                case 1: // Crear cuenta y añadirla al banco.
                    if (BANCO.abrirCuenta(crearCuenta())) System.out.println("\nCuenta creada con éxito.");
                    else System.out.println("\nNo se ha podido crear la cuenta.");
                    break;
                case 2: // Mostrar cuentas.
                    System.out.print("\n" + BANCO.listadoCuentas());
                    break;
                case 3: // Datos de una cuenta concreta.
                    System.out.print("\nIntroduce el número de cuenta: ");
                    String iban = SC.next();
                    if (BANCO.informacionCuenta(iban) != null) System.out.println(BANCO.informacionCuenta(iban));
                    else System.out.println("\nLa cuenta no existe");
                    break;
                case 0: // Salir.
                    System.out.println("\nSaliendo...");
            }
        } while (op != 0);

        SC.close();
    }

    /**
     * Pide por teclado la información necesaria.
     * @return {@link CuentaBancaria} con los datos obtenidos. Pudiendo ser:
     * <li>{@link CuentaAhorro}</li>
     * <li>{@link CuentaCorrientePersonal}</li>
     * <li>{@link CuentaCorrienteEmpresa}</li>
     */
    private static CuentaBancaria crearCuenta() {
        CuentaBancaria cuenta = null;
        // Datos personales:
        Persona persona = new Persona(pedirTitular(), pedirApellidos(), pedirDNI());

        // Saldo:
        double saldo = pedirSaldo();

        // Iban:
        String iban = pedirString("Introduce el número de cuenta: ", Validator::iban,
                "El número de cuenta no es válido.").toUpperCase();

        // Pedir el tipo de cuenta:
        int tipo;
        do {
            System.out.print("""
                        \n1. Cuenta de ahorro.
                        2. Cuenta corriente personal.
                        3. cuenta corriente de empresa.
                        Introduce el tipo de cuenta:\s""");
            while (!SC.hasNextInt()) {
                System.out.println("\nIntroduce un número del 1 al 3.");
                SC.next();
            }
            tipo = SC.nextInt();
            SC.nextLine();
        } while (tipo < 1 || tipo > 3);

        cuenta = switch (tipo) {
            case 1 -> // Cuenta de ahorro.
                    new CuentaAhorro(persona, saldo, iban, pedirInteres());
            case 2 -> // Cuenta corriente personal.
                // todo preguntar si es necesario pedir o añadir las entidades en las cuentas corrientes.
                    new CuentaCorrientePersonal(persona, saldo, iban, pedirComision());
            case 3 -> // Cuenta corriente de empresa.
                // todo preguntar si es necesario pedir o añadir las entidades en las cuentas corrientes.
                    new CuentaCorrienteEmpresa(persona, saldo, iban, pedirMaxDescubiertos(),
                            pedirInteresDescubierto(), pedirComisionDescubierto());
            default -> cuenta;
        };

        return cuenta;
    }

    private static float pedirFloat(String mensaje) {
        System.out.print("\n" + mensaje);
        while (!SC.hasNextFloat()) {
            System.out.println("\nIntroduce un número decimal.");
            SC.next();
        }
        float valor = SC.nextFloat();
        SC.nextLine();
        if (valor >= 0) return valor;
        System.out.println("\nNo se puede introducir un valor menor a 0.");
        return pedirFloat(mensaje);
    }

    /**
     * Se encarga de pedir una cadena de caractéres por la entrada estándar y validarla en función de la condición
     * pasada como parámetro, si se cumple se devuelve la cadena, si no es así, se vuelve a pedir hasta que se
     * cumpla.
     * @param mensaje Mensaje que se solicita al usuario.
     * @param validator debe de ser una función que reciba un {@code String} y devuelva un {@code Boolean}
     *                  como lo establece la interfáz {@link Predicate}.
     *                  En este caso, yo uso los métodos de la clase Validator.
     * @param error Mensaje en caso de que la cadena no cumpla con la validación.
     * @return {@code true} si la validación es correcta.
     */
    private static String pedirString(String mensaje, Predicate<String> validator, String error) {
        System.out.print("\n" + mensaje);
        String valor = SC.nextLine();
        if (validator.test(valor)) return valor;
        System.out.print("\n" + error);
        return pedirString(mensaje, validator, error);
    }

    /**
     * Pide por teclado el nombre del titular.
     * @return el nombre del titular en caso de ser válido, si no lo es, lo vuelve a pedir hasta que así
     * lo sea.
     */
    private static String pedirTitular() {
        return pedirString("Introduce el nombre del titular de la cuenta: ", Validator::nombre,
                "El nombre no es válido");
    }

    /**
     * Pide por teclado los apellidos del titular.
     * @return los apellidos en caso de ser válidos, si no lo son, los vuelve a pedir hasta que así
     * lo sea.
     */
    private static String pedirApellidos() {
        return pedirString("Introduce los apellidos del titular de la cuenta:", Validator::nombre,
                "Los apellidos no son válidos.");
    }

    /**
     * Pide por teclado el DNI del titular.
     * @return el DNI del titular en caso de ser válido, si no lo es, lo vuelve a pedir hasta que así
     * lo sea.
     */
    private static String pedirDNI() {
        return pedirString("Introduce el DNI del titular de la cuenta: ", Validator::dni,
                "El DNI no es válido").toUpperCase();
    }

    private static double pedirSaldo() {
        System.out.print("\nIntroduce el saldo inicial: ");
        while (!SC.hasNextDouble()) {
            System.out.println("\nPor favor introduce un número decimal.");
            SC.next();
        }
        double saldo = SC.nextDouble();
        SC.nextLine();
        if (saldo >= 0) return saldo;
        System.out.println("\nEl saldo no puede ser menor a 0.");
        return pedirSaldo();
    }

    private static float pedirInteres() {
        return pedirFloat("Introduce el interés de remuneración: ");
    }

    private static float pedirComision() {
        return pedirFloat("Introduce la comisión de mantenimiento: ");
    }

    private static int pedirMaxDescubiertos() {
        System.out.print("\nIntroduce el número máximo de descubiertos permitidos: ");
        while (!SC.hasNextInt()) {
            System.out.println("\nIntroduce un número entero.");
            SC.next();
        }
        int maxDescubiertos = SC.nextInt();
        SC.nextLine();
        if (maxDescubiertos >= 0) return maxDescubiertos;
        System.out.println("\nEl máximo de descubiertos no puede ser menor a 0.");
        return pedirMaxDescubiertos();
    }

    private static float pedirInteresDescubierto() {
        return pedirFloat("Introduce los intereses por descubierto: ");
    }

    private static float pedirComisionDescubierto() {
        return pedirFloat("Introduce la comisión por descubierto: ");
    }
}