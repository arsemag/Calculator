import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * A test class to make sure that the methods for Smart Calculator are working
 * properly. Looks for edge cases and makes sure that all conditions are checked.
 */
public class SmartCalculatorTest extends SimpleCalculatorTest {

  @Before
  public void setup() {
    myCalculator = new SmartCalculator();
  }


  @Override
  @Test
  public void testMultipleEqualSignAfterOneOperator() {
    Calculator result = myCalculator.input('5').input('-').input('4')
            .input('=').input('=').input('=');


    assertEquals("-7", result.getResult());
  }

  @Override
  @Test
  public void testMultipleEqualSignAfterTwoOperator() {
    Calculator result = myCalculator.input('5').input('-').input('4').input('+')
            .input('6').input('=').input('=').input('=');


    assertEquals("19", result.getResult());

  }

  @Override
  @Test
  public void testContinuousEqualSignsAfterClear() {
    Calculator result = myCalculator.input('9').input('+').input('1').input('=')
            .input('C').input('5').input('+').input('1').input('=').input('=').input('=');

    assertEquals("8", result.getResult());
  }

  @Override
  @Test
  public void testLeadingZeros() {
    Calculator result = myCalculator.input('0').input('0').input('1').input('0').input('=');
    assertEquals("10", result.getResult());
  }

  @Override
  @Test
  public void validateInputOperateJustPuttingOne() {

    Calculator result = myCalculator.input('1').input('+').input('+');

    assertEquals("1+", result.getResult());
  }

  @Override
  @Test
  public void testEqualSignWithoutOperation() {
    Calculator result = myCalculator.input('5').input('=').input('=').input('=');
    assertEquals("5", result.getResult());
  }


  // TODO EXCEPTION TEST

  // testing for with  operators in the beginning of the equation

  @Test(expected = IllegalArgumentException.class)
  public void TwoOperatorInTheBeginningSubSubOneDigit() {
    myCalculator.input('-').input('3').input('2')
            .input('-').input('2').input('4');
  }

  @Test(expected = IllegalArgumentException.class)
  public void NumberOperatorSubBeforeEquation() {
    Calculator result = myCalculator.input('=').input('5').input('*');

    assertEquals("5*", result.getResult());

    result = result.input('=').input('=');

    assertEquals("32798", result.getResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void NumberOperatorMultiBeforeEquation() {
    Calculator result = myCalculator.input('*').input('5').input('*');

    assertEquals("32*", result.getResult());

    result = result.input('=').input('=');

    assertEquals("32798", result.getResult());
  }


  // TODO MULTIPLE EQUAL SIGN

  // Multiple Equal Sign OneDigit  inputs
  @Test
  public void TwoEqualSignOneDigitAdd() {
    Calculator result = myCalculator.input('5').input('+').input('6');

    assertEquals("5+6", result.getResult());

    result = result.input('=');

    assertEquals("11", result.getResult());

    result = result.input('=');

    assertEquals("17", result.getResult());
  }


  @Test
  public void TwoEqualSignOneDigitSub() {
    Calculator result = myCalculator.input('6').input('-').input('5');

    assertEquals("6-5", result.getResult());

    result = result.input('=');

    assertEquals("1", result.getResult());

    result = result.input('=');

    assertEquals("-4", result.getResult());
  }

  @Test
  public void TwoEqualSignOneDigitMulti() {
    Calculator result = myCalculator.input('6').input('*').input('5');

    assertEquals("6*5", result.getResult());

    result = result.input('=');

    assertEquals("30", result.getResult());

    result = result.input('=');

    assertEquals("150", result.getResult());
  }


  // Multiple Equal Sign TwoDigit  inputs
  @Test
  public void TwoEqualSignTwoDigitAdd() {
    Calculator result = myCalculator.input('1').input('0').input('+').input('1').input('0');

    assertEquals("10+10", result.getResult());

    result = result.input('=');

    assertEquals("20", result.getResult());

    result = result.input('=');

    assertEquals("30", result.getResult());
  }


  @Test
  public void TwoEqualSignTwoDigitSub() {
    Calculator result = myCalculator.input('1').input('0').input('-').input('1').input('0');

    assertEquals("10-10", result.getResult());

    result = result.input('=');

    assertEquals("0", result.getResult());

    result = result.input('=');

    assertEquals("-10", result.getResult());
  }

  @Test
  public void TwoEqualSignTwoDigitMulti() {
    Calculator result = myCalculator.input('1').input('0').input('*').input('1').input('0');

    assertEquals("10*10", result.getResult());

    result = result.input('=');

    assertEquals("100", result.getResult());

    result = result.input('=');

    assertEquals("1000", result.getResult());
  }


  // Multiple Equal Sign 3 Digits
  @Test
  public void TwoEqualSignThreeDigitAdd() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('+')
            .input('1').input('0').input('0');

    assertEquals("100+100", result.getResult());

    result = result.input('=');

    assertEquals("200", result.getResult());

    result = result.input('=');

    assertEquals("300", result.getResult());
  }

  @Test
  public void TwoEqualSignThreeDigitSub() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('-')
            .input('1').input('0').input('0');

    assertEquals("100-100", result.getResult());

    result = result.input('=');

    assertEquals("0", result.getResult());

    result = result.input('=');

    assertEquals("-100", result.getResult());
  }

