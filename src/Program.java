public class Program {

    public static void main(String[] args) throws Exception {
        while (true){
            System.out.println("Введите ваши данные");
            int num1 = Calculator.getInt(true);
            char operation = Calculator.getOperation();
            int num2 = Calculator.getInt(false);
            int result = Calculator.calc( num1, num2, operation);
            Calculator.printResult(result);
        }
    }
}
