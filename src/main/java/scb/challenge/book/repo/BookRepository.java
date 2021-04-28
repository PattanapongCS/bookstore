package scb.challenge.book.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scb.challenge.book.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	
	List<BookEntity> findAllByOrderByIdAsc();
	
	List<BookEntity> findByIsRecommended(boolean rec);
	
}
