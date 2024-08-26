package com.test.calculator.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChainRequest {
	@NotNull(message = "initial value is a must, make sure request contains intial value.")
	private Number initial;
	@NotNull(message = "chainOperations value is a must, make sure request contains chainOperations value.")
	@Valid
	private List<ChainOperation> chainOperations;
}
