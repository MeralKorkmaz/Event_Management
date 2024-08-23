package event.management.payload.response.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {



    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private int maxAttendees;
    private int ticketPrice;
}
