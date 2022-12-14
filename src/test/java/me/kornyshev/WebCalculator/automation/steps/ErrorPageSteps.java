package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.flow.ErrorPageFlow;

@Slf4j
public class ErrorPageSteps {

    ErrorPageFlow errorPageFlow = new ErrorPageFlow();

    @When("user clicks Main Page link on Error Page")
    public void userClicksMainPageLinkOnErrorPage() {
        errorPageFlow.clickReturnToMainPageLink();
    }

}
