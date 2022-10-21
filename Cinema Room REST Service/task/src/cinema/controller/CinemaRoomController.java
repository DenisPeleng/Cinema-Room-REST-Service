package cinema.controller;

import cinema.exception.*;
import cinema.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class CinemaRoomController {
    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoomSeats() {

        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Seat seatToBook) {
        if (cinemaRoom.isNotCorrectColumn(seatToBook.getColumn(), cinemaRoom) || cinemaRoom.isNotCorrectRow(seatToBook.getRow(), cinemaRoom)) {
            throw new OutOfBoundsCinemaException("The number of a row or a column is out of bounds!");
        }
        if (cinemaRoom.isTaken(seatToBook)) {
            throw new TicketAlreadyPurchasedException("The ticket has been already purchased!");
        }
        return new ResponseEntity<>(cinemaRoom.bookTicket(seatToBook), HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody TokenUUID tokenUUID) {
        String token = tokenUUID.getToken();
        if (!cinemaRoom.isTokenExist(token)) {
            throw new IllegalTokenException("Wrong token!");
        }
        Ticket returnedTicket = cinemaRoom.returnTicket(token);
        return new ResponseEntity<>(Map.of("returned_ticket", returnedTicket), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats(@RequestParam(required = false) String password) {

        if (password == null || !cinemaRoom.isCorrectStatPassword(password)) {
            throw new IllegalStatsPasswordException("The password is wrong!");
        }
        return new ResponseEntity<>(new Stats(cinemaRoom.getCurrentIncome(), cinemaRoom.getNumberOfAvailableSeats(), getCinemaRoomSeats().getNumberOfBookedSeats()), HttpStatus.OK);
    }

    @ExceptionHandler(OutOfBoundsCinemaException.class)
    public ResponseEntity<?> handleOutOfBoundsCinemaException(OutOfBoundsCinemaException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalTokenException.class)
    public ResponseEntity<?> handleIllegalTokenException(IllegalTokenException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<?> handleTicketAlreadyPurchasedException(TicketAlreadyPurchasedException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalStatsPasswordException.class})
    public ResponseEntity<?> handleIllegalStatsPasswordException(IllegalStatsPasswordException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}

