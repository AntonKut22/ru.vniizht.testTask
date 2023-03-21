package ru.vniizht.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vniizht.calc.domain.CalculationModel;
import ru.vniizht.calc.service.CalculationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalcApplicationTests {

    private final CalculationService calculationService = new CalculationService();

    @Test
    void calculation() {

        String rawData = "5+6/2";
        CalculationModel calculationModel = CalculationModel.builder()
                .rawData(rawData)
                .build();

        CalculationModel result = calculationService.calculation(calculationModel);

        assertEquals(8.0, result.getResult());
        assertEquals("Результат: 8.0", result.getResultText());
    }

    @Test
    void calculationFail() {
        String rawData = "(5+6)/2";
        CalculationModel calculationModel = CalculationModel.builder()
                .rawData(rawData)
                .build();

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            calculationService.calculation(calculationModel);
        });
    }
}
