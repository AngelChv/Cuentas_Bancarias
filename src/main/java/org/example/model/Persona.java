package org.example.model;

/**
 * Clase con los datos personales.
 */
public class Persona implements Imprimible{
    private String nombre;
    private String apellidos;
    private String dni;

    public Persona(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    @Override
    public String devolverInfoString() {
        return "Nombre: " + nombre + ", Apellidos: " + apellidos + ", DNI: " + dni;
    }
}
