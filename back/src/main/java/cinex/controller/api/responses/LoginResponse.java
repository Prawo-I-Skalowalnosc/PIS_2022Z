package cinex.controller.api.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    public boolean success;
    public String token;
    public String message;
    public String username;
    public List<String> roles;
}
