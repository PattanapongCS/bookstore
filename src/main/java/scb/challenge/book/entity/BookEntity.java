package scb.challenge.book.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "book", schema = "migrations")
@Data
public class BookEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="book_name")
    private String bookName;
	
	@Column(name="author_name")
    private String authorName;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="is_recommended")
	private boolean isRecommended;

}
