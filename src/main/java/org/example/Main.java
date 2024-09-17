package org.example;

import org.example.model.*;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        Persona persona = new Persona("Angel", "Chicote Veganzones", "45577845X");
        CuentaBancaria cuenta = new CuentaAhorro(persona, 100, "123", 0.02f);
        banco.abrirCuenta(cuenta);

        HashSet<String> entidades = new HashSet<>();
        entidades.add("Endesa");
        CuentaBancaria cuenta2 = new CuentaCorrienteEmpresa(persona, 2000, "1234", entidades, 10, 1, 2);
        banco.abrirCuenta(cuenta2);
        System.out.println(banco.listadoCuentas());

        banco.ingresoCuenta("123", 1000);
        System.out.println(banco.informacionCuenta("123"));

        banco.retiradaCuenta("1234", 2001);
        System.out.println(banco.obtenerSaldo("1234"));
    }
}