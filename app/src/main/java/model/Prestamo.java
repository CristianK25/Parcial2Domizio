package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    public int numero;
    public Date dia_prestamo;
    public Date devolucion;
    public Persona socio;
    public ArrayList<Libro> librosEscritos;

    public Prestamo() {
        this.librosEscritos = new ArrayList<>();
        // Obtener la fecha actual
        this.dia_prestamo = new Date();

        // Crear una instancia de Calendar y sumarle 7 días
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia_prestamo);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        this.devolucion = calendar.getTime();

    }

    public Prestamo(int numero, Date dia_prestamo, Date devolucion) {
        this.numero = numero;
        this.dia_prestamo = dia_prestamo;
        this.devolucion = devolucion;
        this.librosEscritos = new ArrayList<>();
    }

    public Prestamo(int numero, Date dia_prestamo, Date devolucion, Persona socio) {
        this.numero = numero;
        this.dia_prestamo = dia_prestamo;
        this.devolucion = devolucion;
        this.socio = socio;
        this.librosEscritos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro){
        this.librosEscritos.add(libro);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "\nNumero Prestamo= " + numero +
                "\nDia Prestamo = " + dia_prestamo +
                "\nDia de Devolucion = " + devolucion +
                "\nSocio = " + socio +
                "\nLibros Escritos = " + librosEscritos +
                "\n}";
    }


}

