package com.test.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.calculator.model.ChainRequest;
import com.test.calculator.model.Response;
import com.test.calculator.service.ICalculatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/calculator")
public class ChainOperationController {

	@Autowired
	private ICalculatorService calculator;
	
	@PostMapping("/chain")
	/**
	 * Perform calculation on either simple request or chaining request and return the result
	 * @param request : 1. Initial : The number which will consider as first operand.
	 * 					2. ChainOperations[] : The list of chaining operation which contains Operation 
	 * 							and Next Operand. like Add 3 and then Multiply by 5.
	 * @return response : contains final result
	 */
	public Response chainCalculator(@RequestBody @Valid ChainRequest request) {
		return new Response( 
				calculator.calculate(request.getInitial(), request.getChainOperations()));
	}

}
