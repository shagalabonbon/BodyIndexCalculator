package com.example.calculate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.calculate.model.entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value="Select * From user Where email = :email", nativeQuery = true)
	Optional<User> findUserByEmail(String email);
	
	@Query(value="Select * From user Where username = :username", nativeQuery = true)
	Optional<User> findUserByUsername(String username);
}
