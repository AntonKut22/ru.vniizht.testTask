package ru.vniizht.calc;

import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vniizht.calc.domain.CalculationModel;
import ru.vniizht.calc.service.CalculationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


        String rawData = "5+6q";

        UnknownFunctionOrVariableException thrown = assertThrows(
                UnknownFunctionOrVariableException.class,
                () -> {
                    CalculationModel calculationModel = CalculationModel.builder()
                            .rawData(rawData)
                            .build();

                    calculationService.calculation(calculationModel);
                },
                "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(rawData));
    }

}
