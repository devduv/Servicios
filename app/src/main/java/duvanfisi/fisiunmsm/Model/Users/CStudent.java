package duvanfisi.fisiunmsm.Model.Users;

public class CStudent extends CUser{
    private int tickets_withdrawn;
    private int tickets_consumed;
    private boolean ticket_lunch;
    private boolean ticket_dinner;

    public CStudent(String email, String names, String last_name_p, String last_name_m, String user_type){
        super(email, names, last_name_p, last_name_m, user_type);
        this.ticket_dinner = false;
        this.ticket_lunch = false;
        this.tickets_consumed = 0;
        this.tickets_withdrawn = 0;
    }

    public CStudent(){
        super();
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
