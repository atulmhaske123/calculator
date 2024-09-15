# Flexible-calculator

## Features
- Support basic math operations like Addition, Sutraction, Divide & Multiplication.
- Support REST for simple and chaining calculations.
- Provided exception handling support.
- Strong input validation.

## Design follows strict open-close principle.

![image](https://github.com/user-attachments/assets/6d497fba-17bf-429d-9487-6befd25faae9)

- **Used java.lang.Number class to support all numeric data type :**
    The abstract class Number is the superclass of classes BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, and Short. So we dont need to check data type explicitly for each request.
- **INFINITY is a valid response :**
    cases like divide by zero or large number mutiplication which exceeding number class limits are considered as INFINITY response.
- **Supported and verified all possible usecases using SpringJUnit4ClassRunner**
  
   ![image](https://github.com/user-attachments/assets/1c084668-4746-48b3-be75-5183c6e1cdab)
- **Error Handling :** Used SpringBoot based global exception handling to handle possible exception in application.
- **Validation :** Used jakarta.validation.constraints power to validate input request params.
- **IoC Compatibility :** Used springboot's dependancy injecting capability extensivly within the application.
  Below code make sure whatever operations are there will get injected into List<IOperation> operations. Even we added new operation like POWER in application, we dont have to make any kind of modification to inject bean here.
  ```java
    @Bean
	public Map<Operation, IOperation> CalculatorOperations(List<IOperation> operations) {
		return operations.stream()
				.collect(Collectors.toMap(IOperation::getOperation, op -> op));
	}
  ```
## Extensibility : How to add new operation like Power into application.
- Modify the ENUM Operation to support new operation.
- Create a new class which will implements IOperation interface.
- Provide your calculation logic in newly added class under calculation method.
- Thats Set !!! No need to modify any IF/ELSE or SWITCH case in application to support new operation.
  
## Extensibility : How to add new calculation strategy like priority based calculation.
- Create a new Strategy class which will implements IStrategy inteface.
- Add your algo logic in calculation method of newly added class.( like multiply/divide having more prority than add/subtract).
- Create a new Controller class which will extends CalculationController base class.
- In the newly added controller assign priority strategy for incoming requests.
- Thats Set !!! No need to modify any IF/ELSE or SWITCH case in application to support new calculation algo.
  
## How To Run?
- Git clone current repo in your local machine.
- Open Project in any IDE and Run the project as a java application. application will start on default port 8080.
- Below are the REST curl requests which we can execute in postman/shell.

## Implemented REST endpoint to perform simple/chain operations.
- Supported operations are **ADD, SUBTRACT, MULTIPLY, and DIVIDE.**
- To Perform **Simple sum operation** use below **POST** curl request. given request performing summation of 999 and 1.
     ```ts
     curl --location 'http://localhost:8080/calculator/v1/basic' \
      --header 'Content-Type: application/json' \
      --data '{
          "operation": "MULTIPLY",
          "operand1": 999,
          "operand2": 1
      }'
     ```

- To Perform **chaining operation** use below **POST** curl request. where
  Here Initial is 899 and we performing add operation on it with operand 5.
  then, we performing add operation again on previous result with operand 5.
  then, we perfoming divide operation on previous result with operand 1.
     ```ts
     curl --location 'http://localhost:8080/calculator/chain' \
     --header 'Content-Type: application/json' \
     --data '{
         "initial": 899,
         "chainOperations":[
             { "operation": "ADD", "operand": 5 },
             { "operation": "ADD", "operand": 5 },
             { "operation": "DIVIDE", "operand": "1"}
         ]
     }'
     ```
