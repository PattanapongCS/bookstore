package scb.challenge.book.service;

import scb.challenge.book.model.UserGetDataResponse;
import scb.challenge.book.model.UserOrderPostDataResponse;
import scb.challenge.book.model.UserOrderPostRequest;
import scb.challenge.book.model.UserPostRequest;

public interface UserService {

	UserGetDataResponse getUser(Integer userId);

	void createUser(UserPostRequest req);

	UserOrderPostDataResponse userOrder(UserOrderPostRequest req);

}
