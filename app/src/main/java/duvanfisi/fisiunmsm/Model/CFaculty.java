package duvanfisi.fisiunmsm.Model;

import java.util.ArrayList;

public class CFaculty {
    private int _id;
    private int cantidadesc;
    private String nombre;
    private String photo;
    private String sobrenombre;
    private boolean flag;
    private ArrayList<CEscuela> escuelas = new ArrayList<>();

    public CFaculty(){
        //flag = false;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
