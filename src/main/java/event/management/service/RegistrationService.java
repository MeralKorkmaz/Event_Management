package event.management.service;

import event.management.domain.business.Registration;
import event.management.exception.ResourceNotFoundException;
import event.management.payload.mapper.RegistrationMapper;
import event.management.payload.request.business.RegistrationRequest;
import event.management.payload.response.business.RegistrationResponse;
import event.management.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    public RegistrationResponse saveRegistration(RegistrationRequest registrationRequest) {

        Registration registration = registrationMapper.mapRegistrationRequestToRegistration(registrationRequest);

       Registration savedRegistration =  registrationRepository.save(registration);

       return registrationMapper.mapRegistrationToRegistrationResponse(savedRegistration);
    }

    public Registration existByRegistrationId(Long registrationId){
       return  registrationRepository.findById(registrationId).orElseThrow(() -> new ResourceNotFoundException("registration with this id does not exist"));
    }

    public String deleteOrganization(Long registrationId) {

       Registration registration =  existByRegistrationId(registrationId);
        registrationRepository.deleteById(registrationId);

        return "registration with this id has been deleted";

    }
}
