import java.net.SocketTimeoutException;
import java.util.Scanner;
public class calculator {
    static int leftBound = 1;

    static int rightBound = 10;

    static boolean firstNumberIsArabian;

    static  boolean secondNumberIsArabian;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true){
            System.out.println("Введите выши данные");
            int num1 = getInt(true);
            validateNumber(num1);
            char operation = getOperation();
            int num2 = getInt(false);
            validateNumber(num2);
            int result = calc( num1, num2, operation);
            printResult(result);
        }
    }
    private static void printResult(int result) throws Exception{
        if(firstNumberIsArabian && secondNumberIsArabian){
            System.out.println(result);
        } else if (!firstNumberIsArabian && !secondNumberIsArabian){
            System.out.println(integerToRoman(result));
        } else {
            throw new Exception("Числа разного формата!");
        }
    }

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
            System.out.println(" вы педик затупили");
            scanner.next();
            num = getInt(firstNumber);
        }
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

    public static int calc(int num1, int num2, char operation) {
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
        return result;
    }

    public static int convertRomanToArabic(String letter) throws Exception {
        switch (letter){
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new ArrayIndexOutOfBoundsException(letter);
        }
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

    private static void validateNumber(int number){
        if(number < leftBound || number > rightBound)
            throw new ArrayIndexOutOfBoundsException(number);
    }

}
