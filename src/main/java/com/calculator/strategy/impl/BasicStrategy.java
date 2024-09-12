package com.calculator.strategy.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.calculator.exception.ExecuteException;
import com.calculator.model.IRequest;
import com.calculator.model.SimpleRequest;
import com.calculator.operation.IOperation;
import com.calculator.operation.Operation;
import com.calculator.strategy.IComputeStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Primary
@Slf4j
public class BasicStrategy implements IComputeStrategy {
	
	protected final Map<Operation, IOperation> operations;

	@Autowired
	public BasicStrategy(Map<Operation, IOperation> operations) {
		this.operations = operations;
	}

	@Override
	public Number calculate(IRequest request) {
		try {
			SimpleRequest req = (SimpleRequest)request;
			IOperation operation = this.operations.get(req.getOperation());
			return operation.calculate(req.getOperand1(), req.getOperand2());
		} catch (Exception ex) {
			log.error(" Error while performing basic calculation : {}", ex.getMessage());
			throw new ExecuteException(ex.getMessage());
		}
	}

}
