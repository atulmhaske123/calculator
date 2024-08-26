package com.test.calculator.model;

import com.test.calculator.operation.Operation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChainOperation {
	@NotNull(message = "operation value is a must, make sure chainOperations array contains operation in every object.")
	private Operation operation;
	@NotNull(message = "operand value is a must, make sure chainOperations array contains operand in every object.")
	private Number operand;
}
