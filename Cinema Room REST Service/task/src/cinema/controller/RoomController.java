package cinema.controller;

import cinema.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class RoomController {

    final String ERROR_ALREADY_PURCHASED = "The ticket has been already purchased!";
    final String ERROR_INVALID_SEAT = "The number of a row or a column is out of bounds!";
    final String ERROR_WRONG_TOKEN = "Wrong token!";
    final String ERROR_WRONG_PASSWORD = "The password is wrong!";
    final String PASS = "super_secret";

    @Autowired
    Room room;

    @GetMapping("/seats")
    public Room getSeats() {
        return room;
    }

    @PostMapping("/purchase")
    public PurchasedTicket purchase(@RequestBody Seat seat) {
        if (!room.isValidSeat(seat)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_INVALID_SEAT);
        }
        PurchasedTicket purchasedTicket = room.bookSeat(seat);
        if (Objects.isNull(purchasedTicket)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_ALREADY_PURCHASED);
        }
        return purchasedTicket;
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody ReturnToken token) {
        if (!room.isPurchased(token.getToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_WRONG_TOKEN);
        }
        return room.returnTicket(token.getToken());
    }

    @PostMapping("/stats")
    public Stats getStats(@RequestParam(value = "password", required = false) String password) {
        Stats stats = new Stats();
        if (Objects.nonNull(password) && PASS.equals(password)) {
            stats = room.statistics();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_WRONG_PASSWORD);
        }
        return stats;
    }

}

