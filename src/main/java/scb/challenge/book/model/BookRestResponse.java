package scb.challenge.book.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRestResponse {
	private Integer id;
	private String book_name;
	private String author_name;
	private BigDecimal price;
}
