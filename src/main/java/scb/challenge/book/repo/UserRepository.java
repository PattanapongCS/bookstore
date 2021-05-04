package scb.challenge.book.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scb.challenge.book.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  
	List<UserEntity> findByUsername(String username);
	
	void deleteByUsername(String username);
}
