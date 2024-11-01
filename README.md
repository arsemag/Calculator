# Project Title: Calculator

## Project Description
A Calculator is designed to perform basic arithmetic operations, allowing users to input mathematical 
expressions and receive accurate results. This project aims to provide a simple yet effective tool for performing 
calculations while learning about object-oriented programming concepts, such as implementing interfaces and creating 
class instances. There is a SmartCalculator and SimpleCalculator which are both a type of Calculator both implement 
basic arithmetic operations. The distinction is that when multiple equal signs are entered, the SmartCalculator permits
the final operator and operand to be applied once more. Additionally, the equation may contain more than one 
operation, and the addition operation may be entered first.


## Prerequisites
- Recommended to open through Visual Studio Code or Intellji.

## Usage Instructions
- Clone the repository to your local machine.
- Compile the Java files using your preferred Java development environment.
- There are only test currently but will be implementing a main function. 

=

## Features
- **Basic Arithmetic Operations:** Supports addition, subtraction, multiplication, and division.
- **Error Handling:** Includes mechanisms to handle invalid inputs gracefully.

## Code Organization
The project is structured as follows:
- **Calculator Interface** : Defines essential methods that all calculator types must implement,
  such as input() and getResult(), ensuring a consistent interface across different calculator implementations.

- **Abstract Calculator Class** : Provides shared functionality for all calculators, including:
Common Properties: Manages properties like makeEquation, leftSideNumber, and rightSideNumber.
Helper Methods: Includes methods like isOverflow() to check for arithmetic overflow and calculateResult() to perform operations.
Abstract Methods: Defines core methods (input(), createInstance(), etc.) that subclasses must implement.

- **SmartCalculator and SimpleCalculator Classes**: These classes extend ACalculator, implementing specialized behavior:
SmartCalculator: Offers advanced functionality like handling complex input sequences.
SimpleCalculator: Provides basic arithmetic operations for straightforward calculations.

## Key Classes and Functions

### Java Classes
- `SmartCalculator`: Main class that runs the application and handles user interactions.
- `Calculator`: Contains methods for performing arithmetic operations.
- `ACalculator`: An abstract class that provides common functionality for handling inputs and basic arithmetic operations.

### ACalculator Class Functions
- **Constructor:** 
  - `ACalculator()`: Initializes the calculator with default values.
  - `ACalculator(StringBuilder makeEquation, long leftSideNumber, long rightSideNumber, boolean isThisOperators, char operator, boolean hasEqualSign)`: Initializes with specific values.

- **Abstract Methods:**
  - `abstract Calculator input(char input)`: Handles input from the user.
  - `protected abstract Calculator onlyOperator(char operator)`: Handles operator inputs.
  - `protected abstract Calculator onlyNumber(char operator)`: Handles number inputs.
  - `protected abstract Calculator equalSignInput(char operator)`: Handles equal sign inputs.
  - `protected abstract Calculator handleWhenEqualSign(char input)`: Manages logic when the equal sign is pressed.
  - `protected abstract Calculator createInstance(StringBuilder makeEquation, long leftSideNumber, long rightSideNumber, boolean isThisOperators, char operator, boolean hasEqualSign)`: Creates an instance of the calculator with given parameters.

- **Concrete Methods:**
  - `String getResult()`: Returns the current equation as a string.
  - `boolean isOverflow(long result)`: Checks for integer overflow.
  - `String calculateResult(char operator, long leftSideNumber, long rightSideNumber)`: Performs calculation based on the operator.
  - `Calculator clearEverything()`: Resets the calculator.
  - `Calculator handleLeftSideNumber(char input)`: Handles input for the left side number.
  - `Calculator handleRightSideNumber(char input)`: Handles input for the right side number.
  - `Calculator handleOperatorRightSide(char input)`: Manages operator input on the right side.
  - `boolean validOperator(char input)`: Validates operator input.
  - `boolean validNumber(char input)`: Validates number input.
  - `Calculator handleOverFlowInput(char input)`: Handles overflow input scenarios.
  - `Calculator handleAllInputs(char input)`: Manages all types of input.
  - `Calculator handleLastOperatorCase(char input)`: Handles cases where the last input is an operator.
  - `Calculator handleSpecialCase(char input)`: Handles special input cases.

### Key Functions in Calculator Class
- `add(double a, double b)`: Returns the sum of two numbers.
- `subtract(double a, double b)`: Returns the difference between two numbers.
- `multiply(double a, double b)`: Returns the product of two numbers.
- `divide(double a, double b)`: Returns the quotient of two numbers, handling division by zero.

### Utility Functions
- `isValidInput(String input)`: Validates user input to ensure it's a proper mathematical expression.
- `displayResult(double result)`: Outputs the calculation result to the user.
