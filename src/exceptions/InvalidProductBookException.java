package exceptions;

public class InvalidProductBookException extends RuntimeException {
  public InvalidProductBookException(String message) {
    super(message);
  }
}
