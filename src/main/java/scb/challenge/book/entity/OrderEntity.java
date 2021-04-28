package scb.challenge.book.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "order", schema = "migrations")
@Data
public class OrderEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="user_id")
    private Integer userId;
	
	@Column(name="book_id")
    private Integer bookId;

	@Column(name="created_date")
    private Date createdDate;

}
