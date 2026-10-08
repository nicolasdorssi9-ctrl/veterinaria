package dao;
import conexion.Conexion;
import dominio.Mascota;
import java.sql.*;
import java.util.ArrayList;

public class MascotaDAO {

    /**
     * Inserta una mascota nueva.
     * @return el id que asigno MySQL, o -1 si no se inserto nada.
     */
    public int guardar(Mascota mascota) throws SQLException {
        String sql = "INSERT INTO mascota (nombre, edad, especie, raza, genero, peso, id_dueño) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEdad());
            ps.setString(3, mascota.getEspecie());
            ps.setString(4, mascota.getRaza());
            ps.setString(5, mascota.getGenero());
            ps.setInt(6, mascota.getPeso());
            ps.setInt(7, mascota.getId_dueño());

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

    /** Devuelve todas las mascotas guardadas, ordenadas por nombre. */
    public ArrayList<Mascota> listar() throws SQLException {
        String sql = "SELECT id_mascota, nombre, edad, especie, raza, genero, peso, id_dueño "
                + "FROM mascota ORDER BY nombre";
        ArrayList<Mascota> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mascota mascota = new Mascota(
                        rs.getInt("id_mascota"),
                        rs.getString("nombre"),
                        rs.getString("edad"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("genero"),
                        rs.getInt("peso"));
                mascota.setId_dueño(rs.getInt("id_dueño"));
                lista.add(mascota);
            }
        }
        return lista;
    }

    /** Actualiza los datos de una mascota existente usando su id. */
    public boolean actualizar(Mascota mascota) throws SQLException {
        String sql = "UPDATE mascota SET nombre = ?, edad = ?, especie = ?, raza = ?, genero = ?, peso = ?, id_dueño = ? "
                + "WHERE id_mascota = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEdad());
            ps.setString(3, mascota.getEspecie());
            ps.setString(4, mascota.getRaza());
            ps.setString(5, mascota.getGenero());
            ps.setInt(6, mascota.getPeso());
            ps.setInt(7, mascota.getId_dueño());
            ps.setInt(8, mascota.getId_mascota());

            return ps.executeUpdate() > 0;
        }
    }

    /** Elimina una mascota por su id. */
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM mascota WHERE id_mascota = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
