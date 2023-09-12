package Message;

import org.springframework.data.jpa.repository.JpaRepository;

import User.Entity.UserEntity;

public interface MessageRepository extends  JpaRepository<UserEntity,Integer> {

}
