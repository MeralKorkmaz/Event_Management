package event.management.payload.request.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {


    @Column(unique= true, nullable = false)
    @NotNull(message = "Ticket Number cannot be empty")
    private int ticketNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "issuedDate cannot be empty")
    private LocalDate issuedDate;

    @Column(nullable = false)
    @NotNull(message = "price cannot be empty")
    private int price;
}
