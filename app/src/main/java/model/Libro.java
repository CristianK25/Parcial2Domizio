package model;

@SuppressWarnings("ALL")
public class Libro {
    public int numero;
    public String titulo;
    public String clasificacion;
    

    public Libro() {
    }

    public Libro(int numero,String titulo, String clasificacion) {
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "\n\tNumero = " + numero+
                "\n\tTitulo = " + titulo +
                "\n\tClasificacion = " +clasificacion;
    }
    
    
}
