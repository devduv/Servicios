package duvanfisi.fisiunmsm.Modelo.Users;

import java.util.HashMap;
import java.util.Map;

public class CUser {
    private String _id;
    private String email;

    private String names;
    private String last_name_p;
    private String last_name_m;
    private String birthdate;
    private String phonenumber;

    private String user_type;

    private String headquarters;
    private String professional_school;
    private String faculty;


    private String last_connection;

    private boolean show_location;

    private Map<String, Object> latlong;


    public CUser(){
        CUser user = new CUser();
        user.setUser_type("transpinterno");

//user.setTypeUser(“student”);

    }
    public CUser(String email, String names, String last_name_p, String last_name_m, String user_type){
        this.email = email;
        this.names = names;
        this.last_name_p = last_name_p;
        this.last_name_m = last_name_m;
        this.user_type = user_type;
        this.show_location = false;
        latlong = new HashMap<String, Object>();
    }

    public String get_id() {
        return _id;
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

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast_name_p() {
        return last_name_p;
    }

    public void setLast_name_p(String last_name_p) {
        this.last_name_p = last_name_p;
    }

    public String getLast_name_m() {
        return last_name_m;
    }

    public void setLast_name_m(String last_name_m) {
        this.last_name_m = last_name_m;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
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
}
