    package dao;
    import conexion.Conexion;
    import dominio.vacunas;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;




    public class VacunaDAO {

        private void asegurarTabla(Connection cn) throws SQLException {
            String sql = "CREATE TABLE IF NOT EXISTS vacunas ("
                    + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "tipo VARCHAR(100) NOT NULL, "
                    + "fecha VARCHAR(30), "
                    + "hora VARCHAR(20), "
                    + "vencimiento VARCHAR(30)"
                    + ")";

            try (Statement statement = cn.createStatement()) {
                statement.executeUpdate(sql);
            }
        }

        /**
         * Inserta un vacuna nuevo.
         *
         * @return el id que asigno MySQL, o -1 si no se inserto nada.
         */
        public int guardar(vacunas vacuna) throws SQLException {
             String sql = "INSERT INTO vacunas (tipo, fecha, hora, vencimiento) VALUES (?, ?, ?, ?)";

            try (Connection cn = Conexion.getConexion()) {
                 asegurarTabla(cn);
                 try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                     ps.setString(1, vacuna.getTipo());
                     ps.setString(2, vacuna.getFecha());
                     ps.setString(3, vacuna.getHora());
                     ps.setString(4, vacuna.getVencimiento());

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
        }

        /**
         * Devuelve todos los duenos guardados, ordenados por nombre.
         */
        public List<vacunas> listar() throws SQLException {
            String sql = "SELECT id, tipo, fecha, hora, vencimiento FROM vacunas ORDER BY tipo";
            List<vacunas> lista = new ArrayList<>();

            try (Connection cn = Conexion.getConexion()) {
                asegurarTabla(cn);
                try (PreparedStatement ps = cn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new vacunas(
                                rs.getInt("id"),
                                rs.getString("tipo"),
                                rs.getString("fecha"),
                                rs.getString("hora"),
                                rs.getString("vencimiento")));
                    }
                }
            }
            return lista;
        }

        /**
         * Actualiza los datos de un vacuna ya existente (usando su id).
         *
         * @return true si se actualizo algun registro.
         */
        public boolean actualizar(vacunas vacuna) throws SQLException {
            String sql = "UPDATE vacunas SET tipo = ?, fecha = ?, hora = ?, vencimiento = ? WHERE id = ?";

            try (Connection cn = Conexion.getConexion()) {
                asegurarTabla(cn);
                try (PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setString(1, vacuna.getTipo());
                    ps.setString(2, vacuna.getFecha());
                    ps.setString(3, vacuna.getHora());
                    ps.setString(4, vacuna.getVencimiento());
                    ps.setInt(5, vacuna.getId_vacunas());

                    return ps.executeUpdate() > 0;
                }
            }
        }

        /**
         * Elimina un vacuna por su id.
         *
         * @return true si se elimino algun registro.
         */
        public boolean eliminar(int id) throws SQLException {
            String sql = "DELETE FROM vacunas WHERE id = ?";

            try (Connection cn = Conexion.getConexion()) {
                asegurarTabla(cn);
                try (PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    return ps.executeUpdate() > 0;
                }
            }
        }



























































}
