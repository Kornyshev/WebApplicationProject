package me.kornyshev.WebCalculator.automation.ui.flow;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.pom.ResultPage;
import me.kornyshev.WebCalculator.models.Result;

import static com.codeborne.selenide.Selenide.page;

@Slf4j
public class ResultPageFlow {

    public Result getResult() {
        final ResultPage resultPage = page(ResultPage.class);
        return Result.builder()
                .expression(resultPage.getExpression().text())
                .result(resultPage.getResult().text())
                .build();
    }

    public void clickReturnToMainPageLink() {
        page(ResultPage.class).getLinkToMainPage().click();
    }

}
