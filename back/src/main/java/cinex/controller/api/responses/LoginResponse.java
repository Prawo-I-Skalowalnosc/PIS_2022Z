package cinex.controller.api.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    public boolean success;
    public String token;
    public String message;
}
