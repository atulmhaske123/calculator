package com.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.model.Response;
import com.calculator.model.SimpleRequest;
import com.calculator.service.Calculator;
import com.calculator.strategy.CalculationStrategy;
import com.calculator.strategy.impl.BasicStrategy;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicCalculatorController extends CalculatorController{
	
	@Autowired
	public BasicCalculatorController(Calculator calculator, CalculationStrategy calculationStrategy, BasicStrategy basicStrategy) {
		super(calculator, calculationStrategy);
		calculationStrategy.setComputeStrategy(basicStrategy);
	}

	@PostMapping("/v1/basic")
	public Response basicCalculator(@RequestBody @Valid SimpleRequest request) {
		log.debug(" basic request={}", request);
		return super.processRequest(request);
	}

}
