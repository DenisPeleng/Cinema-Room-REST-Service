package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;

   public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

   public Seat() {

    }
    @JsonIgnore
    public boolean isTaken() {
        return isTaken;
    }
    @JsonIgnore
    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    private boolean isTaken = false;

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


}
