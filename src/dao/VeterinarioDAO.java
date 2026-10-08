package dao;

import conexion.Conexion;
import dominio.Veterinario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    private void asegurarTabla(Connection cn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS veterinarios ("
                + "id_veterinario INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(150) NOT NULL, "
                + "password VARCHAR(255) NOT NULL, "
                + "especialidad VARCHAR(150) NOT NULL"
                + ")";

        try (Statement statement = cn.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public int guardar(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinarios (nombre, password, especialidad) VALUES (?, ?, ?)";

        try (Connection cn = Conexion.getConexion()) {
            asegurarTabla(cn);
            try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, veterinario.getNombre());
                ps.setString(2, veterinario.getPassword());
                ps.setString(3, veterinario.getEspecialidad());

                int filas = ps.executeUpdate();
                if (filas == 0) {
                    return -1;
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return rs.next() ? rs.getInt(1) : -1;
                }
            }
        }
    }

    public List<Veterinario> listar() throws SQLException {
        String sql = "SELECT id_veterinario, nombre, password, especialidad "
                + "FROM veterinarios ORDER BY nombre";
        List<Veterinario> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion()) {
            asegurarTabla(cn);
            try (PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Veterinario(
                            rs.getInt("id_veterinario"),
                            rs.getString("nombre"),
                            rs.getString("password"),
                            rs.getString("especialidad")));
                }
            }
        }
        return lista;
    }

    public boolean actualizar(Veterinario veterinario) throws SQLException {
        String sql = "UPDATE veterinarios "
                + "SET nombre = ?, password = ?, especialidad = ? "
                + "WHERE id_veterinario = ?";

        try (Connection cn = Conexion.getConexion()) {
            asegurarTabla(cn);
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, veterinario.getNombre());
                ps.setString(2, veterinario.getPassword());
                ps.setString(3, veterinario.getEspecialidad());
                ps.setInt(4, veterinario.getId_veterinario());
                return ps.executeUpdate() > 0;
            }
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM veterinarios WHERE id_veterinario = ?";

        try (Connection cn = Conexion.getConexion()) {
            asegurarTabla(cn);
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        }
    }
}
