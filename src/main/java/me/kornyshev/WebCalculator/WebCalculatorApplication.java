package me.kornyshev.WebCalculator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCalculatorApplication.class, args);
        log.info("Application started");
    }

}
