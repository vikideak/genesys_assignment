import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

    /** Hashmap to store the digits and the letters  */
    static HashMap<Integer,String> characters = new HashMap<Integer, String>(){{
        put(1, "");
        put(2,"abc");
        put(3,"def");
        put(4,"ghi");
        put(5,"jkl");
        put(6,"mno");
        put(7,"pqrs");
        put(8,"tuv");
        put(9,"wxyz");
        put(0, " ");
    }} ;

    /** Inserts character to a string **/
    public static String insertChar(String str, char what, int indexWhere) {
        String strBegin = str.substring(0,indexWhere);
        String strEnd = str.substring(indexWhere);
        return strBegin + what + strEnd;
    }

    /** Checks whether input digit is an integer */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        else if(str.equals("")){
            return true;
        }

        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**Checks whether the first part of the input is correct*/
    public static boolean correctFirstPartOfInput(String input, String expected) {

        try {
            String firstPartOfInput = input.substring(0, 9);
            return expected.equals(firstPartOfInput);
        } catch (Exception e) {
            return false;
        }
    }

    /**Extracts the digits from the input*/
    public static String getDigits(String inputLine) {
        //Extracting the digits between "" from the input string
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(inputLine);

        String digits = "";

        //This parameter is needed to check whether there are Quotation marks in the input
        boolean foundQuotation = false;

        while (m.find()) {
            digits += m.group(1);
            foundQuotation = true;
        }

        String firstPartMustBe = "digits = ";

        //Check whether the input format is valid
        if(!foundQuotation || !correctFirstPartOfInput(inputLine, firstPartMustBe) || !isNumeric(digits)) {
            throw new InvalidParameterException();
        }
        return digits;
    }

    /**The function for getting the combinations*/
    public static ArrayList<String> lettercombinations(String inputLine) {

        String digits = getDigits(inputLine);

        //The letter combinations for the output
        ArrayList<String> combinations = new ArrayList<>();

        int digitLength = digits.length();

            //If there are no digits, the output is an empty Arraylist
            if (digitLength > 0) {
                //If there are more than 4 digits, throws an Exception
                if(digitLength <= 4) {
                    combinations.add('"' + "" + '"');

                    //Loop for all extracted digits
                    for (int i = 0; i < digitLength; i++) {
                        int digit = Character.getNumericValue(digits.charAt(i));

                        //0 and 1 do not map to any letters, if the digits include 0 or 1, throws an Exception
                        if (digit > 1) {
                            String charsOfDigit = characters.get(digit);

                            ArrayList<String> temp = new ArrayList<>();

                            //Loop for all already existing combinations
                            for (String combination : combinations) {
                                for (int k = 0; k < charsOfDigit.length(); k++) {
                                    //Extending the combinations with the new character
                                    temp.add(insertChar(combination, charsOfDigit.charAt(k), combination.length() - 1));
                                }
                            }
                            combinations = temp;
                        }
                        else {
                            throw new InvalidParameterException();
                        }
                    }
                }
                else {
                    throw new InvalidParameterException();
                }
            }
        //The combinations ArrayList is already sorted alphabetically
        return combinations;
    }


    public static void main(String[] args) {

        //Reading data from console
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        try {
            System.out.println(lettercombinations(input));
        } catch (Exception e){
            System.out.println("Invalid input!");
        }
    }

    }

