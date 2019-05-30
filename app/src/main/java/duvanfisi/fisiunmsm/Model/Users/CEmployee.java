package duvanfisi.fisiunmsm.Model.Users;

public class CEmployee extends CUser {
    private String occupation;

    public CEmployee(String email, String names, String last_name_p, String last_name_m,
                     String user_type, String occupation){

        super(email, names, last_name_p, last_name_m, user_type);
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
