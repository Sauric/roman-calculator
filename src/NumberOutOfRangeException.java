public class NumberOutOfRangeException extends Exception{
    public NumberOutOfRangeException(Object number){
        super("Number " + number.toString() + " is out of range");
    }
}

