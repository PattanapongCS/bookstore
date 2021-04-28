package scb.challenge.book.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostRequest {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date date_of_birth;
}