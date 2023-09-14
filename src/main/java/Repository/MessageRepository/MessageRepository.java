package Repository.MessageRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.MessageEntity.MessageEntity;


public interface MessageRepository extends  JpaRepository<MessageEntity,MessageEntity.CompositeKey> {

}
