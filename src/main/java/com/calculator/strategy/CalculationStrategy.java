package com.calculator.strategy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.calculator.model.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Scope(value = "prototype")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationStrategy {
	
	private IComputeStrategy computeStrategy;
	
	public Number calculate(IRequest request) {
		return this.computeStrategy.calculate(request);
	}
	

}
