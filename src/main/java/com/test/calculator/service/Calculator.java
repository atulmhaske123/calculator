package com.test.calculator.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.calculator.operation.IOperation;
import com.test.calculator.operation.Operation;

@Service
public class Calculator implements ICalculator{

	// operations map store 
	// key --> Operation Like ( ADD / SUBTRACT / ...)
	// value --> actual implementation for that operation like ( AddOperation / SubtractOperation / ...).
	private final Map<Operation, IOperation> operations;

	@Autowired
	public Calculator(List<IOperation> operations) {
		//Initiate map of all available operations 
		this.operations = operations.stream()
				.collect(Collectors.toMap(IOperation::getOperation, op -> op));
	}

	/**
	 * Method performs a single operation between two numbers and returns the result.
	 */
	@Override
	public Number calculate(Operation op, Number num1, Number num2) {
		return this.operations.get(op).calculate(num1, num2);
	}

}
