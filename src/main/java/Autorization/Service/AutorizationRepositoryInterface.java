package Autorization.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import Autorization.Entity.AutorizationUserEntity;
import User.Entity.UserEntity;


public interface AutorizationRepositoryInterface extends JpaRepository<AutorizationUserEntity,Integer> {
	
	
	
}

