package event.management.payload.mapper;

import event.management.domain.user.User;
import event.management.payload.request.user.UserRequest;
import event.management.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse mapUserToUserResponse(User user){
        return UserResponse.builder().
                userId(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .userRole(user.getRole().getRoleType().name)
                .build();
    }

    public User mapUserRequestToUser(UserRequest userRequest){
        return User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .surname(userRequest.getSurname())
                .password(userRequest.getPassword())
                .built_in(userRequest.getBuilt_in())
                .build();
    }

    public User mapUserRequestToUpdatedUser(UserRequest userRequest){
        return User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
