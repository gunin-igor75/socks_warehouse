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
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Глобальная обработка исключении в приложении
 */
@RestControllerAdvice
public class SocksGlobalExceptionHandler {

    /**
     * Обработчик исключения{@code MethodArgumentNotValidException}
     *
     * @param ex- исключение при валидаии данных обрабатываемых spring
     * @return - обертка с удобной формы восприятия исключения на фронте
     */
    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleAnotherError(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String message = fieldErrors.stream()
                .map(field -> field.getObjectName() + "." + field.getField() + ":" + field.getDefaultMessage())
                .collect(Collectors.joining(",", "[", "]"));
        return new ResponseEntity<>(new ErrorResponse(message), BAD_REQUEST);
    }

    /**
     * Обработчик исключения{@code ConstraintViolationException}
     *
     * @param ex- исключение при валидаии данных кастомным обработчиком
     * @return - обертка с удобной формы восприятия исключения на фронте
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), BAD_REQUEST);
    }

    /**
     * Обработчик исключения{@code ResourceNotFoundException}
     *
     * @param ex- исключение при отсутсвии ресурсв в базе данных
     * @return - обертка с удобной формы восприятия исключения на фронте
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), NOT_FOUND);
    }
}
