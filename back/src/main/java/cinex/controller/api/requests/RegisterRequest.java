package cinex.controller.api.requests;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequest extends LoginRequest {
    @NotNull
    public String confirmPassword;
    @NotNull
    public String email;
}
