package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
    @JsonProperty("returned_ticket")
    Seat returnedTicket;

    public ReturnedTicket(Seat ticket) {
        returnedTicket = ticket;
    }

    public Seat getReturnedTicket() {
        return returnedTicket;
    }
}
