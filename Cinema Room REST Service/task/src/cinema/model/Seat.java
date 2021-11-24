package cinema.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private final int HIGHER_PRICE_AREA_LAST_ROW_ID = 4;
    private final int HIGHER_PRICE = 10;
    private final int NORMAL_PRICE = 8;

    @JsonIgnore
    private boolean booked;

    @JsonCreator
    public Seat(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        price = row <= HIGHER_PRICE_AREA_LAST_ROW_ID ? HIGHER_PRICE : NORMAL_PRICE;
        booked = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = true;
    }
}
