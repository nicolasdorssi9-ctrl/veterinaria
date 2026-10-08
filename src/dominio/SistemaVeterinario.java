package dominio;
import java.util.ArrayList;
import java.util.List;
import dao.VeterinarioDAO;

public class SistemaVeterinario {
    
private VeterinarioDAO VeterinarioDAO = new VeterinarioDAO();

    public int guardar(Veterinario veterinario) {
        try {
            return VeterinarioDAO.guardar(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Veterinario> listar() {
        try {
            return VeterinarioDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(Veterinario veterinario) {
        try {
            return VeterinarioDAO.actualizar(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return VeterinarioDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }































































}
