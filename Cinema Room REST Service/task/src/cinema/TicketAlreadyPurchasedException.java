package cinema;


public class TicketAlreadyPurchasedException extends IllegalArgumentException {

    public TicketAlreadyPurchasedException(String cause) {
        super(cause);
    }

}
