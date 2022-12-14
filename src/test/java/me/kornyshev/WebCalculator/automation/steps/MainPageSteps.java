package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.flow.MainPageFlow;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MainPageSteps {

    MainPageFlow mainPageFlow = new MainPageFlow();

    @When("user clicks History link on Main Page")
    public void userClicksHistoryOnMainPage() {
        mainPageFlow.clickHistoryLink();
    }

    @When("user clicks Calculate button on Main Page")
    public void userClicksCalculateButton() {
        mainPageFlow.clickCalculateButton();
    }

    @When("user set {string} value in {int}(st)(nd) field on the Main Page")
    public void userSetIntValueInIntStSndFieldOnTheMainPage(String value, int fieldIndex) {
        switch (fieldIndex) {
            case 1 -> mainPageFlow.setFirstNumber(Integer.parseInt(value));
            case 2 -> mainPageFlow.setSecondNumber(Integer.parseInt(value));
            default -> throw new IllegalArgumentException();
        }
    }

    @When("user selects math operation {string} on the Main Page")
    public void userSelectsMathOperationOnTheMainPage(String operation) {
        mainPageFlow.selectOperation(operation);
    }

    @Then("verify that math operations on the Main Page are equal to expected:")
    public void verifyMathOperationOnMainPage(List<String> expectedOperations) {
        assertThat(mainPageFlow.getOperationSelectOptions()).isEqualTo(expectedOperations);
    }

}
