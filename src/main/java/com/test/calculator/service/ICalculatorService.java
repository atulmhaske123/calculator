package com.test.calculator.service;

import java.util.List;

import com.test.calculator.model.ChainOperation;

public interface ICalculatorService {
	public Number calculate(Number intial, List<ChainOperation> list);
}
