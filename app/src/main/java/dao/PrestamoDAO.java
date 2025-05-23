package dao;

import model.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Libro;

@SuppressWarnings("ALL")
public class PrestamoDAO {
    public static void insertarPrestamo(Connection conn, Prestamo prestamo){
        String sql = "INSERT INTO prestamo (dia_prestamo,devolucion,persona_dni) VALUES (?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setDate(1,new java.sql.Date(prestamo.dia_prestamo.getTime()));
            ps.setDate(2,new java.sql.Date(prestamo.devolucion.getTime()));
            ps.setInt(3,prestamo.socio.dni);
            int columnasAfectadas = ps.executeUpdate();
            
            if (columnasAfectadas == 0) {
                JOptionPane.showMessageDialog(null,"La inserción del préstamo falló, no se insertaron filas.");
            }
            
            // Obtenemos el ID generado
            try (ResultSet idPrestamoGenerado = ps.getGeneratedKeys()) {
                if (idPrestamoGenerado.next()) {
                    int idGenerado = idPrestamoGenerado.getInt(1);
                    prestamo.setId(idGenerado); // Asignamos el ID al objeto prestamo
                    
                    // Ahora insertamos los libros asociados
                    PrestamoLibroDAO.insertarPrestamoLibro(conn, prestamo);
                } else {
                    JOptionPane.showMessageDialog(null,"La inserción del préstamo falló, no se obtuvo ID.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al insertar prestamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Prestamo buscarPrestamoPorDni(Connection conn,int dni){
        String sql = "SELECT * FROM prestamo WHERE persona_dni = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int idPrestamo = rs.getInt("id");
                System.out.println("DEBUG: ID de préstamo encontrado en BD: " + idPrestamo);
                Prestamo prestamo = new Prestamo(idPrestamo,
                        rs.getDate("dia_prestamo"),
                        rs.getDate("devolucion"),
                        PersonaDAO.buscarPersonaDni(conn, rs.getInt("persona_dni")));
                ArrayList<Libro> libros = PrestamoLibroDAO.obtenerLibrosPrestados(conn, prestamo);
                prestamo.setLibrosEscritos(libros);
                return prestamo;
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar prestamo");
            return null;
        }
    }


}
