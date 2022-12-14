package me.kornyshev.WebCalculator.automation.ui.flow;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.pom.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

@Slf4j
public class MainPageFlow {

    public List<String> getOperationSelectOptions() {
        return new Select(page(MainPage.class).getOperationSelect()).getOptions()
                .stream().map(WebElement::getText).toList();
    }

    public void setFirstNumber(int number) {
        page(MainPage.class).getFirstField().setValue(String.valueOf(number));
    }

    public void setSecondNumber(int number) {
        page(MainPage.class).getSecondField().setValue(String.valueOf(number));
    }

    public void selectOperation(String sign) {
        new Select(page(MainPage.class).getOperationSelect()).selectByValue(sign);
    }

    public void clickCalculateButton() {
        page(MainPage.class).getCalculateButton().click();
    }

    public void clickHistoryLink() {
        page(MainPage.class).getHistoryLink().click();
    }

}
