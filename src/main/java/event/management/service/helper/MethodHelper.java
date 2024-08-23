package event.management.service.helper;

import event.management.domain.user.User;
import event.management.exception.BadRequestException;
import event.management.exception.ResourceNotFoundException;
import event.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;

    public User isUserExist(Long userId){
        return  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("There is not user with this id"));
    }


    public void checkBuiltIn(User user){

        if(Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException("This user cannot be deleted");
        }

    }
}
