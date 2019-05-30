package duvanfisi.fisiunmsm.Model;

import java.util.ArrayList;

public class CPiso {

    private int _idsede;
    private int _idcomida;
    private int total_pisos;

    private ArrayList<CTurno> turnos;

    public CPiso(){

    }

    public ArrayList<CTurno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<CTurno> turnos) {
        this.turnos = turnos;
    }

    public int get_idsede() {
        return _idsede;
    }

    public void set_idsede(int _idsede) {
        this._idsede = _idsede;
    }

    public int get_idcomida() {
        return _idcomida;
    }

    public void set_idcomida(int _idcomida) {
        this._idcomida = _idcomida;
    }

    public int getTotal_pisos() {
        return total_pisos;
    }

    public void setTotal_pisos(int total_pisos) {
        this.total_pisos = total_pisos;
    }
}
