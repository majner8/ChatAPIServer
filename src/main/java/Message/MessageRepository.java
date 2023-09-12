package Message;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.UserEntity.UserEntity;

public interface MessageRepository extends  JpaRepository<UserEntity,Integer> {

}
