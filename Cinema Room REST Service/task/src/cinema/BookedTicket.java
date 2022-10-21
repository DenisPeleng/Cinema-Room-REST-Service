package cinema;

public class BookedTicket {
    private String token;
    private Ticket ticket;

    public BookedTicket(Ticket ticket) {
        this.ticket = ticket;
        this.token = TokenUUID.generateUUIDToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


}
