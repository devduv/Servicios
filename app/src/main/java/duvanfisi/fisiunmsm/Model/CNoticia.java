package duvanfisi.fisiunmsm.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CNoticia implements Parcelable {

    private int id;
    private String link_foto;
    private String titulo;
    private String fecha;
    private String descripcion_1;
    private String descripcion_2;
    private String descripcion_3;
    private String descripcion_4;
    private String descripcion_5;

    public CNoticia(){

    }

    protected CNoticia(Parcel in) {
        id = in.readInt();
        link_foto = in.readString();
        titulo = in.readString();
        fecha = in.readString();
        descripcion_1 = in.readString();
        descripcion_2 = in.readString();
        descripcion_3 = in.readString();
        descripcion_4 = in.readString();
        descripcion_5 = in.readString();
    }

    public static final Creator<CNoticia> CREATOR = new Creator<CNoticia>() {
        @Override
        public CNoticia createFromParcel(Parcel in) {
            return new CNoticia(in);
        }

        @Override
        public CNoticia[] newArray(int size) {
            return new CNoticia[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink_foto() {
        return link_foto;
    }

    public void setLink_foto(String link_foto) {
        this.link_foto = link_foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion_1() {
        return descripcion_1;
    }

    public void setDescripcion_1(String descripcion_1) {
        this.descripcion_1 = descripcion_1;
    }

    public String getDescripcion_2() {
        return descripcion_2;
    }

    public void setDescripcion_2(String descripcion_2) {
        this.descripcion_2 = descripcion_2;
    }

    public String getDescripcion_3() {
        return descripcion_3;
    }

    public void setDescripcion_3(String descripcion_3) {
        this.descripcion_3 = descripcion_3;
    }

    public String getDescripcion_4() {
        return descripcion_4;
    }

    public void setDescripcion_4(String descripcion_4) {
        this.descripcion_4 = descripcion_4;
    }

    public String getDescripcion_5() {
        return descripcion_5;
    }

    public void setDescripcion_5(String descripcion_5) {
        this.descripcion_5 = descripcion_5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(link_foto);
        dest.writeString(titulo);
        dest.writeString(fecha);
        dest.writeString(descripcion_1);
        dest.writeString(descripcion_2);
        dest.writeString(descripcion_3);
        dest.writeString(descripcion_4);
        dest.writeString(descripcion_5);
    }
}
