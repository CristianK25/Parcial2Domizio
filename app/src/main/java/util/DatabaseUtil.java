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
                "FOREIGN KEY (persona_dni) REFERENCES persona(dni));";
        String initSqlPrestamoLibro = "CREATE TABLE IF NOT EXISTS prestamo_libro("
                + "idPrestamo INT,"
                + "numeroLibro INT,"
                + "PRIMARY KEY (idPrestamo,numeroLibro),"
                + "FOREIGN KEY (idPrestamo) REFERENCES prestamo(id),"
                + "FOREIGN KEY (numeroLibro) REFERENCES libro(numero)"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(initSqlPersona);
            stmt.executeUpdate(initSqlLibro);
            stmt.executeUpdate(initSqlPrestamo);
            stmt.executeUpdate(initSqlPrestamoLibro);
            insertLibrosEjemplo(conn);
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas.");
        }
    }
    
    public static void insertLibrosEjemplo(Connection conn) {
    String[][] librosAInsertar = {
        {"1", "Cien años de soledad", "Novela"},
        {"2", "1984", "Ciencia ficción"},
        {"3", "El Principito", "Literatura infantil"},
        {"4", "Don Quijote de la Mancha", "Clásico"},
        {"5", "Orgullo y prejuicio", "Romance"},
        {"6", "Crónica de una muerte anunciada", "Novela"},
        {"7", "El señor de los anillos", "Fantasía"},
        {"8", "Harry Potter y la piedra filosofal", "Fantasía juvenil"},
        {"9", "La sombra del viento", "Misterio"},
        {"10", "Rayuela", "Novela experimental"},
        {"11", "Ficciones", "Cuentos"},
        {"12", "El amor en los tiempos del cólera", "Novela romántica"},
        {"13", "Los juegos del hambre", "Ciencia ficción juvenil"},
        {"14", "El código Da Vinci", "Suspenso"},
        {"15", "Drácula", "Terror"}
    };

    String sql = "INSERT IGNORE INTO libro (numero, titulo, clasificacion) VALUES (?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        for (String[] libro : librosAInsertar) {
            pstmt.setInt(1, Integer.parseInt(libro[0]));  // numero
            pstmt.setString(2, libro[1]);  // titulo
            pstmt.setString(3, libro[2]);  // clasificacion
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        System.out.println("Libros de ejemplo insertados correctamente.");
    } catch (SQLException e) {
        System.out.println("Error al insertar libros de ejemplo: " + e.getMessage());
    }
}
}
