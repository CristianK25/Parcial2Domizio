package dao;

import model.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class PersonaDAO {
    public static void insertarPersona(Connection conn, Persona persona){
        String sql = "INSERT INTO persona (dni,nombre) VALUES (?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,persona.dni);
            ps.setString(2,persona.nombre);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar persona");
        }
    }

    public static Persona buscarPersonaDni(Connection conn, int dni){
        String sql = "SELECT * FROM persona WHERE dni = (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Persona(rs.getInt("dni"),rs.getString("nombre"));
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Persona");
            return null;
        }

    }

    public static boolean existeDni(Connection conn, int dni){
        String sql = "SELECT * FROM persona WHERE dni = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dni);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al buscar el dni.");
            return false;
        }
    }

}

