package duvanfisi.fisiunmsm.Model.Users;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public abstract class CUser implements Parcelable{
    private String _id;
    private String email;

    private String names;
    private String lastname_p;
    private String lastname_m;
    private String phonenumber;

    private String user_type;
    private String nick;
    private String photo;
    private String color;

    private String host_central;
    private String professional_school;
    private String faculty;


    private String last_connection;

    private boolean show_location;

    private Map<String, Object> latlong;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNames() {
        return names;
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

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastname_p() {
        return lastname_p;
    }

    public void setLastname_p(String lastname_p) {
        this.lastname_p = lastname_p;
    }

    public String getLastname_m() {
        return lastname_m;
    }

    public void setLastname_m(String lastname_m) {
        this.lastname_m = lastname_m;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHost_central() {
        return host_central;
    }

    public void setHost_central(String host_central) {
        this.host_central = host_central;
    }

    public String getProfessional_school() {
        return professional_school;
    }

    public void setProfessional_school(String professional_school) {
        this.professional_school = professional_school;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLast_connection() {
        return last_connection;
    }

    public void setLast_connection(String last_connection) {
        this.last_connection = last_connection;
    }

    public boolean isShow_location() {
        return show_location;
    }

    public void setShow_location(boolean show_location) {
        this.show_location = show_location;
    }

    public Map<String, Object> getLatlong() {
        return latlong;
    }

    public void setLatlong(Map<String, Object> latlong) {
        this.latlong = latlong;
    }

    public CUser(String _id, String email, String names, String last_name_p, String last_name_m, String user_type){
        this._id= _id;
        this.email = email;

        this.names = names;
        this.lastname_p = last_name_p;
        this.lastname_m = last_name_m;
        this.user_type = user_type;
        this.show_location = false;

        this.faculty = "null";
        this.professional_school = "null";
        this.host_central = "Ciudad Universitaria (CU)";
        this.nick = "null";
        this.phonenumber = "null";
        this.photo = "null";
        this.color= "null";
        latlong = new HashMap<String, Object>();
    }

    public CUser(){}

    protected CUser(Parcel in) {
        _id = in.readString();
        email = in.readString();
        names = in.readString();
        lastname_p = in.readString();
        lastname_m = in.readString();
        phonenumber = in.readString();
        user_type = in.readString();
        nick = in.readString();
        color = in.readString();
        photo = in.readString();
        host_central = in.readString();
        professional_school = in.readString();
        faculty = in.readString();
        last_connection = in.readString();
        show_location = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(email);
        dest.writeString(names);
        dest.writeString(lastname_p);
        dest.writeString(lastname_m);
        dest.writeString(phonenumber);
        dest.writeString(user_type);
        dest.writeString(nick);
        dest.writeString(color);
        dest.writeString(photo);
        dest.writeString(host_central);
        dest.writeString(professional_school);
        dest.writeString(faculty);
        dest.writeString(last_connection);
        dest.writeByte((byte) (show_location ? 1 : 0));
    }
}
