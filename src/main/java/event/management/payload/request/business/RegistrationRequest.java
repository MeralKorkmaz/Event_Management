package event.management.payload.request.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "registrationDate cannot be empty")
    private LocalDate registrationDate;

    @Column(nullable = false, unique = true)
    @NotNull(message = "ticketNumber cannot be empty")
    private String ticketNumber;
}
