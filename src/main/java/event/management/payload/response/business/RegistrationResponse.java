package event.management.payload.response.business;


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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponse {



    private LocalDate registrationDate;
    private String ticketNumber;
}
