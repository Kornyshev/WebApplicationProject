package me.kornyshev.WebCalculator.automation.ui.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AbstractPage {

    @FindBy(css = "body > h2")
    private SelenideElement title;

}
