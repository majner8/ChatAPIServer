package Autorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Autorization.Entity.DeviceUserEntity;
import Autorization.Entity.DeviceUserEntity.CompositePrimaryKey;
import User.Entity.UserPasswordEntity;

public interface DeviceUserEntityRepository extends JpaRepository<DeviceUserEntity,CompositePrimaryKey> {
	
	
	
}
