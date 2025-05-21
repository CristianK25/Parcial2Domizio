package model;

public class Persona {
    public int dni;
    public String nombre;

    public Persona() {
    }

    public Persona(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "\n\tdni = " + dni +
                "\n\tnombre = '" + nombre + '\'' +
                '}';
    }
}
