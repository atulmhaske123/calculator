package com.calculator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.model.IRequest;
import com.calculator.model.Response;
import com.calculator.service.Calculator;
import com.calculator.strategy.CalculationStrategy;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/calculator")
@Slf4j
public class CalculatorController {

	protected Calculator calculator;
	protected CalculationStrategy calculationStrategy;
	
	public CalculatorController(Calculator calculator, CalculationStrategy calculationStrategy) {
		this.calculator = calculator;
		this.calculationStrategy = calculationStrategy;
	}

	public Response processRequest(IRequest request) {
		Number result = calculator.calculate(calculationStrategy.getComputeStrategy(), request);
		return Response.builder().code("200").message("Calculation successful").data(result).build();
	}

}
