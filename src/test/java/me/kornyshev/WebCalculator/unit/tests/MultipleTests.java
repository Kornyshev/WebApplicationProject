package me.kornyshev.WebCalculator.unit.tests;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.businesslogic.CalculatorService;
import me.kornyshev.WebCalculator.unit.extensions.annotations.CalculatorServiceInjector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(CalculatorServiceInjector.class)
public class MultipleTests {

    @Inject
    CalculatorService calculatorService;

    @ParameterizedTest(name = " Test {index}: verify that {0} * {1} == {2}")
    @CsvFileSource(resources = "/testdata/multiplication.csv", delimiter = ' ')
    void verifyMultipleMethodWithDataFromCsvFile(int x, int y, int result) {
        log.info("Test 'verifyMultipleMethodWithDataFromCsvFile' started with value: '{}', '{}', '{}'", x, y, result);
        assertThat(calculatorService.multiple(x, y)).isEqualTo(result);
    }

}
