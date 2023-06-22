package sky.pro.socks_warehouse.exception_nandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class SocksGlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleAnotherError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String message = fieldErrors.stream()
                .map(field -> field.getObjectName() + "." + field.getField() + ":" + field.getDefaultMessage())
                .collect(Collectors.joining(",", "[", "]"));
        return new ResponseEntity<>(new ErrorResponse(message), BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class, ResourceNotFoundException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), BAD_REQUEST);
    }
}
