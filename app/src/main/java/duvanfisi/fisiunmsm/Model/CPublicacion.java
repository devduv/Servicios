package duvanfisi.fisiunmsm.Model;

public class CPublicacion {
    private int _id;
    private int cod;
    private String description;
    private String email;
    private String hora;
    private String fecha;
    private String nombre_user;
    private String photo_user;
    private String photo_pub;
    private String tipo;
    private String titulo;
    private String date_user;

    public CPublicacion(){

    }

    public int get_id() {
        return _id;
    }

    public String getDate_user() {
        return date_user;
    }

    public void setDate_user(String date_user) {
        this.date_user = date_user;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public String getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public String getPhoto_pub() {
        return photo_pub;
    }

    public void setPhoto_pub(String photo_pub) {
        this.photo_pub = photo_pub;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
