package duvanfisi.fisiunmsm.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class CPersonaje implements Parcelable, Comparable<CPersonaje> {

    private String photo;
    private String names;
    private String descripcion;
    private int valoracion;
    private String fecha_nac;
    private String ciudad_natal;

    private int favs;
    private int likes;
    private int loves;
    private int dislikes;

    private String bio_first;
    private String bio_second;
    private String bio_third;
    private String bio_four;

    public CPersonaje(){

    }


    protected CPersonaje(Parcel in) {
        photo = in.readString();
        names = in.readString();
        descripcion = in.readString();
        valoracion = in.readInt();
        fecha_nac = in.readString();
        ciudad_natal = in.readString();
        favs = in.readInt();
        likes = in.readInt();
        loves = in.readInt();
        dislikes = in.readInt();
        bio_first = in.readString();
        bio_second = in.readString();
        bio_third = in.readString();
        bio_four = in.readString();
    }

    public static final Creator<CPersonaje> CREATOR = new Creator<CPersonaje>() {
        @Override
        public CPersonaje createFromParcel(Parcel in) {
            return new CPersonaje(in);
        }

        @Override
        public CPersonaje[] newArray(int size) {
            return new CPersonaje[size];
        }
    };

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public static Creator<CPersonaje> getCREATOR() {
        return CREATOR;
    }

    public String getCiudad_natal() {
        return ciudad_natal;
    }

    public int getFavs() {
        return favs;
    }

    public void setFavs(int favs) {
        this.favs = favs;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLoves() {
        return loves;
    }

    public void setLoves(int loves) {
        this.loves = loves;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setCiudad_natal(String ciudad_natal) {
        this.ciudad_natal = ciudad_natal;
    }

    public String getBio_first() {
        return bio_first;
    }

    public void setBio_first(String bio_first) {
        this.bio_first = bio_first;
    }

    public String getBio_second() {
        return bio_second;
    }

    public void setBio_second(String bio_second) {
        this.bio_second = bio_second;
    }

    public String getBio_third() {
        return bio_third;
    }

    public void setBio_third(String bio_third) {
        this.bio_third = bio_third;
    }

    public String getBio_four() {
        return bio_four;
    }

    public void setBio_four(String bio_four) {
        this.bio_four = bio_four;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo);
        dest.writeString(names);
        dest.writeString(descripcion);
        dest.writeInt(valoracion);
        dest.writeString(fecha_nac);
        dest.writeString(ciudad_natal);
        dest.writeInt(favs);
        dest.writeInt(likes);
        dest.writeInt(loves);
        dest.writeInt(dislikes);
        dest.writeString(bio_first);
        dest.writeString(bio_second);
        dest.writeString(bio_third);
        dest.writeString(bio_four);
    }

    @Override
    public int compareTo(CPersonaje o) {
        return 0;
    }
}
