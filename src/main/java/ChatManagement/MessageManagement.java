package ChatManagement;

import DTO.MessageDTO.MessageDTO;
import DTO.MessageDTO.MessageViewNotificationDTO;

public interface MessageManagement {

	/**Metod Resend Message to appropriate chat
	*/
	public void SendMessage(MessageDTO message,int chatUUID);
	
	/**Metod Resend notifiction to all user in chat*/
	public void SawMessage(int chatUUID,MessageViewNotificationDTO message);


}
