package exceptions;

public class InvalidTradableException extends RuntimeException {
  public InvalidTradableException(String message) {
    super(message);
  }
}
