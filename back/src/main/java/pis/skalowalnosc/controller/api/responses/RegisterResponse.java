package pis.skalowalnosc.controller.api.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterResponse extends LoginResponse {
    public RegisterResponse(boolean success, String token, String message) {
        super(success, token, message);
    }
}
