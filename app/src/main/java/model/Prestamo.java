package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    public int id;
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

    public Prestamo(Date dia_prestamo, Date devolucion) {
        this.dia_prestamo = dia_prestamo;
        this.devolucion = devolucion;
        this.librosEscritos = new ArrayList<>();
    }

    public Prestamo(int id,Date dia_prestamo, Date devolucion, Persona socio) {
        this.id = id;
        this.dia_prestamo = dia_prestamo;
        this.devolucion = devolucion;
        this.socio = socio;
        this.librosEscritos = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSocio(Persona socio) {
        this.socio = socio;
    }

    public void setLibrosEscritos(ArrayList<Libro> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }
    
    
    
    public void agregarLibro(Libro libro){
        this.librosEscritos.add(libro);
    }

    @Override
    public String toString() {
        String totalLibrosEscritos = "\n";
        for (Libro l: this.librosEscritos){
            totalLibrosEscritos+= l.toString()+"\n\t------------------";
        }
        return "\nNumero Prestamo = " + id +
                "\nDia Prestamo = " + dia_prestamo +
                "\nDia de Devolucion = " + devolucion +
                "\nSocio = " + socio +
                "\nLibros Escritos = {" + totalLibrosEscritos +
                "\n}";
    }


}

