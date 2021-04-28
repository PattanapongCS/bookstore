package scb.challenge.book.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetDataResponse {

	private Integer id;
	private String name;
	private String surname;
	private Date date_of_birth;
	private List<Integer> books;
}