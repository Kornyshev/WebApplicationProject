package me.kornyshev.WebCalculator.automation.ui.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

@Getter
public class ResultPage extends AbstractPage {

    @FindBy(xpath = "//table/tbody//td[1]")
    private SelenideElement expression;
    @FindBy(xpath = "//table/tbody//td[2]")
    private SelenideElement result;
    @FindBy(css = "body > h5 > a")
    private SelenideElement linkToMainPage;

}
