package scb.challenge.book.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import scb.challenge.book.entity.BookEntity;
import scb.challenge.book.model.BookGetDataResponse;
import scb.challenge.book.repo.BookRepository;
import scb.challenge.book.utils.Constant;
import scb.challenge.book.utils.RestTemplateUtil;

@Service("BookService")
public class BookServiceImp implements BookService{
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<BookGetDataResponse> getBookRecommend() {
		List<BookGetDataResponse> res = new ArrayList<>();
		List<BookEntity> books = bookRepository.findByIsRecommended(true);
		for (BookEntity book : books) {
			res.add(BookGetDataResponse.builder()
					.id(book.getId())
					.book_name(book.getBookName())
					.author_name(book.getAuthorName())
					.price(book.getPrice())
					.is_recommended(book.isRecommended())
					.build());
		}
		return res;
	}

	@Override
	public List<BookGetDataResponse> getBook() {
		List<BookGetDataResponse> res = new ArrayList<>();
		List<BookEntity> books = bookRepository.findAllByOrderByIdAsc();
		for (BookEntity book : books) {
			res.add(BookGetDataResponse.builder()
					.id(book.getId())
					.book_name(book.getBookName())
					.author_name(book.getAuthorName())
					.price(book.getPrice())
					.is_recommended(book.isRecommended())
					.build());
		}
		return res;
	}

	@Override
	public void syncBook() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		RestTemplate restTemplate = RestTemplateUtil.disbleSSL();
		ResponseEntity<String> response = restTemplate.getForEntity(Constant.BOOK_URL, String.class);
		List<BookGetDataResponse> books = Arrays.asList(new Gson().fromJson(response.getBody(), BookGetDataResponse[].class));
		List<BookEntity> bookList = new ArrayList<>();
		for (BookGetDataResponse book : books) {
			Random randomRec = new Random();
			BookEntity entity = new BookEntity();
			entity.setId(book.getId());
			entity.setBookName(book.getBook_name());
			entity.setAuthorName(book.getAuthor_name());
			entity.setPrice(book.getPrice());
			entity.setRecommended(randomRec.nextBoolean());
			bookList.add(entity);
		}
		bookRepository.saveAll(bookList);
	}
	
	
}
