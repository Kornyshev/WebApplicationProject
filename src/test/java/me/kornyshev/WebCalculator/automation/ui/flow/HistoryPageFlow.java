package me.kornyshev.WebCalculator.automation.ui.flow;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.automation.ui.pom.HistoryPage;
import me.kornyshev.WebCalculator.automation.ui.pom.elements.HistoryTable;
import me.kornyshev.WebCalculator.models.Calculation;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

@Slf4j
public class HistoryPageFlow {

    public List<Calculation> getCalculationsFromPage() {
        return new HistoryTable(page(HistoryPage.class).getHistoryTable()).getData();
    }

    public boolean tableIsEmpty() {
        return new HistoryTable(page(HistoryPage.class).getHistoryTable()).tableIsEmpty();
    }

    public void clickReturnToMainPageLink() {
        page(HistoryPage.class).getLinkToMainPage().click();
    }

}
