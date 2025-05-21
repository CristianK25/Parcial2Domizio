package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Libro;

public class LibroDAO {

    public static void insertarLibro(Connection conn, Libro libro){
        String sql = "INSERT INTO libro (titulo,clasificacion,numero) VALUES (?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,libro.titulo);
            ps.setString(2,libro.clasificacion);
            ps.setInt(3,libro.numero);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar libros");
        }
    }

    public static Libro buscarLibroPorNumero(Connection conn, int numero){
        String sql = "SELECT * FROM libro WHERE numero = (?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Libro(rs.getInt("numero"),rs.getString("titulo"),rs.getString("clasificacion"));
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar libro");
            return null;
        }
    }

    public static Libro buscarLibroPorNombre(Connection conn, String nombre){
        String sql = "SELECT * FROM libro WHERE titulo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Libro(rs.getInt("numero"),rs.getString("titulo"),rs.getString("clasificacion"));
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro");
            return null;
        }
    }

    public static ArrayList buscarNombreLibro(Connection conn){
        ArrayList<String> titulos = new ArrayList<>();
        String sql = "SELECT titulo FROM libro;";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                titulos.add(rs.getString("titulo"));
            }
            return titulos;
        } catch (SQLException e) {
            System.out.println("Error al buscar titulos del libros");
            return null;
        }
    }


}
