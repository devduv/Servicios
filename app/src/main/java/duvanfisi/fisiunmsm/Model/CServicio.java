package duvanfisi.fisiunmsm.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CServicio implements Parcelable {
    private String nombre;
    private String photo;
    private String Adicional;
    private String informacion;

    public CServicio(){

    }


    protected CServicio(Parcel in) {
        nombre = in.readString();
        photo = in.readString();
        Adicional = in.readString();
        informacion = in.readString();
    }

    public static final Creator<CServicio> CREATOR = new Creator<CServicio>() {
        @Override
        public CServicio createFromParcel(Parcel in) {
            return new CServicio(in);
        }

        @Override
        public CServicio[] newArray(int size) {
            return new CServicio[size];
        }
    };

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

    public String getAdicional() {
        return Adicional;
    }

    public void setAdicional(String adicional) {
        Adicional = adicional;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(photo);
        dest.writeString(Adicional);
        dest.writeString(informacion);
    }
}
