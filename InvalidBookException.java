/**
 * InvalidBookException.java
 * Custom checked exception thrown when book data provided by the user
 * is invalid (empty fields, bad year, duplicate ID, etc.).
 */
public class InvalidBookException extends Exception {

    public InvalidBookException(String message) {
        super(message);
    }
}
