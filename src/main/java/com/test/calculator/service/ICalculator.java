package com.test.calculator.service;

import com.test.calculator.operation.Operation;

public interface ICalculator {
	public Number calculate(Operation op, Number num1, Number num2);
}
