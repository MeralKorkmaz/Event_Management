package event.management.service;

import event.management.domain.user.User;
import event.management.exception.BadRequestException;
import event.management.payload.mapper.UserMapper;
import event.management.payload.request.LoginRequest;
import event.management.payload.response.AuthResponse;
import event.management.payload.request.UpdatePasswordRequest;
import event.management.repository.UserRepository;
import event.management.security.jwt.JwtUtils;
import event.management.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    public AuthResponse authenticateTheUser(LoginRequest loginRequest) {

        User user = userRepository.findByUsername("Admin");




        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //to generate the token
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        //to reach the user who send login request
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //to get the String role from GrantedAuthorities
        Set<String> roles = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());



        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse =   AuthResponse.builder();
        authResponse.username(userDetails.getUsername());
        authResponse.name(userDetails.getName());
        authResponse.token(token.substring(7));
        role.ifPresent(authResponse::role);

        return authResponse.build();
    }

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        User user = userRepository.findByUsernameEquals(username);

        if (Boolean.TRUE.equals(user.getBuilt_in())) {
            throw new BadRequestException("This users password cannot be updated");

        }

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("The old password is not correct");
        }

        String hashedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }
}
