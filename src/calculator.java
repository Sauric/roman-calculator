import java.util.Scanner;

public class Calculator {
    static int leftBound = 1;

    static int rightBound = 10;

    static boolean firstNumberIsArabian;

    static  boolean secondNumberIsArabian;

    static Scanner scanner = new Scanner(System.in);

    public static int getInt(boolean firstNumber) throws Exception {
        int num;
        if(scanner.hasNextInt()) {
            num = scanner.nextInt();
            if(firstNumber)
                firstNumberIsArabian = true;
            else
                secondNumberIsArabian = true;
        }
        else if(scanner.hasNext()){
            var number = scanner.next();
            num = convertRomanToArabic(number);
            if(firstNumber)
                firstNumberIsArabian = false;
            else
                secondNumberIsArabian = false;
        }
        else {
            System.out.println("Введите числа от 1 до 10");
            scanner.next();
            num = getInt(firstNumber);
        }
        validateNumber(num);
        return num;
    }
    public static char getOperation() {
        char operation;
        if(scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            scanner.next();
            operation = getOperation();
        }
        return operation;
    }

    public static void printResult(int result) throws Exception{
        if(firstNumberIsArabian && secondNumberIsArabian){
            System.out.println(result);
        } else if (!firstNumberIsArabian && !secondNumberIsArabian){
            System.out.println(integerToRoman(result));
        } else {
            throw new DifferentNumbersFormatException();
        }
    }

    public static int calc(int num1, int num2, char operation) throws ResultOutOfRangeException {
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                result = calc(num1, num2, getOperation());
        }
        validateResult(result);
        return result;
    }

    public static int convertRomanToArabic(String letter) throws Exception {
        String[] romanLiterals = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for(int i = 1; i < romanLiterals.length; ++i){
            if(letter.equals(romanLiterals[i]))
                return i;
        }
        throw new NumberOutOfRangeException(letter);
    }

    public static String integerToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    private static void validateNumber(int number) throws NumberOutOfRangeException {
        if(number < leftBound || number > rightBound)
            throw new NumberOutOfRangeException(number);
    }

    private static void validateResult(int result) throws ResultOutOfRangeException {
        if(!firstNumberIsArabian && !secondNumberIsArabian && result < 1)
            throw new ResultOutOfRangeException(result);
    }

}
