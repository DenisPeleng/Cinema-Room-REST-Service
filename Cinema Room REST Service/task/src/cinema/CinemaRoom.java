package cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        for (int i = 0; i < totalColumns; i++) {
            for (int j = 0; j < totalRows; j++) {
                int price = 10;
                if (i > 8) {
                    price = 8;
                }
                Seat newSeat = new Seat(j + 1, i + 1);
                newSeat.setPrice(price);
                availableSeats.add(newSeat);
            }
        }
    }

    private int getIndexOfSeat(Seat seat) {
        for (int i = 0; i < availableSeats.size(); i++) {
            Seat tempSeat = availableSeats.get(i);
            if (seat.getRow() == tempSeat.getRow()
                    && seat.getColumn() == tempSeat.getColumn()) {
                return i;
            }
        }
        return -1;
    }

    public Seat bookSeat(Seat seat) {
        int index = getIndexOfSeat(seat);
        Seat seatToBook = availableSeats.get(index);
        seatToBook.setTaken(true);
        return seatToBook;
    }

    public boolean isTaken(Seat seat) {
        int index = getIndexOfSeat(seat);
        return availableSeats.get(index).isTaken();
    }

    public boolean isNotCorrectColumn(int column, CinemaRoom cinemaRoom) {
        return column <= 0 || column > cinemaRoom.getTotalColumns();
    }

    public boolean isNotCorrectRow(int row, CinemaRoom cinemaRoom) {
        return row <= 0 || row > cinemaRoom.getTotalRows();
    }
}
