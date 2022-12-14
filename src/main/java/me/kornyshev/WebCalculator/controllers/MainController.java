package me.kornyshev.WebCalculator.controllers;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.businesslogic.CalculatorService;
import me.kornyshev.WebCalculator.models.Calculation;
import me.kornyshev.WebCalculator.models.Result;
import me.kornyshev.WebCalculator.models.UiData;
import me.kornyshev.WebCalculator.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    CalculationRepository calculationRepository;
    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/")
    public String getIndex(Model model) {
        log.info("getIndex() method");
        model.addAttribute("uiData", new UiData());
        return "index";
    }

    @GetMapping("/history")
    public ModelAndView openHistoryPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("history");
        final List<Calculation> calculations = calculationRepository.findAll();
        final List<Calculation> calculationsForPage = calculations.size() > 10
                ? calculations.subList(calculations.size() - 10, calculations.size())
                : calculations;
        Collections.reverse(calculationsForPage);
        modelAndView.addObject("calculations", calculationsForPage);
        return modelAndView;
    }

    @PostMapping("/calculate")
    public ModelAndView generateDatabase(@ModelAttribute("uiData") UiData uiData) {
        ModelAndView modelAndView = new ModelAndView();
        if (uiData.getMathSign().equals("/") && uiData.getSecondNumber() == 0) {
            modelAndView.setViewName("validation");
        } else {
            final double resultOfCalculation = calculate(uiData.getFirstNumber(), uiData.getSecondNumber(), uiData.getMathSign());
            final Calculation entity = Calculation.builder()
                    .firstNumber(uiData.getFirstNumber())
                    .secondNumber(uiData.getSecondNumber())
                    .mathSign(uiData.getMathSign())
                    .date(LocalDate.now().toString())
                    .result(resultOfCalculation).build();
            log.info("Saving to DB: '{}'", entity);
            calculationRepository.save(entity);
            modelAndView.addObject("resultOfCalculation", Result.builder()
                    .expression(uiData.getFirstNumber() + uiData.getMathSign() + uiData.getSecondNumber())
                    .result(String.valueOf(resultOfCalculation))
                    .build());
            modelAndView.setViewName("result");
        }
        return modelAndView;
    }

    private double calculate(int firstNumber, int secondNumber, String operation) {
        return switch (operation) {
            case "+" -> calculatorService.sum(firstNumber, secondNumber);
            case "-" -> calculatorService.subtract(firstNumber, secondNumber);
            case "*" -> calculatorService.multiple(firstNumber, secondNumber);
            case "/" -> calculatorService.divideWithFloor(firstNumber, secondNumber);
            default -> throw new IllegalArgumentException();
        };
    }

}
