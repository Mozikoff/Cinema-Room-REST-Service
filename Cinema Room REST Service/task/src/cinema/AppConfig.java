package cinema;

import cinema.model.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private static final int DEFAULT_ROWS_COUNT = 9;
    private static final int DEFAULT_COLUMNS_COUNT = 9;

    @Bean
    public Room getRoom() {
        return new Room(DEFAULT_ROWS_COUNT, DEFAULT_COLUMNS_COUNT);
    }
}
