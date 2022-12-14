package me.kornyshev.WebCalculator.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UiData {

    private int firstNumber;
    private String mathSign;
    private int secondNumber;

}
