package scb.challenge.book.exception;

public class BookDuplicateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookDuplicateException(String msg) {
		super(msg);
	}
}