package me.kornyshev.WebCalculator.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SelenideUtils {

    public static Stream<SelenideElement> fixedStream(ElementsCollection collection) {
        return fixedStream(collection, false);
    }

    public static Stream<SelenideElement> fixedStream(ElementsCollection collection, boolean parallel) {
        return StreamSupport.stream(collection.asFixedIterable().spliterator(), parallel);
    }

}
