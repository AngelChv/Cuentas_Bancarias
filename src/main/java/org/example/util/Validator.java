package org.example.util;

/**
 * Clase para validar los datos de entrada.
 */
public class Validator {
    /**
     * Método para validar los dni.
     * @param dni a validar.
     * @return {@code true} si está compuesto por 8 números y una letra coincidente.
     */
    public static boolean dni(String dni) {
        boolean dniCorrecto = true;

        // Validar que sean 8 números y una letra.
        if (!dni.matches("^[0-9]{8}[A-Za-z]$")) {
            dniCorrecto = false;
        } else {
            // Validar la letra
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int numeros = Integer.parseInt(dni.substring(0, 8));
            char letra = Character.toUpperCase(dni.charAt(8));
            char letraCorrecta = letras.charAt(numeros % 23);

            if (letra != letraCorrecta) {
                dniCorrecto = false;
            }
        }

        return dniCorrecto;
    }

    /**
     * @param nombre a comprobar.
     * @return {@code true} si está compuesto por caractéres válidos.
     */
    public static boolean nombre(String nombre) {
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    // todo preguntar como quiere la validación.
    public static boolean iban(String iban) {
        return iban.matches("^[A-Za-z]{2}[0-9]{10,34}$");
    }
}
