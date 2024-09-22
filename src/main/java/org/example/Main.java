package org.example;

import org.example.model.*;

import java.util.Scanner;

public class Main {
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
            } while (op < 0 || op > 6);

            switch (op) {
                case 1: // Crear cuenta.
                    crearCuenta();
                    break;
                case 2:
                    System.out.print("\n" + BANCO.listadoCuentas());
                    break;
                case 0:
                    System.out.println("\nSaliendo...");
            }
        } while (op != 0);
    }

    private static Persona crearPersona() {
        System.out.print("\nIntroduce el nombre del titular de la cuenta: ");
        String titular = SC.next();
        System.out.print("\nIntroduce el apellido del titular de la cuenta: ");
        String apellido = SC.next();
        // Todo agregar validación del dni.
        System.out.print("\nIntroduce el DNI del titular de la cuenta: ");
        String dni = SC.next();

        return new Persona(titular, apellido, dni);
    }

    private static void crearCuenta() {
        // Datos personales:
        Persona persona = crearPersona();

        // Saldo:
        System.out.print("\nIntroduce el saldo inicial: ");
        while (!SC.hasNextDouble()) {
            System.out.println("\nPor favor introduce un número decimal.");
            SC.next();
        }
        double saldo = SC.nextDouble();

        // Iban:
        System.out.print("\nIntroduce el número de cuenta: ");
        String iban = SC.next();

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
        } while (tipo < 1 || tipo > 3);

        switch (tipo) {
            case 1: // Cuenta de ahorro.
                BANCO.abrirCuenta(crearCuentaAhorro(persona, saldo, iban));
                break;
        }
    }

    private static CuentaAhorro crearCuentaAhorro(Persona persona, Double saldo, String iban) {
        System.out.print("\nIntroduce el interés de remuneración: ");
        while (!SC.hasNextFloat()) {
            System.out.println("\nIntroduce un número decimal.");
            SC.next();
        }
        float interes = SC.nextFloat();

        // Crear cuenta de ahorro:
        return new CuentaAhorro(persona, saldo, iban, interes);
    }
}