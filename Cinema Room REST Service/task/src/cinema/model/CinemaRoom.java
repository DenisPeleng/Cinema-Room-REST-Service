package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {

    private int totalRows;
    private int totalColumns;
    private List<Ticket> availableSeats;
    private List<BookedTicket> bookedTickets;
    private int currentIncome;

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalColumns; i++) {
            for (int j = 1; j <= totalRows; j++) {
                availableSeats.add(new Ticket(j, i, j < 5 ? 10 : 8));
            }
        }
        this.bookedTickets = new ArrayList<>();
        this.currentIncome = 0;
    }


    public List<Ticket> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Ticket> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonIgnore
    public List<BookedTicket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<BookedTicket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }
    @JsonIgnore
    public int getCurrentIncome() {
        return currentIncome;
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

    public BookedTicket bookTicket(Seat seat) {
        int index = getIndexOfSeat(seat);
        Ticket ticketToBook = availableSeats.get(index);
        ticketToBook.setTaken(true);
        BookedTicket bookedTicket = new BookedTicket(ticketToBook);
        bookedTickets.add(bookedTicket);
        currentIncome += ticketToBook.getPrice();
        return bookedTicket;
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

    public boolean isTokenExist(String token) {
        for (BookedTicket bTicket : bookedTickets
        ) {
            if (bTicket.getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    public Ticket returnTicket(String token) {
        for (BookedTicket bTicket : bookedTickets
        ) {
            if (bTicket.getToken().equals(token)) {
                bookedTickets.remove(bTicket);
                Ticket ticket = bTicket.getTicket();
                ticket.setTaken(false);
                currentIncome -= ticket.getPrice();
                return ticket;
            }
        }
        return null;
    }

    public boolean isCorrectStatPassword(String password) {
        String passwordStat = "super_secret";
        return passwordStat.equals(password);
    }
    @JsonIgnore
    public int getNumberOfAvailableSeats() {
        return availableSeats.size() - getNumberOfBookedSeats();
    }
    @JsonIgnore
    public int getNumberOfBookedSeats() {
        return bookedTickets.size();
    }
}
