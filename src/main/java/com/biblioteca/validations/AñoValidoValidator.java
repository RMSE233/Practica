package com.biblioteca.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 * Validador personalizado para la anotación AñoValido.
 * Valida que el año esté entre 1900 y el año actual.
 */
public class AñoValidoValidator implements ConstraintValidator<AñoValido, Integer> {
    private static final int AÑO_MINIMO = 1900;
    private static final int AÑO_ACTUAL = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    public void initialize(AñoValido annotation) {
        // Inicialización si es necesaria
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Se requiere un valor
        }
        return value >= AÑO_MINIMO && value <= AÑO_ACTUAL;
    }
}
