package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberHooks {

    @Before
    void createBrowser() {
        log.info("Creating browser");
    }

    @After
    void closeDriver() {
        log.info("Closing browser");
    }

}
