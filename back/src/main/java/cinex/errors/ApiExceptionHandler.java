package cinex.errors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    protected ResponseEntity<Object> handleBackendException(AppException ex) {
        var error = "Błąd logiki aplikacji. Wyjątek typu: " + ex.getClass().getName();
        var status = HttpStatus.CONFLICT;
        return new ResponseEntity<>( new ErrorResponse(status, error, ex), status);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleBackendException(SQLException ex) {
        var error = "Błąd bazy danych. Wyjątek typu: " + ex.getClass().getName();
        var status = HttpStatus.CONFLICT;
        return new ResponseEntity<>( new ErrorResponse(status, error, ex), status);
    }
}
