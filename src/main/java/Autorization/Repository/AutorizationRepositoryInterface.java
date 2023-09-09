package Autorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import User.Entity.UserEntity;
import User.Entity.UserPasswordEntity;


public interface AutorizationRepositoryInterface extends JpaRepository<UserPasswordEntity,Integer> {
	
	
	
}

