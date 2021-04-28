package scb.challenge.book.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import scb.challenge.book.model.BookGetDataResponse;
import scb.challenge.book.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/book")
	public ResponseEntity<List<BookGetDataResponse>> getBook() {
		List<BookGetDataResponse> res = new ArrayList<>();
		res = bookService.getBook();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/book/recommendation")
	public ResponseEntity<List<BookGetDataResponse>> getBookRecommend() {
		List<BookGetDataResponse> res = new ArrayList<>();
		res = bookService.getBookRecommend();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/syncBook")
	public ResponseEntity<?> syncBook() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		bookService.syncBook();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
