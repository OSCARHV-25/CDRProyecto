package cdr_proyecto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {
    public static Connection getConexion() throws SQLException {
        //Nombre de la base de datos
        String baseDatos = "proyecto";
        String url = "jdbc:mysql://localhost:3308/" + baseDatos;
        String usuario = "root";
        String password = "";

        try {
            return DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
            throw e; // se imprime un mensaje de error y luego se relanza la excepción con throw e
        }
    }

    //Main de prueba para comprobar que si se hizo la conexión a la base de datos
    public static void main(String[] args) throws SQLException {
        var conexion = ConexionBd.getConexion();
        if(conexion != null)
            System.out.println("Conexion exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}
