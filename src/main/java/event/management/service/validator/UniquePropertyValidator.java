package event.management.service.validator;

import event.management.domain.user.User;
import event.management.payload.request.user.UserRequest;
import event.management.repository.UserRepository;
import event.management.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public class UniquePropertyValidator {

    private final  UserRepository userRepository;

    public void checkDuplicate(String userName, String email){
        if(userRepository.existsByUsername(userName)){
            throw new ConflictException("This userName already exist");

        }
        if(userRepository.existsByEmail(email)){

            throw new ConflictException("This email already exist");
        }
    }

    public void checkUniqueProperties(User user, UserRequest userRequest){
        String updatedUsername = "";
        String updatedEmail = "";
        boolean isChanged = false;

        if(!user.getUsername().equalsIgnoreCase(userRequest.getUsername())){
            updatedUsername = userRequest.getUsername();
            isChanged = true;
        }

        if(!user.getEmail().equalsIgnoreCase(userRequest.getEmail())){
            updatedEmail = userRequest.getEmail();
            isChanged = true;
        }

        if(isChanged){
            checkDuplicate(updatedUsername,updatedEmail);
        }
    }
}
