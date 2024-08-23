package event.management.controller;

import event.management.payload.request.business.RegistrationRequest;
import event.management.payload.response.business.RegistrationResponse;
import event.management.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/saveRegistration")
    @PreAuthorize("hasAnyAuthority('ADMIN','ORGANIZER')")
    public ResponseEntity<RegistrationResponse> saveRegistration(@Valid @RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(registrationService.saveRegistration(registrationRequest));
    }

    @DeleteMapping("/deleteRegistration/{registrationId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ORGANIZER')")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long registrationId){
        return ResponseEntity.ok(registrationService.deleteOrganization(registrationId));
    }
}
