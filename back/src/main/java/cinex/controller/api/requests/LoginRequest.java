package cinex.controller.api.requests;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    public String username;
    @NotNull
    public String password;
}
