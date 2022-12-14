package me.kornyshev.WebCalculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Result {

    @JsonProperty("expression")
    private String expression;
    @JsonProperty("result")
    private String result;

}
