package scb.challenge.book.exception;

public class UsernameDuplicateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameDuplicateException(String msg) {
		super(msg);
	}
}