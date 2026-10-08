package dominio;
public class Dueño {

    private int id_dueño;
    private String nombre;
    private String cedula;
    private String direccion;
    private String telefono;
    private String email;

    public Dueño(int id_dueño, String nombre, String cedula, String direccion, String telefono, String email) {
        this.id_dueño = id_dueño;
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_dueño() {
        return id_dueño;
    }

    public void setId_dueño(int id_dueño) {
        this.id_dueño = id_dueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}