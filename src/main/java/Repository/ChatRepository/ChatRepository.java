package Repository.ChatRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.ChatEntity.Chat;


public interface ChatRepository extends JpaRepository<Chat,Integer> {

}
