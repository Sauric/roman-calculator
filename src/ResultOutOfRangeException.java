public class ResultOutOfRangeException extends Exception{
    public ResultOutOfRangeException(Object number){
        super("Result number " + number.toString() + " is out of range");
    }
}