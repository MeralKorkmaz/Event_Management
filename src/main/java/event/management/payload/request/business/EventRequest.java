package event.management.payload.request.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {



    @Column(unique = true, nullable = false)
    @NotNull(message = "title cannot be empty")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "description cannot be empty")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "location cannot be empty")
    private String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    @NotNull(message = "location cannot be empty")
    private LocalDateTime dateTime;


    @NotNull(message = "You must enter the max attendees limit")
    @Column(nullable = false)
    private int maxAttendees;

    @NotNull(message = "ticketPrice cannot be empty")
    @Column(nullable = false)
    private int ticketPrice;


}
