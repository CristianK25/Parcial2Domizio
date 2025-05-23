
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Libro;
import model.Prestamo;


public class PrestamoLibroDAO {
//    public static void insertarPrestamoLibro(Connection conn, Prestamo prestamo){
//        String sql = "INSERT INTO prestamo_libro (idPrestamo,numeroLibro) VALUES (?,?)"; 
//        try (PreparedStatement ps = conn.prepareStatement(sql)){
//            // Desactivamos auto-commit para manejar como transacción
//            conn.setAutoCommit(false);
//            for (Libro libro : prestamo.librosEscritos) {
//                ps.setInt(1, prestamo.id);
//                ps.setInt(2, libro.numero);
//                ps.addBatch(); // Agregamos a batch para ejecución masiva
//            }
//            int[] resultados = ps.executeBatch();
//                conn.commit();
//                
//            // Verificar que todos los inserts fueron exitosos
//            for (int resultado : resultados) {
//                if (resultado == PreparedStatement.EXECUTE_FAILED) {
//                    System.out.println("Al menos un libro no se insertó correctamente");
//                    return;
//                }
//            }
//        }catch(SQLException e){
//            System.out.println("Error insertando la relacion entre prestamo y libro :" + 
//                    prestamo.id + "-");
//        }
//    }
    public static void insertarPrestamoLibro(Connection conn, Prestamo prestamo) {
        String sql = "INSERT INTO prestamo_libro (idPrestamo, numeroLibro) VALUES (?, ?)";
        
        try {
            conn.setAutoCommit(false);
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Libro libro : prestamo.librosEscritos) {
                    ps.setInt(1, prestamo.id);
                    ps.setInt(2, libro.numero);  // Asegúrate que este campo se llame "numero"
                    ps.addBatch();
                }
                
                int[] resultados = ps.executeBatch();
                conn.commit();
                
                // Verificar que todos los inserts fueron exitosos
                for (int resultado : resultados) {
                    if (resultado == PreparedStatement.EXECUTE_FAILED) {
                        System.out.println("Al menos un libro no se insertó correctamente");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error insertando la relación entre préstamo y libro: " + e.getMessage());
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex.getMessage());
            }
            throw new RuntimeException("Error al insertar libros del préstamo", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error al restaurar auto-commit: " + e.getMessage());
            }
        }
    }
    
    public static ArrayList<Libro> obtenerLibrosPrestados(Connection conn,Prestamo prestamo){
        System.out.println("Buscando libros para préstamo ID: " + prestamo.id);
        ArrayList<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.* FROM libro l " +
                     "INNER JOIN prestamo_libro pl ON l.numero = pl.numeroLibro " +
                     "WHERE pl.idPrestamo = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, prestamo.id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Libro libro = new Libro();
                    libro.numero = rs.getInt("numero");
                    libro.titulo = rs.getString("titulo");
                    libro.clasificacion = rs.getString("clasificacion");
                    libros.add(libro);
                }
            }
            if(libros.isEmpty()) {
                System.out.println("ADVERTENCIA: No se encontraron libros para préstamo ID: " + prestamo.id);
            }
            return libros;
        }catch(SQLException e){
            System.out.println("Error obteniendo los libros del prestamo");
        }
        return null;
    }
}

