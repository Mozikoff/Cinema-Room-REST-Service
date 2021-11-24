package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ReturnToken {
    @JsonProperty("token")
    UUID returnToken;

    public UUID getToken() {
        return returnToken;
    }
}
