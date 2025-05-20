package model;

public class Libro {
    public String titulo;
    public String clasificacion;
    public int numero;
    public Prestamo prestado;

    public Libro() {
    }

    public Libro(String titulo, String clasificacion, int numero) {
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.numero = numero;
    }

    public Libro(String titulo, String clasificacion, int numero, Prestamo prestado) {
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.numero = numero;
        this.prestado = prestado;
    }
}
