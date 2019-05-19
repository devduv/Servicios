package duvanfisi.fisiunmsm.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class CUsuario implements Parcelable {
    //importante
    private String email;
    private String password;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private int edad;
    private String fechanac;
    private int codigo;
    private String phone;
    private String nickname;
    private String color;
    private String photo;

    //otros
    private String sede;
    private String escuela;
    private String facultad;
    private String tipo_usuario;

    //registro
    private int t_retirados;
    private int t_consumidos;

    //inicio de sesion
    private String ult_conexion;

    private boolean ticketon;

    public CUsuario(){

    }

    public CUsuario(String email, int codigo, String ap_paterno, String ap_materno, String nombres) {
        this.phone = "null";
        this.email = email;
        this.codigo = codigo;
        this.ap_paterno = ap_paterno;
        this.ap_materno = ap_materno;
        this.nombre = nombres;
        this.color = "null";
        this.photo = "null";
        this.nickname = "null";
        this.t_consumidos = 0;
        this.t_retirados = 0;
        this.tipo_usuario = "normal";
        this.facultad = "null";
        this.escuela = "null";
        this.ult_conexion = "no";
        this.sede = "Ciudad Universitaria";
        this.fechanac = "null";
        this.ticketon = false;
    }

    protected CUsuario(Parcel in) {
        email = in.readString();
        password = in.readString();
        nombre = in.readString();
        ap_paterno = in.readString();
        ap_materno = in.readString();
        edad = in.readInt();
        fechanac = in.readString();
        codigo = in.readInt();
        phone = in.readString();
        nickname = in.readString();
        color = in.readString();
        photo = in.readString();
        sede = in.readString();
        escuela = in.readString();
        facultad = in.readString();
        tipo_usuario = in.readString();
        t_retirados = in.readInt();
        t_consumidos = in.readInt();
        ult_conexion = in.readString();
        ticketon = in.readByte() != 0;
    }

    public static final Creator<CUsuario> CREATOR = new Creator<CUsuario>() {
        @Override
        public CUsuario createFromParcel(Parcel in) {
            return new CUsuario(in);
        }

        @Override
        public CUsuario[] newArray(int size) {
            return new CUsuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getT_retirados() {
        return t_retirados;
    }

    public void setT_retirados(int t_retirados) {
        this.t_retirados = t_retirados;
    }

    public int getT_consumidos() {
        return t_consumidos;
    }

    public void setT_consumidos(int t_consumidos) {
        this.t_consumidos = t_consumidos;
    }

    public String getUlt_conexion() {
        return ult_conexion;
    }

    public void setUlt_conexion(String ult_conexion) {
        this.ult_conexion = ult_conexion;
    }

    public boolean isTicketon() {
        return ticketon;
    }

    public void setTicketon(boolean ticketon) {
        this.ticketon = ticketon;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(nombre);
        dest.writeString(ap_paterno);
        dest.writeString(ap_materno);
        dest.writeInt(edad);
        dest.writeString(fechanac);
        dest.writeInt(codigo);
        dest.writeString(phone);
        dest.writeString(nickname);
        dest.writeString(color);
        dest.writeString(photo);
        dest.writeString(sede);
        dest.writeString(escuela);
        dest.writeString(facultad);
        dest.writeString(tipo_usuario);
        dest.writeInt(t_retirados);
        dest.writeInt(t_consumidos);
        dest.writeString(ult_conexion);
        dest.writeByte((byte) (ticketon ? 1 : 0));
    }
}
