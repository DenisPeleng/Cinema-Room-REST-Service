package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {
    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoomSeats() {

        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Seat seatToBook) {
        if (cinemaRoom.isNotCorrectColumn(seatToBook.getColumn(), cinemaRoom)
                || cinemaRoom.isNotCorrectRow(seatToBook.getRow(), cinemaRoom)) {
            return ErrorResponse.OutOfBoundsCinemaException();
        }
        if (cinemaRoom.isTaken(seatToBook)) {
            return ErrorResponse.TicketAlreadyPurchasedException();
        }
        return new ResponseEntity<>(cinemaRoom.bookSeat(seatToBook), HttpStatus.OK);
    }

}

