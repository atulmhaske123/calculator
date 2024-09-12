package com.calculator.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.calculator.operation.IOperation;
import com.calculator.operation.Operation;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CalculatorConfig {
	
	@Bean
	public Map<Operation, IOperation> CalculatorOperations(List<IOperation> operations) {
		log.debug("All supported operations are : {}", operations.toString());
		return operations.stream()
				.collect(Collectors.toMap(IOperation::getOperation, op -> op));
	}

}
