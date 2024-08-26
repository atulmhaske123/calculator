# flexible-calculator
- A simple, extensible calculator in Java that supports multiple operations.
- Added enum named as **Operation.java** that includes basic operations like ADD, SUBTRACT, MULTIPLY, and DIVIDE.
- For each operation implemented seprate class with there own calculate method. like **AddOperation.java, DivideOperation.java, MultiplyOperation.java** etc..
- Implemented a method **calculate(Operation op, Numbernum1, Number num2)** in the **Calculator.java** class that performs a single operation between
two numbers and returns the result.
- **Chaining Operations:** Implementd a method that allows chaining multiple operations on a single value, similar to how basic calculators work. This should enable users to start with an initial value and perform a series of operations sequentially. 
- **Extensibility:** The Calculator class will allow new operations to be added without requiring changes to its existing code.
- **IoC Compatibility:** Using SpringBoot Inversion of Control (IoC) capability to intialise bean.
- **Error Handling:** The solution handled invalid operations gracefully.
- **Testing:** Added unit tests to verify solution, including both normal cases and edge cases.

## How To Run?
- Git clone current repo in your local machine.
- Open Project in any IDE and Run the project as a java application. application will start on default port 8080.
- Below are the REST curl requests which we can execute in postman/shell.

## Implemented REST endpoint to perform simple/chain operations.
- Supported operations are **ADD, SUBTRACT, MULTIPLY, and DIVIDE.**
- To Perform **Simple sum operation** use below **POST** curl request. below request performing summation of 2 and 5,
  Here Initial is 2 and we performing add operation on it with operand 5. 
     ```ts
     curl --location 'http://localhost:8080/calculator/chain' \
      --header 'Content-Type: application/json' \
      --data '{
          "initial": 2,
          "chainOperations":[
              { "operation": "ADD", "operand": 5 }
          ]
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
