package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.flow.ResultPageFlow;
import me.kornyshev.WebCalculator.models.Result;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ResultPageSteps {

    ResultPageFlow resultPageFlow = new ResultPageFlow();

    @When("user clicks Main Page link on Result Page")
    public void userClicksMainPageLinkOnResultPage() {
        resultPageFlow.clickReturnToMainPageLink();
    }

    @Then("verify that Result on Result Page is equals to following data:")
    public void verifyResultOnThePage(Result expectedResult) {
        assertThat(resultPageFlow.getResult()).isEqualTo(expectedResult);
    }

}
