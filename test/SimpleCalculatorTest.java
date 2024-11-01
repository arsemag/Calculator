import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;

/**
 * A test class to make sure that the methods for Simple Calculator are working
 * properly. Looks for edge cases and makes sure that all conditions are checked.
 */
public class SimpleCalculatorTest {

  protected Calculator myCalculator;

  @Before
  public void setUp() {

    this.myCalculator = new SimpleCalculator();
  }


  // Exception Testing for invalid input throw/ catch
  @Test
  public void validateInputQuestionMark() {
    try {
      myCalculator.input('?');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid operator");
    }
  }


  @Test
  public void validateInputLetter() {
    try {
      myCalculator.input('B');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid operator");
    }
  }


  @Test
  public void validateInputSubOperatorBeforeNumber() {
    try {
      myCalculator.input('-').input('5');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }

  @Test
  public void validateInputAddOperatorBeforeNumber() {
    try {
      myCalculator.input('+').input('5');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }


  @Test
  public void validateInputMultiOperatorBeforeNumber() {
    try {
      myCalculator.input('*').input('5');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }


  @Test
  public void validateInputOperatorOperatorSubTractionAfterOperator() {
    try {
      myCalculator.input('7').input('-').input('-');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }

  @Test
  public void validateInputOperatorOperatorAdditionAfterOperator() {
    try {
      myCalculator.input('7').input('+').input('+');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }

  @Test
  public void validateInputOperatorOperatorMultipleAfterOperator() {
    try {
      myCalculator.input('7').input('*').input('*');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Must put in a number before an operator");
    }
  }


  // Exception Testing for invalid input expected Argument
  @Test(expected = IllegalArgumentException.class)
  public void validateInputWrongOperator() {

    myCalculator.input('/');
  }


  @Test(expected = IllegalArgumentException.class)
  public void validateInputOperateMultiFirstNoNumber() {

    myCalculator.input('*');

  }

  @Test(expected = IllegalArgumentException.class)
  public void validateInputOperateEqualSignFirstNoNumber() {

    myCalculator.input('*');

  }

  @Test(expected = IllegalArgumentException.class)
  public void validateInputOperateJustPuttingOne() {

    myCalculator.input('1');
    myCalculator.input('+');
    myCalculator.input('+');
  }


  // OVERFLOW TEST


  // expected exception class
  @Test(expected = IllegalArgumentException.class)
  public void inputOverFlowLefSideInput() {
    myCalculator.input('5').input('5').input('5').input('5').input('5').input('5')
            .input('5').input('5').input('5').input('5').input('5').input('5');


  }


  @Test(expected = IllegalArgumentException.class)
  public void inputOverFlowRightSideInput() {
    Calculator result = myCalculator.input('5');

    result.input('*').input('5').input('5').input('5').input('5')
            .input('5').input('5').input('5').input('5').input('5').input('5').input('5');

  }


  // Exception testing for throw/catch overflow
  @Test
  public void leftSideOverFlow() {
    try {
      myCalculator.input('5').input('5').input('5').input('5').input('5').input('5').input('5')
              .input('5').input('5').input('5').input('5');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "OVERFLOW!! Too much inputs were entered");
    }
  }


  @Test
  public void rightSideOverFlow() {
    try {
      myCalculator.input('5').input('+').input('5').input('5')
              .input('5').input('5').input('5').input('5')
              .input('5').input('5').input('5').input('5')
              .input('5').input('5').input('5').input('5');
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "OVERFLOW!! Too much inputs were entered");
    }
  }


  // Result OVer TESTING FOR SOLVING WITH ONE OPERATION
  @Test
  public void getResultOverFlowAnswerMultiple() {
    Calculator result = myCalculator.input('1');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');

    result = result.input('*');

    result = result.input('1').input('0').input('0').input('0').input('0').input('0');
    result = result.input('*');
    result = result.input('1');
    result = result.input('=');


    assertEquals("0", result.getResult());
  }

  @Test
  public void getResultOverFlowAnswerAddition() {

    //2147483647
    Calculator result = myCalculator.input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('7').input('+');

    assertEquals("2147483647+", result.getResult());


    result = result.input('1');
    result = result.input('=');


    assertEquals("0", result.getResult());
  }


  @Test
  public void getResultOverFlowAnswerSubtraction() {
    // Initialize the calculator and input the maximum int value
    Calculator result = myCalculator.input('5');
    result = result.input('-').input('6').input('=');

    // have number 6
    assertEquals("-1", result.getResult());

    result = result.input('*').input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('7').input('=');


    assertEquals("-2147483647", result.getResult());

    // Input a number that causes the result to overflow

    result = result.input('-').input('2').input('=');


    // Assert that the result is zero
    assertEquals("0", result.getResult());
  }


  @Test
  public void getResultOverFlowAnswerAllOperations() {

    //2147483647
    Calculator result = myCalculator.input('2').input('1').input('4').input('7').input('4')
            .input('8').input('3').input('6').input('4').input('7').input('-').input('0')
            .input('*').input('1').input('+');

    assertEquals("2147483647+", result.getResult());


    result = result.input('1');
    result = result.input('=');


    assertEquals("0", result.getResult());
  }


  // OverFLOWS BUT User use operant
  @Test
  public void testOperandOverflowLeftSideRetainValue() {
    Calculator result = myCalculator.input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1').input('1');


    assertEquals("1111111111", result.getResult());

    try {
      result = result.input('1');

    } catch (IllegalArgumentException e) {

      assertEquals("OVERFLOW!! Too much inputs were entered", e.getMessage());

      assertEquals("1111111111", result.getResult());
    }


    result = result.input('+').input('2').input('=');

    // Verify the result after using the retained operand
    assertEquals("1111111113", result.getResult());
  }


  @Test
  public void testOperandOverflowRightSideRetainValue() {
    Calculator result = myCalculator.input('1').input('*').input('1')
            .input('1').input('1').input('1').input('1').input('1').input('1')
            .input('1').input('1').input('1');


    // right side returns the answer
    assertEquals("1*1111111111", result.getResult());

    try {
      result = result.input('1');

    } catch (IllegalArgumentException e) {

      assertEquals("OVERFLOW!! Too much inputs were entered", e.getMessage());

      assertEquals("1*1111111111", result.getResult());
    }


    result = result.input('+').input('2').input('=');

    // Verify the result after using the retained operand
    assertEquals("1111111113", result.getResult());
  }


  // Would overflow addition
  @Test(expected = IllegalArgumentException.class)
  public void getResultOverFlowAnswerAdd() {
    Calculator result = myCalculator.input('1').input('1').input('1')
            .input('1').input('1').input('1')
            .input('1').input('1').input('1').input('1');

    // Adding one more digit to overflow
    result = result.input('+').input('9').input('9').input('9').input('9').input('9').input('9')
            .input('9').input('9').input('9').input('9').input('=');

    // This should return zero as the result due to overflow
    assertEquals("0", result.getResult());
  }


  // EDGE CASE TRYING OVERFLOW FOR SUBTRACT BECOME EXCEPTION
  @Test(expected = IllegalArgumentException.class)
  public void getResultOverFlowSubtraction() {
    Calculator result = myCalculator.input('1');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');
    result = result.input('0');

    result = result.input('-');

    result = result.input('1');
    result = result.input('=');

    assertEquals("OVERFLOW!! Too much inputs were entered", result.getResult());

  }


  /// equal sign edge case MULTIPLE EQUAL SIGN CASE
  @Test
  public void testContinuousEqualSignsAfterClear() {
    Calculator result = myCalculator.input('9').input('+').input('1').input('=')
            .input('C').input('5').input('+').input('1').input('=').input('=').input('=');

    assertEquals("6", result.getResult());
  }


  // EGGE CASE TRYING TO SOLVE WITHOUT AN OPERATION
  @Test(expected = IllegalArgumentException.class)
  public void testEqualSignWithoutOperation() {
    Calculator result = myCalculator.input('5').input('=').input('=').input('=');
    assertEquals("5", result.getResult());
  }


  @Test
  public void testMultipleEqualSignAfterOneOperator() {
    Calculator result = myCalculator.input('5').input('-').input('4')
            .input('=').input('=').input('=');


    assertEquals("1", result.getResult());

  }

  @Test
  public void testMultipleEqualSignAfterTwoOperator() {
    Calculator result = myCalculator.input('5').input('-').input('4').input('+')
            .input('6').input('=').input('=').input('=');


    assertEquals("7", result.getResult());

  }


  @Test
  public void testMultipleEqualSignAfterThreeOperator() {
    Calculator result = myCalculator.input('5').input('-').input('4').input('+')
            .input('6').input('*').input('0').input('=').input('=').input('=');


    assertEquals("0", result.getResult());

  }


  // Normal 2, 3 , 4  5 digits plug in numbers without solving
  @Test
  public void getResultRegularNumber() {
    Calculator result = myCalculator.input('6').input('-').input('5');

    assertEquals("6-5", result.getResult());
  }

  @Test
  public void testInput2Digits() {
    Calculator result = myCalculator.input('5').input('5').input('-').
            input('4').input('5');


    assertEquals("55-45", result.getResult());
  }

  @Test
  public void testInput3Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6')
            .input('-').input('4').input('5').input('6');


    assertEquals("556-456", result.getResult());
  }

  @Test
  public void testInput4Digits() {

    Calculator result = myCalculator.input('5').input('5').input('6').input('0').input('-')
            .input('4').input('5').input('6').input('0');


    assertEquals("5560-4560", result.getResult());
  }

  @Test
  public void testInput5Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('-').input('4').input('5').input('6').input('0').input('0');


    assertEquals("55600-45600", result.getResult());
  }

  @Test
  public void testInput6Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('-').input('4').input('5').input('6').input('0').input('0')
            .input('0');


    assertEquals("556000-456000", result.getResult());
  }

