package dao;

import model.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class PrestamoDAO {
    public static void insertarPrestamo(Connection conn, Prestamo prestamo){
        String sql = "INSERT INTO prestamo (dia_prestamo,devolucion,persona_dni) VALUES (?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1,new java.sql.Date(prestamo.dia_prestamo.getTime()));
            ps.setDate(2,new java.sql.Date(prestamo.devolucion.getTime()));
            ps.setInt(3,prestamo.socio.dni);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar libros");
        }
    }

    public static Prestamo buscarPrestamoPorId(Connection conn,int id){
        String sql = "SELECT * FROM prestamo WHERE id = (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Prestamo prestamo = new Prestamo(rs.getInt("id"),
                        rs.getDate("dia_prestamo"),
                        rs.getDate("devolucion"),
                        PersonaDAO.buscarPersonaDni(conn, rs.getInt("persona_dni")));
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

    public static Prestamo buscarPrestamoPorDni(Connection conn,int dni){
        String sql = "SELECT * FROM prestamo WHERE persona_dni = (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Prestamo prestamo = new Prestamo(rs.getInt("id"),
                        rs.getDate("dia_prestamo"),
                        rs.getDate("devolucion"),
                        PersonaDAO.buscarPersonaDni(conn, rs.getInt("persona_dni")));
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
