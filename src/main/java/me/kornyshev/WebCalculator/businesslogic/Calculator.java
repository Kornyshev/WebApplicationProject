package me.kornyshev.WebCalculator.businesslogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Calculator implements CalculatorService {

    @Override
    public int sum(int x, int y) {
        return Math.addExact(x, y);
    }

    @Override
    public int subtract(int x, int y) {
        return Math.subtractExact(x, y);
    }

    @Override
    public int multiple(int x, int y) {
        return Math.multiplyExact(x, y);
    }

    @Override
    public double divideWithFloor(int x, int y) {
        return Math.floorDiv(x, y);
    }

    @Override
    public boolean isOdd(int x) {
        return x % 2 != 0;
    }

}
