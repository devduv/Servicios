package duvanfisi.fisiunmsm.Model.Users;

public class CTeacher extends CUser{

    private String code_teacher;
    private float amount;

    private int tickets_withdrawn;
    private int tickets_consumed;

    private boolean ticket_lunch;
    private boolean ticket_dinner;

    public CTeacher(String email, String names, String last_name_p, String last_name_m,
                    String user_type, String code_teacher){
        super(email, names, last_name_p, last_name_m, user_type);
        this.ticket_dinner = false;
        this.ticket_lunch = false;
        this.tickets_consumed = 0;
        this.tickets_withdrawn = 0;
        this.amount = 0;
        this.code_teacher = code_teacher;
    }

    public String getCode_teacher() {
        return code_teacher;
    }

    public void setCode_teacher(String code_teacher) {
        this.code_teacher = code_teacher;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getTickets_withdrawn() {
        return tickets_withdrawn;
    }

    public void setTickets_withdrawn(int tickets_withdrawn) {
        this.tickets_withdrawn = tickets_withdrawn;
    }

    public int getTickets_consumed() {
        return tickets_consumed;
    }

    public void setTickets_consumed(int tickets_consumed) {
        this.tickets_consumed = tickets_consumed;
    }

    public boolean isTicket_lunch() {
        return ticket_lunch;
    }

    public void setTicket_lunch(boolean ticket_lunch) {
        this.ticket_lunch = ticket_lunch;
    }

    public boolean isTicket_dinner() {
        return ticket_dinner;
    }

    public void setTicket_dinner(boolean ticket_dinner) {
        this.ticket_dinner = ticket_dinner;
    }
}
