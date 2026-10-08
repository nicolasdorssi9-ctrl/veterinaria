package dominio;

import java.util.ArrayList;
import dao.DueñoDAO;

public class SistemaDueño {
   private DueñoDAO DueñoDAO = new DueñoDAO();

    public int guardar(Dueño dueño) {
        try {
            return DueñoDAO.guardar(dueño);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Dueño> listar() {
        try {
            return DueñoDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(Dueño dueño) {
        try {
            return DueñoDAO.actualizar(dueño);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return DueñoDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }







}
