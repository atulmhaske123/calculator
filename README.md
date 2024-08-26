# flexible-calculator
- A simple, extensible calculator in Java that supports multiple operations.
- Added enum named as 'Operation.java' that includes basic operations like ADD, SUBTRACT, MULTIPLY, and DIVIDE.
- For each operation implemented seprate class with there own calculate method. like 'AddOperation.java', 'DivideOperation.java', 'MultiplyOperation.java' etc..
- Implemented a method 'calculate(Operation op, Numbernum1, Number num2)' in the 'Calculator.java' class that performs a single operation between
two numbers and returns the result. 
- 

## Implemented REST endpoint to perform simple/chain operations.
- To Perform Simple sum operation use below curl request. below request performing summation of 2 and 5,
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

- To Perform chaining operation use below curl request. where
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
