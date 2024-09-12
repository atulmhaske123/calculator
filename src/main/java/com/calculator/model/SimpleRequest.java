package com.calculator.model;

import com.calculator.operation.Operation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleRequest implements IRequest {
	@NotNull(message = "operation value is a must.")
	private Operation operation;
	@NotNull(message = "operand1 value is a must.")
	private Number operand1;
	@NotNull(message = "operand2 value is a must.")
	private Number operand2;
}
