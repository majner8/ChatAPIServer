package Autorization.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Autorization.Entity.DeviceIpAdressEntity;
import Autorization.Entity.DeviceUserEntity.CompositePrimaryKey;

public interface DeviceUserIpAdressEntityRepository extends JpaRepository<DeviceIpAdressEntity,CompositePrimaryKey> {
	
	
	
}
