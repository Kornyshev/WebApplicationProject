package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.en.When;
import me.kornyshev.WebCalculator.utils.DatabaseQueries;

public class DatabaseSteps {

    @When("user deletes all data from Calculator Database")
    public void userDeletesAllDataFromCalculatorDatabase() {
        DatabaseQueries.deleteDataFromCalculator();
    }

}
