package com.calculator.operation.impl;

import org.springframework.stereotype.Component;

import com.calculator.operation.IOperation;
import com.calculator.operation.Operation;

@Component
public class MultiplyOperation implements IOperation {

	@Override
	public Number calculate(Number num1, Number num2) {
		return num1.doubleValue() * num2.doubleValue();
	}

	@Override
	public Operation getOperation() {
		return Operation.MULTIPLY;
	}
}
