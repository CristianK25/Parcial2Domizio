package model;

public class Persona {
    public int dni;
    public String nombre;
    public Prestamo prestamo;

    public Persona() {
    }

    public Persona(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Persona(int dni, String nombre, Prestamo prestamo) {
        this.dni = dni;
        this.nombre = nombre;
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "\ndni = " + dni +
                "\nnombre = '" + nombre + '\'' +
                "\nprestamo = " + prestamo +
                '}';
    }
}
