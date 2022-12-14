package me.kornyshev.WebCalculator.automation.ui.flow;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.pom.ErrorPage;

import static com.codeborne.selenide.Selenide.page;

@Slf4j
public class ErrorPageFlow {

    public void clickReturnToMainPageLink() {
        page(ErrorPage.class).getLinkToMainPage().click();
    }

}
