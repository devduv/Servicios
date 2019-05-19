package duvanfisi.fisiunmsm.Modelo;

import java.util.ArrayList;

public class CFacultad {
    private int _id;
    private int cantidadesc;
    private String nombre;
    private String photo;
    private String sobrenombre;
    private ArrayList<CEscuela> escuelas = new ArrayList<>();

    public CFacultad(){

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCantidadesc() {
        return cantidadesc;
    }

    public void setCantidadesc(int cantidadesc) {
        this.cantidadesc = cantidadesc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSobrenombre() {
        return sobrenombre;
    }

    public void setSobrenombre(String sobrenombre) {
        this.sobrenombre = sobrenombre;
    }

    public ArrayList<CEscuela> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(ArrayList<CEscuela> escuelas) {
        this.escuelas = escuelas;
    }
}
