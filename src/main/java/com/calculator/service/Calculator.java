package com.calculator.service;

import org.springframework.stereotype.Service;

import com.calculator.model.IRequest;
import com.calculator.strategy.IComputeStrategy;

@Service
public class Calculator {
	public Number calculate(IComputeStrategy computeStrategy, IRequest request) {
		return computeStrategy.calculate(request);
	}
}
