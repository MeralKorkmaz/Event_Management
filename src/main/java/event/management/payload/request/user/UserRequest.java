package event.management.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {

    @Column(unique = true, nullable = false)
    @NotNull(message = "Please enter your userName")
    @Size(min = 5, max = 14, message = "userName must be minimum {min} characters and maximum {max} characters ")
    //TODO look for the pattern and regex part
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your username must consist of the characters .")
    private String username;

    @Column(unique = true, nullable = false)
    @Email(message = "Please enter valid email")
    @NotNull(message = "Please enter your email")
    @Size(min = 8, max = 50,message = "email must be minimum {min} characters and maximum {max} characters" )
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Please enter your name")
    @Size(min = 5, max = 14, message = "name must be minimum {min} characters and maximum {max} characters ")
    //TODO look for the pattern and regex part
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Please enter your surname")
    @Size(min = 5, max = 14, message = "surname must be minimum {min} characters and maximum {max} characters ")
    //TODO look for the pattern and regex part
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your surname must consist of the characters .")
    private String surname;


    @NotNull(message = "Please enter your password")
    @Column(nullable = false)
    @Size(min = 8, max = 20, message = "password must be minimum {min} characters and maximum {max} characters")
    private String password;

    private Boolean built_in = false;





}
