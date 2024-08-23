package event.management.controller;

import event.management.domain.user.User;
import event.management.payload.request.user.UserRequest;
import event.management.payload.response.user.UserResponse;
import event.management.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save/{userRole}")
    //TODO Look pathvariable and requestparam
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest, @PathVariable String userRole){
        return ResponseEntity.ok(userService.saveUser(userRequest,userRole));
    }


    @GetMapping("/getAllUsersByPage/{userRole}")
    public ResponseEntity<Page<UserResponse>> getUserByPage(
            @PathVariable String userRole,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value="sort",defaultValue = "surname") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){

        Page<UserResponse> users = userService.getUsersByPage(page,size,sort,type,userRole);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return  ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, HttpServletRequest request){

        return ResponseEntity.ok(userService.deleteUser(id,request));

    }


    @PutMapping("update/{userId}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable Long userId){
        return ResponseEntity.ok(userService.updateUser(userRequest, userId));
    }


}


