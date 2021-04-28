package scb.challenge.book.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import scb.challenge.book.model.BookGetDataResponse;

public interface BookService {

	List<BookGetDataResponse> getBookRecommend();

	List<BookGetDataResponse> getBook();

	void syncBook() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException;
	
}
