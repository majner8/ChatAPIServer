package ChatManagement;

import Message.MessageDTO;
import Message.MessageViewNotificationDTO;

public interface MessageManagement {

	/**Metod Resend Message to appropriate chat
	*/
	public void SendMessage(MessageDTO message,String chatUUID);
	
	/**Metod Resend notifiction to all user in chat*/
	public void SawMessage(String chatUUID,MessageViewNotificationDTO message);


}
