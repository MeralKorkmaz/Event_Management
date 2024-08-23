package event.management.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "Please provide your old password")
    private String oldPassword;

    @NotBlank(message = "Your new  password cannot be empty or  just space")
    private String newPassword;


}
