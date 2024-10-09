package org.example;

import org.example.model.*;
import org.example.util.Validator;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    /**
     * {@link Banco}
     */
    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        do {
            System.out.print("""
            \n1. Abrir una nueva cuenta.
            2. Ver un listado de las cuentas disponibles.
            3. Obtener los datos de una cuenta concreta.
            4. Realizar un ingreso en una cuenta.
            5. Retirar efectivo de una cuenta.
            6. Consultar el saldo actual de una cuenta.
            0. Salir de la aplicación.""");
            op = pedirInt("Elige una opción: ", (Integer i) -> i >= 0 && i <= 6,
                    "Introduce un número del 0 al 6.");

            switch (op) {
                case 1: // Crear cuenta y añadirla al banco.
                    if (Banco.abrirCuenta(crearCuenta())) System.out.println("\nCuenta creada con éxito.");
                    else System.out.println("\nNo se ha podido crear la cuenta.");
                    break;
                case 2: // Mostrar cuentas.
                    String[] cuentaBancarias = Banco.listadoCuentas();
                    System.out.println(Arrays.toString(cuentaBancarias));
                    break;
                case 3: // Datos de una cuenta concreta.
                    System.out.print("\nIntroduce el número de cuenta: ");
                    String iban = SC.next();
                    String info = Banco.informacionCuenta(iban);
                    if (info != null) System.out.println(info);
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
        CuentaBancaria cuenta;
        // Datos personales:
        String nombre = pedirString("Introduce el nombre del titular de la cuenta: ", Validator::nombre,
                "El nombre no es válido");
        String apellidos = pedirString("Introduce los apellidos del titular de la cuenta: ", Validator::nombre,
                "Los apellidos no son válidos.");
        String dni = pedirString("Introduce el DNI del titular de la cuenta: ", Validator::dni,
                "El DNI no es válido").toUpperCase();
        Persona persona = new Persona(nombre, apellidos, dni);

        // Saldo:
        double saldo = pedirFloat("Introduce el saldo inicial: ", (Float f) -> f >= 0,
                "El saldo no puede ser negativo");

        // Iban:
        String iban = pedirString("Introduce el número de cuenta (ES1234567890): ", Validator::iban,
                "El número de cuenta no es válido.").toUpperCase();

        // Pedir el tipo de cuenta:
        System.out.print("""
                    \n1. Cuenta de ahorro.
                    2. Cuenta corriente personal.
                    3. cuenta corriente de empresa.""");
        int tipo = pedirInt("Introduce el tipo de cuenta: ", (Integer i) -> i >= 1 && i <= 3,
                "Introduce un número del 1 al 3.");

        cuenta = switch (tipo) {
            case 1 -> // Cuenta de ahorro.
                    new CuentaAhorro(persona, saldo, iban,
                            // Interés de remuneración.
                            pedirFloat("Introduce el interés de remuneración: ",
                                    (Float f) -> f >= 0,
                                    "El interés no puede ser negativo."));
            case 2 -> // Cuenta corriente personal.
                    new CuentaCorrientePersonal(persona, saldo, iban,
                            // Comisión de mantenimiento.
                            pedirFloat("Introduce la comisión de mantenimiento: ",
                                    (Float f) -> f >= 0,
                                    "La comisión no puede ser negativa."));
            case 3 -> // Cuenta corriente de empresa.
                    new CuentaCorrienteEmpresa(persona, saldo, iban,
                            // Número máximo de descubiertos permitidos.
                            pedirInt( "Introduce el número máximo de descubiertos permitidos: ",
                                    (Integer i) -> i >= 0,
                                    "Introduce un número positivo."),

                            // Intereses por descubierto.
                            pedirFloat("Introduce los intereses por descubierto: ",
                                    (Float f) -> f >= 0,
                                    "Los intereses por descubierto no puede ser negativo."),

                            // Comisión por descubierto.
                            pedirFloat("Introduce la comisión por descubierto:",
                                    (Float f) -> f >= 0,
                                    "La comisión no puede ser negativa."));
            default -> null;
        };

        return cuenta;
    }

    private static float pedirFloat(String mensaje, Predicate<Float> validator, String error) {
        System.out.print("\n" + mensaje);
        while (!SC.hasNextFloat()) {
            System.out.println("\nIntroduce un número decimal.");
            SC.next();
        }
        float valor = SC.nextFloat();
        SC.nextLine(); // Limpiar buffer del teclado para eliminar el salto de línea.
        if (validator.test(valor)) return valor;
        System.out.println("\n" + error);
        return pedirFloat(mensaje, validator, error);
    }

    private static int pedirInt(String mensaje, Predicate<Integer> validator, String error) {
        System.out.print("\n" + mensaje);
        while (!SC.hasNextInt()) {
            System.out.println("\nIntroduce un número entero.");
            SC.next();
        }
        int valor = SC.nextInt();
        SC.nextLine(); // Limpiar buffer del teclado para eliminar el salto de línea.
        if (validator.test(valor)) return valor;
        System.out.println("\n" + error);
        return pedirInt(mensaje, validator, error);
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
     * @return {@code String} con la cadena introducida por el usuario, si la validación es correcta.
     */
    private static String pedirString(String mensaje, Predicate<String> validator, String error) {
        System.out.print("\n" + mensaje);
        String valor = SC.nextLine();
        if (validator.test(valor)) return valor;
        System.out.print("\n" + error);
        return pedirString(mensaje, validator, error);
    }
}