  @Test
  public void testInput7Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('-').input('4').input('5').input('6').input('0')
            .input('0').input('0').input('0');


    assertEquals("5560000-4560000", result.getResult());
  }

  @Test
  public void testInput8Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('0').input('-').input('4').input('5').input('6')
            .input('0').input('0').input('0').input('0').input('0');


    assertEquals("55600000-45600000", result.getResult());
  }

  @Test
  public void testInput9Digits() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('0').input('0').input('-')
            .input('4').input('5').input('6').input('0').input('0').input('0')
            .input('0').input('0').input('0');


    assertEquals("556000000-456000000", result.getResult());
  }

  // Test for basic computation with 2 digits without solving
  @Test
  public void testMulti1Num() {
    Calculator result = myCalculator.input('1').input('*').input('1');

    assertEquals("1*1", result.getResult());
  }

  @Test
  public void testSub1Num() {
    Calculator result = myCalculator.input('1').input('-').input('1');

    assertEquals("1-1", result.getResult());
  }

  @Test
  public void testAdd1Num() {
    Calculator result = myCalculator.input('1').input('+').input('1');

    assertEquals("1+1", result.getResult());
  }

  @Test
  public void testMulti2Num() {
    Calculator result = myCalculator.input('1').input('0').input('*').input('1').input('0');

    assertEquals("10*10", result.getResult());
  }

  @Test
  public void testSub2Num() {
    Calculator result = myCalculator.input('1').input('0').input('-').input('1').input('0');

    assertEquals("10-10", result.getResult());
  }

  @Test
  public void testAdd2Num() {
    Calculator result = myCalculator.input('1').input('0').input('+').input('1').input('0');

    assertEquals("10+10", result.getResult());
  }

  @Test
  public void testMulti3Num() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('*').input('1')
            .input('0').input('0');

    assertEquals("100*100", result.getResult());
  }

  @Test
  public void testSub3Num() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('-').input('1')
            .input('0').input('0');


    assertEquals("100-100", result.getResult());
  }

  @Test
  public void testAdd3Num() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('+').input('1')
            .input('0').input('0');

    assertEquals("100+100", result.getResult());
  }


  @Test
  public void testAdd9Num() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('0').input('0').input('+')
            .input('4').input('5').input('6').input('0').input('0').input('0')
            .input('0').input('0').input('0');


    assertEquals("556000000+456000000", result.getResult());
  }

  @Test
  public void testSub9Num() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('0').input('0').input('-')
            .input('4').input('5').input('6').input('0').input('0').input('0')
            .input('0').input('0').input('0');


    assertEquals("556000000-456000000", result.getResult());
  }


  @Test
  public void testMulti9Num() {
    Calculator result = myCalculator.input('5').input('5').input('6').input('0')
            .input('0').input('0').input('0').input('0').input('0').input('*')
            .input('4').input('5').input('6').input('0').input('0').input('0')
            .input('0').input('0').input('0');


    assertEquals("556000000*456000000", result.getResult());
  }


  // withOUT Solving by adding another operator
  @Test
  public void testMulti1AnotherOperator() {
    Calculator result = myCalculator.input('1').input('*').input('1').input('*');


    assertEquals("1*", result.getResult());
  }

  @Test
  public void testSub1AnotherOperator() {
    Calculator result = myCalculator.input('1').input('-').input('1').input('-');


    assertEquals("0-", result.getResult());
  }

  @Test
  public void testAdd1AnotherOperator() {
    Calculator result = myCalculator.input('1').input('+').input('1').input('+');


    assertEquals("2+", result.getResult());
  }


  @Test
  public void testInput() {
    Calculator result = myCalculator.input('5').input('-').input('4');


    assertEquals("5-4", result.getResult());

    result = result.input('+');

    assertEquals("1+", result.getResult());
  }


  // TEST CASES FOR ADDING ANOTHER OPERATOR WITH 2 DIGESTS WITHOUT EQUAL SIGN
  @Test
  public void testMulti2AnotherOperator() {
    Calculator result = myCalculator.input('1').input('0')
            .input('*').input('1').input('0').input('*');


    assertEquals("100*", result.getResult());
  }

  @Test
  public void testSub2NumSolve() {
    Calculator result = myCalculator.input('1').input('0').input('-')
            .input('1').input('0').input('-');

    assertEquals("0-", result.getResult());
  }


  @Test
  public void testAdd2NumSolve() {
    Calculator result = myCalculator.input('1').input('0').input('+')
            .input('1').input('0').input('+');


    assertEquals("20+", result.getResult());
  }


  // Test for basic computation with four digits WITH SOLVING
  @Test
  public void testAdd4Num() {
    Calculator result = myCalculator.input('7')
            .input('2')
            .input('9')
            .input('3')
            .input('+')
            .input('3')
            .input('2')
            .input('9')
            .input('0')
            .input('=');

    assertEquals("10583", result.getResult());
  }


  @Test
  public void testSubtract4Num() {
    Calculator result = myCalculator.input('7').input('2').input('9')
            .input('3').input('-').input('3').input('2').input('9').input('0').input('=');

    assertEquals("4003", result.getResult());
  }

  @Test
  public void testMulti4Num() {
    Calculator result = myCalculator.input('7').input('2').input('9')
            .input('3').input('*').input('3').input('2').input('9').input('0').input('=');

    assertEquals("23993970", result.getResult());
  }


  @Test
  public void testMulti45Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0').input('0') // 5000
            .input('*')
            .input('4').input('0').input('0').input('0').input('0') // 4000
            .input('=');

    assertEquals("2000000000", result.getResult());
  }

  @Test
  public void testAdd5Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0').input('0') // 5000
            .input('+')
            .input('4').input('0').input('0').input('0').input('0') // 4000
            .input('=');

    assertEquals("90000", result.getResult());
  }


  @Test
  public void tesSub5Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0')
            .input('0')
            .input('-')
            .input('4').input('0').input('0').input('0').input('0')
            .input('=');

    assertEquals("10000", result.getResult());
  }


  @Test
  public void testMulti6Num() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0').input('0')
            .input('0')
            .input('*')
            .input('1').input('0').input('0').input('0').input('0').input('0')
            .input('=');

    assertEquals("0", result.getResult());
  }

  @Test
  public void tesSub6Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0').input('0')
            .input('0')
            .input('-')
            .input('4').input('0').input('0').input('0').input('0').input('0')
            .input('=');

    assertEquals("100000", result.getResult());
  }

  @Test
  public void testAdd6Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0').input('0')
            .input('0')
            .input('+')
            .input('4').input('0').input('0').input('0').input('0').input('0')
            .input('=');

    assertEquals("900000", result.getResult());
  }


  // ALSO OVERFLOW TEST RESULT
  @Test
  public void testMultiple7Num() {
    Calculator result = myCalculator.input('5').input('0').input('0').input('0').input('0')
            .input('0')
            .input('*')
            .input('4').input('0').input('0').input('0').input('0').input('0')
            .input('=');

    assertEquals("0", result.getResult());
  }

  // ALSO NEGATIVE RESULT
  @Test
  public void testSubtractWith8Num() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0').input('0')
            .input('0').input('0').input('0')
            .input('-')
            .input('9').input('9').input('9').input('9').input('9').input('9').input('9').input('9')
            .input('=');

    assertEquals("-89999999", result.getResult());
  }


  @Test
  public void testAddWith9Num() {
    Calculator result = myCalculator.input('9').input('9').input('9').input('9').input('9')
            .input('9').input('9').input('9').input('9')
            .input('+')
            .input('1').input('0').input('0').input('0').input('0').input('0').input('0').input('0')
            .input('0')
            .input('=');

    assertEquals("1099999999", result.getResult());
  }


  /// test for getting negative number using the operators
  @Test
  public void getResultNegativeNumberSubtract() {
    Calculator result = myCalculator.input('5').input('-').input('6').input('-');

    assertEquals("-1-", result.getResult());
  }

  @Test
  public void getResultNegativeNumberAdd() {
    Calculator result = myCalculator.input('2').input('-').input('5').input('+').input('1')
            .input('+');

    assertEquals("-2+", result.getResult());
  }


  @Test
  public void getResultNegativeNumberMultiple() {
    Calculator result = myCalculator.input('2').input('-').input('5').input('*').input('1')
            .input('*');

    assertEquals("-3*", result.getResult());
  }

  @Test
  public void getResultNegativeNumberAllOperators() {
    Calculator result = myCalculator.input('6').input('-').input('8');

    assertEquals("6-8", result.getResult());

    result = result.input('*');

    assertEquals("-2*", result.getResult());

    result = result.input('2');

    assertEquals("-2*2", result.getResult());

    result = result.input('-');

    assertEquals("-4-", result.getResult());

    result = result.input('1');

    assertEquals("-4-1", result.getResult());

    result = result.input('=');

    assertEquals("-5", result.getResult());

  }


  // SOLVING
  @Test
  public void getResultPositiveNumberAllOperatorsLongEquation() {
    Calculator result = myCalculator.input('8').input('-').input('6');

    assertEquals("8-6", result.getResult());

    result = result.input('*');

    assertEquals("2*", result.getResult());

    result = result.input('2');

    assertEquals("2*2", result.getResult());

    result = result.input('-');

    assertEquals("4-", result.getResult());

    result = result.input('1');

    assertEquals("4-1", result.getResult());

    result = result.input('=');

    assertEquals("3", result.getResult());

  }


  // TEST FOR CLEARING NUMBERS
  @Test
  public void testAdd4NumClear() {
    Calculator result = myCalculator.input('7')
            .input('2')
            .input('9')
            .input('3')
            .input('+')
            .input('3')
            .input('2')
            .input('9')
            .input('0')
            .input('=');

    assertEquals("10583", result.getResult());

    result = result.input('C');

    assertEquals("", result.getResult());
  }

  @Test
  public void tesSub4NumClear() {
    Calculator result = myCalculator.input('7')
            .input('2')
            .input('9')
            .input('3')
            .input('-')
            .input('3')
            .input('2')
            .input('9')
            .input('0')
            .input('=');

    assertEquals("4003", result.getResult());

    result = result.input('C');

    assertEquals("", result.getResult());
  }

  @Test
  public void testMulti4NumClear() {
    Calculator result = myCalculator.input('7')
            .input('2')
            .input('9')
            .input('3')
            .input('*')
            .input('3')
            .input('2')
            .input('9')
            .input('0')
            .input('=');

    assertEquals("23993970", result.getResult());

    result = result.input('C');

    assertEquals("", result.getResult());
  }


  @Test
  public void testClearingWithEquationCheckEachStep() {
    Calculator result = myCalculator.input('6').input('-').input('3');

    assertEquals("6-3", result.getResult());

    result = result.input('=').input('C');


    assertEquals("", result.getResult());
  }

  @Test
  public void testClearingWithEquationButStartingNewEquationCheckEachStep() {
    Calculator result = myCalculator.input('6').input('-').input('3');

    assertEquals("6-3", result.getResult());

    result = result.input('=').input('C').input('6').input('*').input('3').input('=');

    assertEquals("18", result.getResult());
  }


  // test for adding operator after one equal sign
  @Test
  public void testSubOperatorAlready1EqualSign() {
    Calculator result = myCalculator.input('1');

    result = result.input('+').input('2').input('=').input('3').input('+').input('4');


    assertEquals("3+4", result.getResult());

  }


  // test for adding operator after two equal sign
  @Test
  public void testAddOperatorAlready2EqualSign() {
    Calculator result = myCalculator.input('1');

    result = result.input('+').input('2').input('=').input('3')
            .input('+').input('4').input('=');

    assertEquals("7", result.getResult());

  }

  @Test
  public void testRandomOperatorAlready2EqualSign() {
    Calculator result = myCalculator.input('1');

    result = result.input('+').input('2').input('=').input('3')
            .input('+').input('4').input('=').input('3')
            .input('+').input('4');

    assertEquals("3+4", result.getResult());

  }

  @Test
  public void tesWithNumberOperatorAlready2EqualSignMulti() {
    Calculator result = myCalculator.input('1');

    result = result.input('*').input('2').input('=').input('3')
            .input('*').input('4').input('=').input('3')
            .input('*').input('4');

    assertEquals("3*4", result.getResult());

  }

  @Test
  public void tesWithNumberOperatorAlready2EqualSignSubtract() {
    Calculator result = myCalculator.input('1');

    result = result.input('-').input('2').input('=').input('3')
            .input('-').input('4').input('=').input('3')
            .input('-').input('4');

    assertEquals("3-4", result.getResult());

  }


  // LARGEST NUMBER
  @Test(expected = IllegalArgumentException.class)
  public void testLargeNumberAddition() {
    Calculator result = myCalculator.input('9').input('9').input('9').input('9').input('9')
            .input('9').input('9').input('9').input('9').input('9').input('+').input('1')
            .input('=');


    assertEquals("OVERFLOW!! Too much inputs were entered", result.getResult());
  }

  @Test
  public void testLargeNumberMultiplication() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0').input('0')
            .input('0').input('*').input('1').input('0').input('0').input('0').input('0').
            input('0').input('=');
    assertEquals("0", result.getResult());
  }

  // Multiple operations
  @Test
  public void testMultipleOperations() {
    Calculator result = myCalculator.input('2').input('+').input('3').input('*')
            .input('4').input('=');
    assertEquals("20", result.getResult());
  }

  @Test
  public void testMultipleDifferentOperators() {
    Calculator result = myCalculator.input('2').input('+').input('3')
            .input('-').input('1').input('=');
    assertEquals("4", result.getResult());
  }


  // test for adding operator after three equal sign
  @Test
  public void tesWithNumberOperatorAlreadyHas3EqualSignsEqualSignMulti() {
    Calculator result = myCalculator.input('1');

    result = result.input('*').input('2').input('=').input('3')
            .input('*').input('4').input('=').input('3')
            .input('*').input('4').input('=');

    assertEquals("12", result.getResult());

  }


  // test for adding operator after three equal sign
  @Test
  public void tesWithNumberOperatorAlreadyHas6EqualSignsEqualSignMulti() {
    Calculator result = myCalculator.input('1');

    result = result.input('*').input('2').input('=').input('3')
            .input('*').input('4').input('=').input('3').input('*')
            .input('4').input('=').input('3').input('*').input('2')
            .input('=').input('3').input('*').input('4').input('=')
            .input('3').input('*').input('4').input('=');

    assertEquals("12", result.getResult());
  }


  // test for adding operator after three equal sign
  @Test
  public void tesWithNumberOperatorAlreadyHas3EqualSignsEqualSignMultiCheckEveryInput() {
    Calculator result = myCalculator.input('1');

    assertEquals("1", result.getResult());


    result = result.input('*');
    assertEquals("1*", result.getResult());


    result = result.input('1');

    assertEquals("1*1", result.getResult());


    result = result.input('=');

    assertEquals("1", result.getResult());


  }

  @Test(expected = IllegalArgumentException.class)
  public void testLeadingZeros() {
    Calculator result = myCalculator.input('0').input('0').input('1').input('0').input('=');
    assertEquals("Invalid operator", result.getResult());
  }


}
