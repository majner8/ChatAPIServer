package Repository.AutorizationRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.UserEntity.UserEntity;
import Entity.UserEntity.UserPasswordEntity;


public interface AutorizationRepositoryInterface extends JpaRepository<UserPasswordEntity,Integer> {
	
	
	
}

