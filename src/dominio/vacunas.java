package dominio;
public class vacunas {

    private int id_vacunas;
    private String tipo;
    private String fecha;
    private String hora;
    private String vencimiento;
    private int id_mascota;
    private int id_consulta;
    private String nombre;

    public vacunas(int id_vacunas, String tipo, String fecha, String hora, String vencimiento) {
        this.id_vacunas = id_vacunas;
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.vencimiento = vencimiento;

    }

    public int getId_vacunas() {
        return id_vacunas;
    }

    public void setId_vacunas(int id_vacunas) {
        this.id_vacunas = id_vacunas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }
 public String getNombre() {
        return nombre;
    }
}