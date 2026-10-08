package dominio;
public class Mascota {

    private int id_mascota;
    private String nombre;
    private String edad;
    private String especie;
    private String raza;
    private String genero;
    private int peso;
    private int id_dueño;

    public Mascota(int id_mascota, String nombre, String edad, String especie, String raza, String genero, int peso) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.edad = edad;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        this.peso = peso;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getId_dueño() {
        return id_dueño;
    }

    public void setId_dueño(int id_dueño) {
        this.id_dueño = id_dueño;
    }

}