package cinema;

public class Seat {
    private int row;
    private int column;

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
        this.row = row + 1;
        this.column = column + 1;
    }

}
