package cinema.model;

import java.util.UUID;

public class PurchasedTicket {
    private final UUID token;
    private final Seat ticket;

    public PurchasedTicket(Seat ticket) {
        token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
