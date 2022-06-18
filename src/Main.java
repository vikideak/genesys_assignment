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

    /** Insert character to a string **/
    public static String insertChar(String str, char what, int indexWhere) {
        String strBegin = str.substring(0,indexWhere);
        String strEnd = str.substring(indexWhere);
        return strBegin + what + strEnd;
    }

    /** Checking whether input digit is an integer */
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

    public static ArrayList<String> lettercombinations(String inputLine) {
        //Extracting the digits between "" from the input string
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(inputLine);
        String digits = "";

        while (m.find()) {
            digits += m.group(1);
        }

        //The letter combinations for the output
        ArrayList<String> combinations = new ArrayList<>();
        if (isNumeric(digits)) {

            int digitLength = digits.length();

            //If there are no digits, the output is an empty Arraylist
            if (digitLength > 0) {
                if(digitLength <= 4) {
                    combinations.add('"' + "" + '"');

                    //Loop for all extracted digits
                    for (int i = 0; i < digitLength; i++) {
                        int digit = Character.getNumericValue(digits.charAt(i));

                        //1 does not map to any letters
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
                    }
                }
                else {
                    throw new InvalidParameterException();
                }
            }

        }
        else {
            System.out.println("Digit is not an integer!");
        }
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

