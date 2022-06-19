import static org.junit.Assert.assertEquals;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Test {


    /**Testing the case when there is 1 digit*/
    @org.junit.Test
    public void testOneDigit() {
        String inputTest = "digits = \"2\"";
        ArrayList<String> combinations = Main.lettercombinations(inputTest);
        String expectedOutput = "[\"a\", \"b\", \"c\"]";
        assertEquals("Testing one digit",expectedOutput, combinations.toString());
    }

    /**Testing the case when there are 2 digits*/
    @org.junit.Test
    public void testTwoDigits() {
        String inputTest = "digits = \"96\"";
        ArrayList<String> combinations = Main.lettercombinations(inputTest);
        String expectedOutput = "[\"wm\", \"wn\", \"wo\", \"xm\", \"xn\", \"xo\", \"ym\", \"yn\", \"yo\", \"zm\", \"zn\", \"zo\"]";
        assertEquals("Testing two digits",expectedOutput, combinations.toString());
    }

    /**Testing the case when there are 3 digits*/
    @org.junit.Test
    public void testThreeDigits() {
        String inputTest = "digits = \"443\"";
        ArrayList<String> combinations = Main.lettercombinations(inputTest);
        String expectedOutput = "[\"ggd\", \"gge\", \"ggf\", \"ghd\", \"ghe\", \"ghf\", \"gid\", \"gie\", \"gif\", \"hgd\", \"hge\", \"hgf\", \"hhd\", \"hhe\", \"hhf\", \"hid\", \"hie\", \"hif\", \"igd\", \"ige\", \"igf\", \"ihd\", \"ihe\", \"ihf\", \"iid\", \"iie\", \"iif\"]";
        assertEquals("Testing three digits",expectedOutput, combinations.toString());
    }

    /**Testing the case when there are no digits*/
    @org.junit.Test
    public void testZeroDigit() {
        String inputTest = "digits = \"\"";
        ArrayList<String> combinations = Main.lettercombinations(inputTest);
        String expectedOutput = "[]";
        assertEquals("Testing zero digits",expectedOutput, combinations.toString());
    }


    /**Testing exceptions: Digits include 0*/
    @org.junit.Test(expected = InvalidParameterException.class)
    public void testDigitsIncludeZero() {
        String inputTest = "digits = \"408\"";
        Main.lettercombinations(inputTest);
    }

    /**Testing exceptions: Digits include 1*/
    @org.junit.Test(expected = InvalidParameterException.class)
    public void testDigitsIncludeOne() {
        String inputTest = "digits = \"12\"";
        Main.lettercombinations(inputTest);
    }

    /**Testing exceptions: More than 4 digits*/
    @org.junit.Test(expected = InvalidParameterException.class)
    public void testTooManyDigits() {
        String inputTest = "digits = \"88562\"";
        Main.lettercombinations(inputTest);
    }

    /**Testing exceptions: Input does not start with "digits = "*/
    @org.junit.Test(expected = InvalidParameterException.class)
    public void testNotValidInputStart() {
        String inputTest = "data: \"42\"";
        Main.lettercombinations(inputTest);
    }

    /**Testing exceptions: Digits are not in ""*/
    @org.junit.Test(expected = InvalidParameterException.class)
    public void testThereIsNoQuotationMark() {
        String inputTest = "digits = 42 ";
        Main.lettercombinations(inputTest);
    }


}
