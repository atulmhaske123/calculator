package com.calculator.strategy.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calculator.exception.ExecuteException;
import com.calculator.model.ChainOperation;
import com.calculator.model.ChainRequest;
import com.calculator.model.IRequest;
import com.calculator.operation.IOperation;
import com.calculator.operation.Operation;
import com.calculator.strategy.IComputeStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ChainStrategy implements IComputeStrategy {
	
	protected final Map<Operation, IOperation> operations;

	@Autowired
	public ChainStrategy(Map<Operation, IOperation> operations) {
		this.operations = operations;
	}

	@Override
	public Number calculate(IRequest request) {
		try {
			ChainRequest req = (ChainRequest)request;
			Number result = req.getInitial();
			List<ChainOperation> list = req.getChainOperations();
			
			IOperation operation;
			for(int i = 0; i < list.size(); i++){
				operation = this.operations.get(list.get(i).getOperation());
				result = operation.calculate(result, list.get(i).getOperand());
			}
			
			return result;
		} catch (Exception ex) {
			log.error(" Error while performing chain calculation : {}", ex.getMessage());
			throw new ExecuteException(ex.getMessage());
		}
	}

}
