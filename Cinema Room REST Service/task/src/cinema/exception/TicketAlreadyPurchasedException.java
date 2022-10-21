package cinema.exception;


public class TicketAlreadyPurchasedException extends IllegalArgumentException {

    public TicketAlreadyPurchasedException(String cause) {
        super(cause);
    }

}
