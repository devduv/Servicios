package duvanfisi.fisiunmsm.Model;

import android.os.Parcel;
import android.os.Parcelable;

import duvanfisi.fisiunmsm.Model.Users.CStudent;

public class CTicket implements Parcelable {

    private String _id;

    private int _id_comida;
    private int _id_sede;
    private int _id_piso;

    private int _id_turno;
    private String horario_ini;
    private String horario_fin;

    private String fecha_retiro;
    private String hora_retiro;
    private String hora_consumido;

    private boolean consumido;


    private CStudent user;

    public CTicket(){

    }

    public CTicket(int _id_comida, int _id_sede, int _id_piso, int _id_turno,
                   String hora_ini, String hora_fin, CStudent user) {
        this._id_comida = _id_comida;
        this._id_sede = _id_sede;
        this._id_piso = _id_piso;
        this._id_turno = _id_turno;
        this.horario_ini = hora_ini;
        this.horario_fin = hora_fin;
        this.consumido = false;
        this.user = user;
    }

    protected CTicket(Parcel in) {
        _id = in.readString();
        _id_comida = in.readInt();
        _id_sede = in.readInt();
        _id_piso = in.readInt();
        _id_turno = in.readInt();
        horario_ini = in.readString();
        horario_fin = in.readString();
        fecha_retiro = in.readString();
        hora_retiro = in.readString();
        hora_consumido = in.readString();
        consumido = in.readByte() != 0;
        user = in.readParcelable(CStudent.class.getClassLoader());
    }

    public static final Creator<CTicket> CREATOR = new Creator<CTicket>() {
        @Override
        public CTicket createFromParcel(Parcel in) {
            return new CTicket(in);
        }

        @Override
        public CTicket[] newArray(int size) {
            return new CTicket[size];
        }
    };

    public void key_ticket(String fecha){
        String one = Integer.toString(this._id_comida); //1 1
        String two = Integer.toString(this._id_sede);   //1 1
        String three = Integer.toString(this._id_piso); //2 2
        String four = Integer.toString(this._id_turno); //3 1
        String five = user.get_id();    //16200049

        String fecha_six[] = fecha.split("/");

        String six = one + two + three + four + five + fecha_six[0] + fecha_six[1] +fecha_six[2];


       set_id(six);

    }

    public CStudent getUser() {
        return user;
    }

    public void setUser(CStudent user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get_id_comida() {
        return _id_comida;
    }

    public void set_id_comida(int _id_comida) {
        this._id_comida = _id_comida;
    }

    public int get_id_sede() {
        return _id_sede;
    }

    public void set_id_sede(int _id_sede) {
        this._id_sede = _id_sede;
    }

    public int get_id_piso() {
        return _id_piso;
    }

    public void set_id_piso(int _id_piso) {
        this._id_piso = _id_piso;
    }

    public int get_id_turno() {
        return _id_turno;
    }

    public void set_id_turno(int _id_turno) {
        this._id_turno = _id_turno;
    }

    public String getHorario_ini() {
        return horario_ini;
    }

    public void setHorario_ini(String horario_ini) {
        this.horario_ini = horario_ini;
    }

    public String getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(String horario_fin) {
        this.horario_fin = horario_fin;
    }

    public String getFecha_retiro() {
        return fecha_retiro;
    }

    public void setFecha_retiro(String fecha_retiro) {
        this.fecha_retiro = fecha_retiro;
    }

    public String getHora_retiro() {
        return hora_retiro;
    }

    public void setHora_retiro(String hora_retiro) {
        this.hora_retiro = hora_retiro;
    }

    public String getHora_consumido() {
        return hora_consumido;
    }

    public void setHora_consumido(String hora_consumido) {
        this.hora_consumido = hora_consumido;
    }

    public boolean isConsumido() {
        return consumido;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeInt(_id_comida);
        dest.writeInt(_id_sede);
        dest.writeInt(_id_piso);
        dest.writeInt(_id_turno);
        dest.writeString(horario_ini);
        dest.writeString(horario_fin);
        dest.writeString(fecha_retiro);
        dest.writeString(hora_retiro);
        dest.writeString(hora_consumido);
        dest.writeByte((byte) (consumido ? 1 : 0));
        dest.writeParcelable(user, flags);
    }
}
