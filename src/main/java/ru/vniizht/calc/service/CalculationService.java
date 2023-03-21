package ru.vniizht.calc.service;

import org.springframework.stereotype.Service;
import ru.vniizht.calc.domain.CalculationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CalculationService {

    private List<String> nums;
    private List<Character> operations;

    public CalculationModel calculation(CalculationModel model) {

        findComponents(model.getRawData());
        model.setResult(calculate());
        model.setResultText("Результат: " + model.getResult());

        return model;
    }

    private void findComponents(String equation) {
        nums = new ArrayList<>();
        operations = new ArrayList<>();

        String temp = equation.replaceAll("\\s", "");
        temp = temp.replace(",", ".");

        char[] chars = temp.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = chars.length - 1; i > 0; i--) {
            if (Character.isDigit(chars[i]) || chars[i] == '.') {
                stringBuilder.append(chars[i]);
            } else {
                if (chars[i] == '-' && !Character.isDigit(chars[i - 1])) {
                    stringBuilder.append(chars[i]);
                    nums.add(stringBuilder.reverse().toString());
                    operations.add(chars[i - 1]);
                    i--;
                    stringBuilder.delete(0, stringBuilder.length());
                } else {
                    nums.add(stringBuilder.reverse().toString());
                    operations.add(chars[i]);
                    stringBuilder.delete(0, stringBuilder.length());
                }
            }
        }
        stringBuilder.append(chars[0]);
        nums.add(stringBuilder.reverse().toString());
        Collections.reverse(nums);
        Collections.reverse(operations);
    }

    private Double calculate() {
        double temp = 0d;
        while (!operations.isEmpty()) {
            if (operations.contains('*')) {
                int index = operations.indexOf('*');
                temp = Double.parseDouble(nums.get(index)) * Double.parseDouble(nums.get(index + 1));
                correctList(temp, index);
            } else if (operations.contains('/')) {
                int index = operations.indexOf('/');
                temp = Double.parseDouble(nums.get(index)) / Double.parseDouble(nums.get(index + 1));
                correctList(temp, index);
            } else if (operations.contains('+')) {
                int index = operations.indexOf('+');
                temp = Double.parseDouble(nums.get(index)) + Double.parseDouble(nums.get(index + 1));
                correctList(temp, index);
            } else if (operations.contains('-')) {
                int index = operations.indexOf('-');
                temp = Double.parseDouble(nums.get(index)) - Double.parseDouble(nums.get(index + 1));
                correctList(temp, index);
            }
        }
        return temp;
    }

    private void correctList(Double temp, int index) {
        operations.remove(index);
        nums.set(index, temp.toString());
        nums.remove(index + 1);
    }

}
