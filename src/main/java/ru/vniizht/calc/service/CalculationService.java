package ru.vniizht.calc.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import ru.vniizht.calc.domain.CalculationModel;

@Service
public class CalculationService {

    public CalculationModel calculation(CalculationModel model){

        Expression expression = new ExpressionBuilder(model.getRawData()).build();
        model.setResult(expression.evaluate());
        model.setResultText("Результат: " + model.getResult());

        return model;
    }

}
