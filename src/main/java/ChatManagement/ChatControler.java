package ChatManagement;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;

import Config.PathConfig;
import Message.MessageDTO;

public class ChatControler {

	
	
	/**Metod process sending message to appropriate chat,
	 * after that send back Message with time of process and order in chat */
	@SendToUser
	@MessageMapping("/{chatUUID}"+PathConfig.SendMessageWSPathSuflix)
	public MessageDTO SendMessage(@DestinationVariable("chatUUID") String chatUUID,
			MessageDTO message) {
		
		
		return null;
	}
}
