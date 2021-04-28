package scb.challenge.book.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scb.challenge.book.exception.ResourceNotFoundException;
import scb.challenge.book.model.UserGetDataResponse;
import scb.challenge.book.model.UserOrderPostDataResponse;
import scb.challenge.book.model.UserOrderPostRequest;
import scb.challenge.book.model.UserPostRequest;
import scb.challenge.book.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<UserGetDataResponse> getUser(@RequestParam(name = "id") Integer userId) {
		UserGetDataResponse res = new UserGetDataResponse();
		res = userService.getUser(userId);
		if (res == null) {
			throw new ResourceNotFoundException("Not found userId =" + userId);
		}
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestParam(name = "id") Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?>  createUser(@RequestBody UserPostRequest req) {
		// TODO Auto-generated method stub
		userService.createUser(req);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/user/order")
	public ResponseEntity<UserOrderPostDataResponse> userOrder(@RequestBody UserOrderPostRequest req) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		// TODO Auto-generated method stub
		UserOrderPostDataResponse res = new UserOrderPostDataResponse();
		res = userService.userOrder(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

}
