package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    public boolean isTaken() {
        return isTaken;
    }
    @JsonIgnore
    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    private boolean isTaken = false;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    Seat() {

    }

}