  @Test
  public void TwoEqualSignThreeDigitMulti() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('*').input('1')
            .input('0').input('0');

    assertEquals("100*100", result.getResult());

    result = result.input('=');

    assertEquals("10000", result.getResult());

    result = result.input('=');

    assertEquals("1000000", result.getResult());
  }


  // Multiple Equal Sign 4 digits
  @Test
  public void TwoEqualSignFourDigitAdd() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0').input('+')
            .input('1').input('0').input('0').input('0');

    assertEquals("1000+1000", result.getResult());

    result = result.input('=');

    assertEquals("2000", result.getResult());

    result = result.input('=');

    assertEquals("3000", result.getResult());
  }

  @Test
  public void TwoEqualSignFourDigitSub() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0').input('-')
            .input('1').input('0').input('0').input('0');

    assertEquals("1000-1000", result.getResult());

    result = result.input('=');

    assertEquals("0", result.getResult());

    result = result.input('=');

    assertEquals("-1000", result.getResult());
  }

  @Test
  public void TwoEqualSignFourDigitMulti() {
    Calculator result = myCalculator.input('1').input('0').input('0').input('0')
            .input('*').input('1').input('0').input('0').input('0');

    assertEquals("1000*1000", result.getResult());

    result = result.input('=');

    assertEquals("1000000", result.getResult());

    result = result.input('=');

    assertEquals("1000000000", result.getResult());
  }


  /* testing for with multiple operators in the middle of the equation */
  @Test
  public void TwoOperatorInMiddleAddSubTwoDigit() {
    Calculator result = myCalculator.input('1').input('0').input('+').input('-')
            .input('1').input('0').input('=');

    assertEquals("0", result.getResult());
  }


  @Test
  public void TwoOperatorInMiddleMultiTwoDigit() {
    Calculator result = myCalculator.input('1').input('0').input('-').input('*')
            .input('1').input('0').input('=');

    assertEquals("100", result.getResult());
  }


  @Test
  public void TwoOperatorInMiddleAddTwoDigit() {
    Calculator result = myCalculator.input('1').input('0').input('+').input('*')
            .input('1').input('0').input('0').input('=');

    assertEquals("1000", result.getResult());
  }


  // TODO ADDITION SIGN IN THE FRONT TESTING
  @Test
  public void TwoOperatorInTheBeginningAddSubOneDigit() {
    Calculator result = myCalculator.input('+').input('3').input('2')
            .input('-').input('2').input('4');

    assertEquals("32-24", result.getResult());

  }

  @Test
  public void TwoOperatorInTheBeginningAddSubOneDigitSolve() {
    Calculator result = myCalculator.input('+').input('3').input('2')
            .input('-').input('2').input('4');

    assertEquals("32-24", result.getResult());

    result = result.input('=');

    assertEquals("8", result.getResult());
  }


  @Test
  public void testEqualSign3() {
    Calculator result = myCalculator.input('+');

    assertEquals("", result.getResult());
  }

  @Test
  public void testNewEquationWithAdditionSign() {
    Calculator result = myCalculator.input('3').input('3').input('+')
            .input('1').input('0').input('=');

    assertEquals("43", result.getResult());

    result = result.input('+').input('1');

    assertEquals("43+1", result.getResult());

    result = result.input('=');

    assertEquals("44", result.getResult());

  }


  @Test
  public void NumberOperatorAddEqualSign() {
    Calculator result = myCalculator.input('3').input('2').input('+');

    assertEquals("32+", result.getResult());

    result = result.input('=');

    assertEquals("64", result.getResult());
  }

  @Test
  public void NumberOperatorMultiEqualSign() {
    Calculator result = myCalculator.input('3').input('2').input('*');

    assertEquals("32*", result.getResult());

    result = result.input('=');

    assertEquals("1024", result.getResult());
  }

  @Test
  public void NumberOperatorSubEqualSign() {
    Calculator result = myCalculator.input('3').input('2').input('-');

    assertEquals("32-", result.getResult());

    result = result.input('=');

    assertEquals("0", result.getResult());
  }

  @Test
  public void NumberOperatorMulti2EqualSign() {
    Calculator result = myCalculator.input('3').input('2').input('*');

    assertEquals("32*", result.getResult());

    // 1024*=
    result = result.input('=').input('=');

    assertEquals("32768", result.getResult());
  }


  @Test
  public void testOperandAfterEqualSign() {

    Calculator result = myCalculator.input('3').input('2').input('+').input('4')
            .input('=').input('+').input('1').input('=');

    assertEquals("37", result.getResult());
  }

  @Test
  public void testOperandAfterEqualSign2() {

    Calculator result = myCalculator.input('3').input('2').input('+').input('4').input('=')
            .input('5').input('+').input('1').input('=');

    assertEquals("6", result.getResult());
  }


  @Test
  public void testOperandAfterEqualSignClear() {

    Calculator result = myCalculator.input('3').input('2').input('+').input('4').input('=')
            .input('5').input('+').input('1').input('=').input('C');

    assertEquals("", result.getResult());
  }


  @Test
  public void testOperandAfterEqualSignSpecialCase() {

    Calculator result = myCalculator.input('3').input('2').input('+').input('-').input('4')
            .input('=').input('=').input('=').input('=');

    assertEquals("16", result.getResult());
  }


  @Test
  public void testOperandAfterEqualSignSpecialCase2() {

    Calculator result = myCalculator.input('+').input('2').input('*').input('2').input('=');

    assertEquals("4", result.getResult());
  }


  @Test
  public void testEqualSign() {
    Calculator result = myCalculator.input('5').input('+').input('+').input('+');

    assertEquals("5+", result.getResult());
  }

  @Test
  public void testEqualSign2() {
    Calculator result = myCalculator.input('2').input('+').input('2').input('+');

    assertEquals("4+", result.getResult());
  }


  @Test
  public void testAnswer2DigitsAdd() {
    Calculator result = myCalculator.input('1').input('4').input('+')
            .input('4').input('4').input('=');


    assertEquals("58", result.getResult());
  }

  @Test
  public void testAnswer2DigitsSub() {
    Calculator result = myCalculator.input('1').input('4').input('-')
            .input('4').input('4').input('=');


    assertEquals("-30", result.getResult());
  }

  @Test
  public void testAnswer2DigitsMulti() {
    Calculator result = myCalculator.input('1').input('4').input('*').input('4')
            .input('4').input('=');


    assertEquals("616", result.getResult());
  }


  @Test
  public void testNewEquationEquationMultiEqualSign() {
    Calculator result = myCalculator.input('1').input('1').input('4').input('*')
            .input('4').input('4').input('=').input('5').input('+').input('5').input('=');


    assertEquals("10", result.getResult());
  }


  @Test
  public void testNewEquationEquationMulti2EqualSign() {
    Calculator result = myCalculator.input('1').input('1').input('4').input('*')
            .input('4').input('4').input('=').input('5').input('*').input('5')
            .input('=').input('=');


    assertEquals("125", result.getResult());
  }


  @Test
  public void testNewEquationEquationAdd2EqualSign() {
    Calculator result = myCalculator.input('1').input('1').input('4').input('*')
            .input('4').input('4').input('=').input('5').input('+').input('5')
            .input('=').input('=');


    assertEquals("15", result.getResult());
  }


  // Basic Arithmetic Operations
  @Test
  public void testAddition() {
    Calculator result = myCalculator.input('5').input('+').input('3').input('=');
    assertEquals("8", result.getResult());
  }

  @Test
  public void testSubtraction() {
    Calculator result = myCalculator.input('8').input('-').input('3').input('=');
    assertEquals("5", result.getResult());
  }

  @Test
  public void testMultiplication() {
    Calculator result = myCalculator.input('4').input('*').input('3').input('=');
    assertEquals("12", result.getResult());
  }

  // Multiple Equals Handling
  @Test
  public void testRepeatedEqualSignsAdd() {
    Calculator result = myCalculator.input('5').input('+').input('3').input('=')
            .input('=').input('=');

    assertEquals("14", result.getResult());
  }

  @Test
  public void testRepeatedEqualSignsSub() {
    Calculator result = myCalculator.input('8').input('-').input('3').input('=')
            .input('=').input('=');

    assertEquals("-1", result.getResult());
  }

  @Test
  public void testRepeatedEqualSignsMul() {
    Calculator result = myCalculator.input('4').input('*').input('3')
            .input('=').input('=').input('=');

    assertEquals("108", result.getResult());
  }

  // Clear Functionality
  @Test
  public void testClearAndContinueAdd() {
    Calculator result = myCalculator.input('5').input('+').input('3').input('=').input('C')
            .input('2').input('+').input('3').input('=');
    assertEquals("5", result.getResult());
  }

  @Test
  public void testClearAndContinueSub() {
    Calculator result = myCalculator.input('8').input('-').input('3').input('=').input('C')
            .input('2').input('-').input('1').input('=');

    assertEquals("1", result.getResult());
  }

  @Test
  public void testClearAndContinueMul() {
    Calculator result = myCalculator.input('4').input('*').input('3').input('=')
            .input('C').input('2').input('*').input('2').input('=');

    assertEquals("4", result.getResult());
  }

  // Leading Zeros
  @Test
  public void testLeadingZero() {
    Calculator result = myCalculator.input('0').input('5').input('+').input('3').input('=');
    assertEquals("8", result.getResult());
  }

  @Test
  public void testMultipleLeadingZeros() {
    Calculator result = myCalculator.input('0').input('0')
            .input('5').input('+').input('3').input('=');

    assertEquals("8", result.getResult());
  }

  // Edge Cases and Exceptions
  @Test
  public void testStartingWithOperator() {
    Calculator result = myCalculator.input('+').input('3').input('*').input('2');

    assertEquals("3*2", result.getResult());
  }


  @Test
  public void testLargeNumberHandling() {
    try {
      myCalculator.input('5').input('+').input('5').input('5').input('5')
              .input('5').input('5').input('5').input('5').input('5')
              .input('5').input('5').input('5').input('5').input('5').input('5').input('=');

    } catch (IllegalArgumentException e) {
      assertEquals("OVERFLOW!! Too much inputs were entered", e.getMessage());
    }
  }

  // Complex Expressions
  @Test
  public void testMixedOperationsAddSub() {
    Calculator result = myCalculator.input('5').input('+')
            .input('3').input('-').input('2').input('=');

    assertEquals("6", result.getResult());
  }

  @Test
  public void testMixedOperationsMulAdd() {
    Calculator result = myCalculator.input('5').input('*').input('3').input('+')
            .input('2').input('=');

    assertEquals("17", result.getResult());
  }

  @Test
  public void testMixedOperationsMulSub() {
    Calculator result = myCalculator.input('5').input('*').input('3').input('-')
            .input('2').input('=');

    assertEquals("13", result.getResult());
  }

  // Smart Calculator Specific Tests
  @Test
  public void testOperatorAfterEqualSign() {
    Calculator result = myCalculator.input('5').input('+').input('3').input('=')
            .input('+').input('2').input('=');
    assertEquals("10", result.getResult());
  }

  @Test
  public void testClearAndContinueAfterEquals() {
    Calculator result = myCalculator.input('5').input('+').input('3').input('=')
            .input('C').input('2').input('+').input('3').input('=');
    assertEquals("5", result.getResult());
  }


  @Test
  public void testMissingSecondOperand() {
    Calculator result = myCalculator.input('3').input('2').input('+').input('=');

    assertEquals("64", result.getResult());
  }

  @Test
  public void testArithmeticOverflow() {
    Calculator result = myCalculator.input('1');
    for (int i = 0; i < 100; i++) { // This should cause an overflow
      result = result.input('*').input('2');
    }
    result = result.input('=');
    assertEquals("0", result.getResult());
  }


  @Test
  public void testGetResultAfterFirstOperator() {
    Calculator result = myCalculator.input('3').input('2').input('+');
    assertEquals("32+", result.getResult());
  }

  @Test
  public void test3Operators() {
    Calculator result = myCalculator.input('3').input('2').input('+').input('*').input('-')
            .input('1').input('=');

    assertEquals("-1", result.getResult());
  }


}