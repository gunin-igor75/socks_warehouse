package sky.pro.socks_warehouse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SocksColorConstraintValidator.class)
public @interface ValidationSocksColors {

    String[] valueColors();

    String message() default "Цвет не допустим ${validatedValue}, можеть быть: {valueColors} ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
