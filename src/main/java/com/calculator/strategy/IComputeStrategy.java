package com.calculator.strategy;

import com.calculator.model.IRequest;

public interface IComputeStrategy {
	
	public abstract Number calculate(IRequest request);

}
