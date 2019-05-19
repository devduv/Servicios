package duvanfisi.fisiunmsm.Modelo;

public class CTurno {

    private int _id;
    private int cantidad;
    private String horario_fin;
    private String horario_ini;


    public CTurno(){

    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(String horario_fin) {
        this.horario_fin = horario_fin;
    }

    public String getHorario_ini() {
        return horario_ini;
    }

    public void setHorario_ini(String horario_ini) {
        this.horario_ini = horario_ini;
    }
}
