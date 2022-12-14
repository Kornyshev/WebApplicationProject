package me.kornyshev.WebCalculator.automation.ui.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

@Getter
public class MainPage extends AbstractPage {

    @FindBy(css = "form input#firstNumber")
    private SelenideElement firstField;
    @FindBy(css = "form input#secondNumber")
    private SelenideElement secondField;
    @FindBy(css = "form select[name='mathSign']")
    private SelenideElement operationSelect;
    @FindBy(css = "form input[value='Calculate']")
    private SelenideElement calculateButton;
    @FindBy(css = "body > h5 > a")
    private SelenideElement historyLink;

}
