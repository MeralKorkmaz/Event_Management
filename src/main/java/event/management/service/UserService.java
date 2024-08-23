package event.management.service;

import event.management.exception.BadRequestException;
import event.management.repository.UserRepository;
import event.management.domain.user.RoleType;
import event.management.domain.user.User;
import event.management.exception.ResourceNotFoundException;
import event.management.payload.mapper.UserMapper;
import event.management.payload.request.user.UserRequest;
import event.management.payload.response.user.UserResponse;

import event.management.service.helper.MethodHelper;
import event.management.service.helper.PageableHelper;
import event.management.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final  UniquePropertyValidator uniqueness;
    private final  UserMapper userMapper;
    private final  RoleService roleService;
    private final  PasswordEncoder passwordEncoder;
    private final PageableHelper pageableHelper;
    private final MethodHelper methodHelper;

    public UserResponse saveUser(UserRequest userRequest, String userRole) {


        uniqueness.checkDuplicate(userRequest.getUsername(),userRequest.getEmail());
       User user =  userMapper.mapUserRequestToUser(userRequest);

        if(userRole.equalsIgnoreCase(RoleType.ADMIN.name)){

            if(Objects.equals(userRequest.getUsername(),"Admin")){
                user.setBuilt_in(true);
            }

            user.setRole(roleService.getRole(RoleType.ADMIN));

        }else if(userRole.equalsIgnoreCase(RoleType.ORGANIZER.name)){

            user.setRole(roleService.getRole(RoleType.ORGANIZER));

        }else if(userRole.equalsIgnoreCase(RoleType.ATTENDEE.name)){
            user.setRole(roleService.getRole(RoleType.ATTENDEE));
        }else if(userRole.equalsIgnoreCase(RoleType.SPONSOR.name)){
            user.setRole(roleService.getRole(RoleType.SPONSOR));
        }else{
            throw  new ResourceNotFoundException("RoleType is not exist in database");
        }

        System.out.println("user password: "  + user.getPassword() + " encrpyted password " + passwordEncoder.encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.mapUserToUserResponse(savedUser);

    }


    public Page<UserResponse> getUsersByPage(int page, int size, String sort, String type, String userRole) {
        Pageable pageable=  pageableHelper.getPageable(page, size, sort, type);

        //TODO look for this part
        return userRepository.findByUserRole(userRole, pageable)
                .map(userMapper::mapUserToUserResponse);
    }

    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("There is no user exist with this is"));
        return userMapper.mapUserToUserResponse(user);
    }

    public String deleteUser(Long id, HttpServletRequest request) {

       User user = methodHelper.isUserExist(id);

       String userName = (String) request.getAttribute("username");
       User theUserSendTheRequest = userRepository.findByUsername(userName);

        if(Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException("This user cannot be deleted");
        }else if(theUserSendTheRequest.getRole().getRoleType() == RoleType.ATTENDEE){

            if((user.getRole().getRoleType()== RoleType.ORGANIZER)
                    || (user.getRole().getRoleType() == RoleType.SPONSOR)
                     || (user.getRole().getRoleType() == RoleType.ADMIN)   ){
                throw new BadRequestException("You do not authorize to delete this user");

            };

        } else if(theUserSendTheRequest.getRole().getRoleType() == RoleType.SPONSOR){
            if((user.getRole().getRoleType() == RoleType.ADMIN)
            || (user.getRole().getRoleType() == RoleType.ATTENDEE)
            || (user.getRole().getRoleType()==RoleType.ORGANIZER)){
                throw new BadRequestException("You do not authorize to delete this user");
            }
        }else if((theUserSendTheRequest.getRole().getRoleType()== RoleType.ORGANIZER)){
            if((user.getRole().getRoleType()==RoleType.ADMIN)
            || (user.getRole().getRoleType()==RoleType.ATTENDEE)
            || (user.getRole().getRoleType()== RoleType.SPONSOR)){
                throw new BadRequestException("You do not authorize to delete this user");
            }
        }
        userRepository.deleteById(id);
        return "User successfully deleted";


    }

    public UserResponse updateUser(UserRequest userRequest, Long userId) {

        User user = methodHelper.isUserExist(userId);
        methodHelper.checkBuiltIn(user);
        uniqueness.checkUniqueProperties(user,userRequest);

        User updatedUser = userMapper.mapUserRequestToUpdatedUser(userRequest);

            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            updatedUser.setRole(user.getRole());
           User savedUser = userRepository.save(updatedUser);
           return userMapper.mapUserToUserResponse(savedUser);



    }

    public long countAllAdmin(){
        return userRepository.countAdmin(RoleType.ADMIN);
    }


}
