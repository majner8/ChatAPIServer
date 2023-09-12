package Repository.AutorizationRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.AutorizationEntity.DeviceIpAdressEntity;
import Entity.AutorizationEntity.DeviceUserEntity.CompositePrimaryKey;

public interface DeviceUserIpAdressEntityRepository extends JpaRepository<DeviceIpAdressEntity,CompositePrimaryKey> {
	
	
	
}
