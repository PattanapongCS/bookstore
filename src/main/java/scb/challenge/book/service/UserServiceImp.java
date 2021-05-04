package scb.challenge.book.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scb.challenge.book.entity.BookEntity;
import scb.challenge.book.entity.OrderEntity;
import scb.challenge.book.entity.UserEntity;
import scb.challenge.book.exception.BookDuplicateException;
import scb.challenge.book.exception.ResourceNotFoundException;
import scb.challenge.book.exception.UsernameDuplicateException;
import scb.challenge.book.model.UserGetDataResponse;
import scb.challenge.book.model.UserOrderPostDataResponse;
import scb.challenge.book.model.UserOrderPostRequest;
import scb.challenge.book.model.UserPostRequest;
import scb.challenge.book.repo.BookRepository;
import scb.challenge.book.repo.OrderRepository;
import scb.challenge.book.repo.UserRepository;

@Service("UserService")
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public UserGetDataResponse getUser(Integer userId) {
		Optional<UserEntity> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<OrderEntity> orderEntities = orderRepository.findByUserId(user.get().getId());
			List<Integer> order = orderEntities.stream().map(OrderEntity::getBookId).collect(Collectors.toList());
			return UserGetDataResponse.builder().id(user.get().getId()).name(user.get().getName()).surname(user.get().getSurname())
					.date_of_birth(user.get().getDateOfBirth()).books(order).build();
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void createUser(UserPostRequest req) {
		List<UserEntity> ckeckUsernameDup = userRepository.findByUsername(req.getUsername());
		if (ObjectUtils.isEmpty(ckeckUsernameDup)) {
			UserEntity user = new UserEntity();
			user.setUsername(req.getUsername());
			user.setPassword(req.getPassword());
			user.setName(req.getName());
			user.setSurname(req.getSurname());
			user.setDateOfBirth(req.getDate_of_birth());
			userRepository.save(user);
		} else {
			throw new UsernameDuplicateException("Username is duplicate name = " + req.getUsername());
		}
	}

	@Override
	@Transactional
	public UserOrderPostDataResponse userOrder(UserOrderPostRequest req){
		List<BookEntity> bookList = bookRepository.findAll();
		List<BookEntity> bookResult = bookList.stream().filter(b -> req.getOrders().contains(b.getId())).collect(Collectors.toList());
		BigDecimal rsPrice = bookResult.stream().map(b -> b.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
		Optional<UserEntity> user = userRepository.findById(req.getUserId());
		if (user.isPresent()) {
			for (BookEntity book : bookResult) {
				List<OrderEntity> checkBook = orderRepository.findByUserIdAndBookId(user.get().getId(), book.getId());
				if (ObjectUtils.isEmpty(checkBook)) {
					OrderEntity order = new OrderEntity();
					order.setUserId(user.get().getId());
					order.setBookId(book.getId());
					order.setCreatedDate(new Date());
					orderRepository.save(order);
				} else {
					throw new BookDuplicateException("Book is duplicate name = " + book.getBookName());
				}
			}
		}
		return UserOrderPostDataResponse.builder().price(rsPrice).build();
	}

	@Override
	@Transactional
	public void deleteUser(Integer userId) {
		Optional<UserEntity> rs = userRepository.findById(userId);
		if (rs.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new ResourceNotFoundException("Not found userId =" + userId);
		}
	}

}
