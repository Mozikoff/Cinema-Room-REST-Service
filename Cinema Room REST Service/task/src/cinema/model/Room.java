package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Room {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private Map<String, Seat> availableSeats;
    @JsonIgnore
    private Map<UUID, Seat> bookedSeats = new HashMap<>();

    public Room(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new HashMap<>();
        createRoom();
    }

    private void createRoom() {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.put("" + i + j, new Seat(i, j));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public Collection<Seat> getAvailableSeats() {
        return availableSeats.values();
    }

    public PurchasedTicket bookSeat(Seat seat) {
        if (isAvailable(seat)) {
            PurchasedTicket ticket = new PurchasedTicket(seat);
            bookedSeats.put(ticket.getToken(), ticket.getTicket());
            availableSeats.remove("" + seat.getRow() + seat.getColumn());
            return ticket;
        }
        return null;
    }

    public boolean isAvailable(Seat seat) {
        return availableSeats.containsKey("" + seat.getRow() + seat.getColumn());
    }

    public boolean isPurchased(UUID token) {
        return bookedSeats.containsKey(token);
    }

    public ReturnedTicket returnTicket(UUID token) {
        if (isPurchased(token)) {
            Seat seat = bookedSeats.get(token);
            availableSeats.put("" + seat.getRow() + seat.getColumn(), seat);
            bookedSeats.remove(token);
            return new ReturnedTicket(seat);
        }
        return null;
    }

    public boolean isValidSeat(Seat seat) {
        if (seat.getRow() > totalRows || seat.getRow() < 0) {
            return false;
        }
        if (seat.getColumn() > totalColumns || seat.getColumn() < 0) {
            return false;
        }
        return true;
    }

    public Stats statistics() {
        Stats stats = new Stats();
        stats.setCurrentIncome(bookedSeats.values().stream()
                .mapToInt(Seat::getPrice).sum());
        stats.setNumberOfAvailableSeats(totalRows * totalColumns - bookedSeats.size());
        stats.setNumberOfPurchasedSeats(bookedSeats.size());
        return stats;
    }
}
