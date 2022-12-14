package me.kornyshev.WebCalculator.automation.ui.pom.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import me.kornyshev.WebCalculator.models.Calculation;
import me.kornyshev.WebCalculator.utils.ConverterUtils;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static me.kornyshev.WebCalculator.utils.ConverterUtils.mapData;
import static me.kornyshev.WebCalculator.utils.SelenideUtils.fixedStream;

@Getter
public class HistoryTable {

    private SelenideElement root;
    public static final String HEADERS = "thead th";

    public HistoryTable(SelenideElement root) {
        this.root = root;
    }

    public List<Calculation> getData() {
        final List<String> headers = root.$$(HEADERS).shouldHave(sizeGreaterThan(0)).texts();
        return fixedStream(root.$$("tbody tr"))
                .map(row -> new ConverterUtils().convert(mapData(headers, row.$$("td").texts()), Calculation.class))
                .toList();
    }

    public boolean tableIsEmpty() {
        return root.$$("tbody tr").isEmpty();
    }

}
