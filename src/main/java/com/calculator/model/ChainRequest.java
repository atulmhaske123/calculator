package com.calculator.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChainRequest implements IRequest {
	@NotNull(message = "Initial value is a must, make sure request contains intial value.")
	private Number initial;
	@NotNull(message = "ChainOperations value is a must, make sure request contains chainOperations value.")
	@Valid
	private List<ChainOperation> chainOperations;
}
