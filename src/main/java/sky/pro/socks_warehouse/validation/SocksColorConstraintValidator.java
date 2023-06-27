package sky.pro.socks_warehouse.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для валидирующий цвет носков
 */
public class SocksColorConstraintValidator implements ConstraintValidator<ValidationSocksColors, String> {

    /** Допустимые цвета носков*/
    private Set<String> colors;


    /**
     * Инициалицая значений с которым спавниваются входные данные
     * @param constraintAnnotation - сущность через которую передаются данные ддля сравнения
     */
    @Override
    public void initialize(ValidationSocksColors constraintAnnotation) {
        colors = Stream.of(constraintAnnotation.valueColors()).collect(Collectors.toSet());
    }

    /**
     * Валидация цвета носков
     * @param value - цвет носков приходящий с фронта
     * @param constraintValidatorContext  ограничительный блок проверки допустимости
     * @return - результат валидации
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return colors.contains(value);
    }
}
