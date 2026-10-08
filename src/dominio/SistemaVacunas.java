package dominio;
import java.util.ArrayList;
import java.util.List;
import dao.VacunaDAO;

public class SistemaVacunas {
    
 private VacunaDAO VacunaDAO = new VacunaDAO();

    public int guardar(vacunas vacuna) {
        try {
            return VacunaDAO.guardar(vacuna);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<vacunas> listar() {
        try {
            return VacunaDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(vacunas vacuna) {
        try {
            return VacunaDAO.actualizar(vacuna);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return VacunaDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }















































}
