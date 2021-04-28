package scb.challenge.book.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderPostRequest {
	private Integer userId;
	private List<Integer> orders;
}