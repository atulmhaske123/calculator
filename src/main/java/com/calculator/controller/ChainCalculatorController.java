package com.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.model.ChainRequest;
import com.calculator.model.Response;
import com.calculator.service.Calculator;
import com.calculator.strategy.CalculationStrategy;
import com.calculator.strategy.impl.ChainStrategy;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChainCalculatorController extends CalculatorController{
	
	@Autowired
	public ChainCalculatorController(Calculator calculator, CalculationStrategy calculationStrategy, ChainStrategy chainStrategy) {
		super(calculator, calculationStrategy);
		calculationStrategy.setComputeStrategy(chainStrategy);
	}

	@PostMapping("/v1/chain")
	public Response chainCalculator(@RequestBody @Valid ChainRequest request) {
		log.debug(" chain request={}", request);
		return super.processRequest(request);
	}

}
