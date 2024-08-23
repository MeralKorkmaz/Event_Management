package event.management.payload.mapper;

import event.management.domain.business.Registration;
import event.management.payload.request.business.RegistrationRequest;
import event.management.payload.response.business.RegistrationResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RegistrationMapper {

    public Registration mapRegistrationRequestToRegistration(RegistrationRequest registrationRequest){
        return Registration.builder()
                .registrationDate(registrationRequest.getRegistrationDate())
                .ticketNumber(registrationRequest.getTicketNumber())
                .build();
    }

    public RegistrationResponse mapRegistrationToRegistrationResponse(Registration registration){
        return RegistrationResponse.builder()
                .registrationDate(registration.getRegistrationDate())
                .ticketNumber(registration.getTicketNumber())
                .build();
    }
}
