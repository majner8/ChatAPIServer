package ChatManagement;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;

import Config.PathConfig;
import DTO.MessageDTO.MessageDTO;
import Entity.MessageEntity.MessageEntity;
import Repository.MessageRepository.MessageRepository;

public class ChatControler {

	@Autowired
	private ChatService chatService;	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private MessageRepository messageRepo;
	/**Metod process sending message to appropriate chat,
	 * after that send back Message with time of process and order in chat */
	@MessageMapping("/{chatUUID}"+PathConfig.SendMessageWSPathSuflix)
	public void SendMessage(@DestinationVariable("chatUUID") String chatUUID,
			MessageDTO receivedMessage) {
		MessageEntity mesEnt=new MessageEntity(receivedMessage);
		//save message to database
		try {
		this.messageRepo.save(mesEnt);
		}
		catch(DataIntegrityViolationException e) {
			if(e.getCause() instanceof SQLException) {
				if(((SQLException)e.getCause()).getErrorCode()==1062){
					//duplicate primary key
					//send messageBack to user
					return;
				}
			}
		}
		
		
		MessageDTO message=new MessageDTO(mesEnt);
		//send message to all user,
		//send notification back is not necesary, because chat resend message to sender as well
		//
		this.chatService.SendMessage(message, Integer.parseInt(chatUUID));
		
		return;
	}
}
