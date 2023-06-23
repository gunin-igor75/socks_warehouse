package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для валидирующий операции
 */
public class SocksOperationConstraintValidator implements ConstraintValidator<ValidationSocksOperations, String> {

    /** Допустимые операции*/
    private Set<String> operations;

    /**
     * Инициалицая значений с которым спавниваются входные данные
     * @param constraintAnnotation - сущность через которую передаются данные ддля сравнения
     */
    @Override
    public void initialize(ValidationSocksOperations constraintAnnotation) {
        operations = Stream.of(constraintAnnotation.valueOperations()).collect(Collectors.toSet());
    }

    /**
     * Валидация операций
     * @param value - операции приходящие с фронта
     * @param constraintValidatorContext  ограничительный блок проверки допустимости
     * @return - результат валидации
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return operations.contains(value);
    }
}
