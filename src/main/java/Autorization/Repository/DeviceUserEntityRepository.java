package Autorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.AutorizationEntity.DeviceUserEntity;
import Entity.AutorizationEntity.DeviceUserEntity.CompositePrimaryKey;
import Entity.UserEntity.UserPasswordEntity;

public interface DeviceUserEntityRepository extends JpaRepository<DeviceUserEntity,CompositePrimaryKey> {
	
	
	
}
