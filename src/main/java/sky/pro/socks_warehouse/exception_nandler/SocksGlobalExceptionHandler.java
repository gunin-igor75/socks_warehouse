package sky.pro.socks_warehouse.exception_nandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class SocksGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnotherError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String message = fieldErrors.stream()
                .map(field -> field.getObjectName() + "." + field.getField() + ":" + field.getDefaultMessage())
                .collect(Collectors.joining(",", "[", "]"));
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(message));
    }

    @ExceptionHandler({ConstraintViolationException.class, ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleConstraintAndResourceError(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
