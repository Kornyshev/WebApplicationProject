package me.kornyshev.WebCalculator.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "Calculation")
@Table(name = "Calculations")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private int id;

    @JsonProperty("firstNumber")
    @JsonAlias("First Number")
    private int firstNumber;
    @JsonProperty("mathSign")
    @JsonAlias("Math Sign")
    private String mathSign;
    @JsonProperty("secondNumber")
    @JsonAlias("Second Number")
    private int secondNumber;
    @JsonProperty("result")
    @JsonAlias("Result")
    private double result;
    @JsonProperty("date")
    @JsonAlias("Date")
    private String date;

}
