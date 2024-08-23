package event.management.controller;

import event.management.payload.request.LoginRequest;
import event.management.payload.response.AuthResponse;
import event.management.payload.request.UpdatePasswordRequest;
import event.management.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateTheUser(@RequestBody @Valid LoginRequest loginRequest){
        return  ResponseEntity.ok(authenticationService.authenticateTheUser(loginRequest));
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request){
        authenticationService.updatePassword(updatePasswordRequest,request);
        String response = "Password has been changed successfully";

        return ResponseEntity.ok(response);
    }
}
