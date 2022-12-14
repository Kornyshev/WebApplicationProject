package me.kornyshev.WebCalculator.automation.ui.pom;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

@Getter
public class HistoryPage extends AbstractPage {

    @FindBy(css = "body > h5 > a")
    private SelenideElement linkToMainPage;
    @FindBy(css = "table")
    private SelenideElement historyTable;

}
