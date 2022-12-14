package me.kornyshev.WebCalculator.unit.extensions.annotations;

import me.kornyshev.WebCalculator.businesslogic.Calculator;
import me.kornyshev.WebCalculator.businesslogic.CalculatorService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import javax.inject.Inject;
import java.lang.reflect.Field;

public class CalculatorServiceInjector implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        final Field[] declaredFields = testInstance.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)
                    && field.getType().isAssignableFrom(CalculatorService.class)) {
                field.setAccessible(true);
                field.set(testInstance, new Calculator());
            }
        }
    }

}
