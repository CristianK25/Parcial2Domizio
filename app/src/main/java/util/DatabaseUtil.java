package util;

import java.sql.*;

@SuppressWarnings("ALL")
public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parcial_1_domizio";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }

    public static void initDatabase(Connection conn) {
        String initSqlLibro = "CREATE TABLE IF NOT EXISTS libro(" +
                "numero INT UNIQUE PRIMARY KEY,"+
                "titulo VARCHAR(255) NOT NULL," +
                "clasificacion VARCHAR(100));";

        String initSqlPersona = "CREATE TABLE IF NOT EXISTS persona(" +
                "dni INT PRIMARY KEY," +
                "nombre VARCHAR(255) NOT NULL);";

        String initSqlPrestamo = "CREATE TABLE IF NOT EXISTS prestamo(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "dia_prestamo DATE," +
                "devolucion DATE," +
                "persona_dni INT," +
                "id_libro INT," +
                "FOREIGN KEY (persona_dni) REFERENCES persona(dni)," +
                "FOREIGN KEY (id_libro) REFERENCES libro(numero));";
        String initSqlPrestamoLibro = "CREATE TABLE IF NOT EXISTS prestamo_libro("
                + "idPrestamo INT,"
                + "numeroLibro INT,"
                + "PRIMARY KEY (idPrestamo,idLibro,"
                + "FOREIGN KEY(idPrestamo) REFERENCES persona(dni),"
                + "FOREIGN KEY(numeroLibro) REFERENCES libro(numero);"
                + ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(initSqlPersona);
            stmt.executeUpdate(initSqlLibro);
            stmt.executeUpdate(initSqlPrestamo);
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas.");
        }
    }
}
