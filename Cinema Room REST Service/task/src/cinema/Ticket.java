package cinema;

public class Ticket extends Seat {
    private int price;

    public Ticket(int row, int column, int price) {
        super(row, column);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
