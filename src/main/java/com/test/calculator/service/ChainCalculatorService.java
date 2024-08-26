package com.test.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.calculator.model.ChainOperation;

@Service
public class ChainCalculatorService implements ICalculatorService {

	@Autowired
	private ICalculator calculator;

	/**
	 * Method allows chaining multiple operations on a single value, similar to how basic calculators work.
	 * @param Initial : The number which will consider as first operand.
	 * @param ChainOperations[] : The list of chaining operation which contains Operation 
	 * 							and Next Operand. like Add 3 and then Multiply by 5.
	 * @return response : contains final result
	 */
	@Override
	public Number calculate(Number intial, List<ChainOperation> list) {
		Number result = intial;
		for(int i = 0; i < list.size(); i++){
			result = calculator.calculate(list.get(i).getOperation(), result, list.get(i).getOperand());
		}
		
		return result;
	}

}
