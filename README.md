# flexible-calculator
A simple, extensible calculator in Java that supports multiple operations.

## Implemented REST endpoint to perform simple/chain operations.
- To Perform Simple sum operation Use Below Curl Request
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
