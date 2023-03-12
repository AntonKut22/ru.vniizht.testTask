package ru.vniizht.calc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vniizht.calc.domain.CalculationModel;
import ru.vniizht.calc.service.CalculationService;

@Controller
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculationService calculationService;

    @GetMapping("/calculation")
    public ModelAndView getText(ModelAndView modelAndView) {
        String text = "Calculation";
        modelAndView.addObject("text", text);

        CalculationModel calculationModel = CalculationModel.builder().build();
        modelAndView.addObject("calculation", calculationModel);

        modelAndView.setViewName("calculation");
        return modelAndView;
    }

    @PostMapping("/calculation")
    public String calculation(@ModelAttribute CalculationModel calculationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "calculation";
        }

        calculationService.calculation(calculationModel);

        String text = "Calculation";
        model.addAttribute("text", text);
        model.addAttribute("calculation", calculationModel);
        return "calculation";
    }

}
