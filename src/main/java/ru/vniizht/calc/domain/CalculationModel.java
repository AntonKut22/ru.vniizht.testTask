package ru.vniizht.calc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CalculationModel {

    String rawData;
    Double result;
    String resultText;

}
