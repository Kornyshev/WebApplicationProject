package me.kornyshev.WebCalculator.automation.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.flow.HistoryPageFlow;
import me.kornyshev.WebCalculator.models.Calculation;
import me.kornyshev.WebCalculator.utils.DateUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class HistoryPageSteps {

    HistoryPageFlow historyPageFlow = new HistoryPageFlow();

    @When("user clicks Main Page link on History Page")
    public void userClicksMainPageLinkOnHistoryPage() {
        historyPageFlow.clickReturnToMainPageLink();
    }

    @Then("verify that History table is equals to following data:")
    public void verifyHistoryTableEqualsToExpected(List<Calculation> calculations) {
        calculations.forEach(elem -> elem.setDate(
                DateUtils.processDateFromString(elem.getDate(), DateUtils.DateFormat.YEAR_MONTH_DAY_HYPHEN)));
        assertThat(historyPageFlow.getCalculationsFromPage()).containsExactlyInAnyOrderElementsOf(calculations);
    }

    @Then("verify that History table contains following data:")
    public void verifyHistoryTableContainsExpected(List<Calculation> calculations) {
        calculations.forEach(elem -> elem.setDate(
                DateUtils.processDateFromString(elem.getDate(), DateUtils.DateFormat.YEAR_MONTH_DAY_HYPHEN)));
        assertThat(historyPageFlow.getCalculationsFromPage()).containsAll(calculations);
    }

    @Then("verify that History Table is empty")
    public void verifyHistoryTableIsEmpty() {
        assertThat(historyPageFlow.tableIsEmpty()).isTrue();
    }

}
