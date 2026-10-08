package dao;

import conexion.Conexion;
import dominio.Dueño;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) de la tabla "duenos".
 * Recibe objetos dueño (del paquete dominio) y los guarda,
 * actualiza, elimina o lee desde MySQL usando la clase Conexion.
 */
public class DueñoDAO {

    /**
     * Inserta un dueño nuevo.
     *
     * @return el id que asigno MySQL, o -1 si no se inserto nada.
     */
    public int guardar(Dueño dueño) throws SQLException {
        String sql = "INSERT INTO dueño (nombre, cedula, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dueño.getNombre());
            ps.setString(2, dueño.getCedula());
            ps.setString(3, dueño.getEmail());
            ps.setString(4, dueño.getTelefono());
            ps.setString(5, dueño.getDireccion());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        }
    }

    /**
     * Devuelve todos los duenos guardados, ordenados por nombre.
     */
    public  ArrayList<Dueño> listar() throws SQLException {
        String sql = "SELECT id_dueño, nombre, cedula, email, telefono, direccion FROM dueño ORDER BY nombre";
        ArrayList<Dueño> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Dueño(
                        rs.getInt("id_dueño"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }
        }
        return lista;
    }

    /**
     * Actualiza los datos de un dueño ya existente (usando su id).
     *
     * @return true si se actualizo algun registro.
     */
    public boolean actualizar(Dueño dueño) throws SQLException {
        String sql = "UPDATE dueño SET nombre = ?, cedula = ?, email = ?, telefono = ?, direccion = ? WHERE id_dueño = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dueño.getNombre());
            ps.setString(2, dueño.getCedula());
            ps.setString(3, dueño.getEmail());
            ps.setString(4, dueño.getTelefono());
            ps.setString(5, dueño.getDireccion());
            ps.setInt(6, dueño.getId_dueño());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un dueño por su id.
     *
     * @return true si se elimino algun registro.
     */
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM dueño WHERE id_dueño = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
