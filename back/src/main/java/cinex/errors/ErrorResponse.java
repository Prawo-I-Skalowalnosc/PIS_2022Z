package cinex.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    public HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public final LocalDateTime timestamp;
    public String message;
    public String infoMessage;

    public ErrorResponse(HttpStatus status, String message, Throwable ex) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message; // don't show to user
        this.infoMessage = ex.getLocalizedMessage(); // show to user
    }
}
