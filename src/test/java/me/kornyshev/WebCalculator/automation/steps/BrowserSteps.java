package me.kornyshev.WebCalculator.automation.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.pom.AbstractPage;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BrowserSteps {

    @Given("open the Main Page of application")
    public void openTheMainPageOfApplication() {
        Selenide.open("http://localhost:8080/");
    }

    @When("user closes browser")
    public void userClosesBrowser() {
        Selenide.closeWebDriver();
    }

    @Then("verify that title on page {string} equals to {string}")
    public void verifyTitleOnThePage(String page, String expectedTitle) {
        assertThat(page(getPageObjectClass(page)).getTitle().text()).isEqualTo(expectedTitle);
    }

    @SuppressWarnings("unchecked")
    public Class<AbstractPage> getPageObjectClass(String pageObjectName) {
        try {
            return (Class<AbstractPage>) Class.forName("me.kornyshev.WebCalculator.automation.ui.pom." +
                    pageObjectName.replaceAll("\\s", StringUtils.EMPTY));
        } catch (ClassNotFoundException e) {
            log.error("Unable to map page object name to a class!", e);
            throw new IllegalStateException("Unable to map page object name to a class!");
        }
    }

}
