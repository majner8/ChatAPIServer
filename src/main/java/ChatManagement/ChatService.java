package ChatManagement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Main.BeanSetting.ActiveChatHashMap;
import Message.MessageDTO;
import Message.MessageViewNotificationDTO;
import Security.CustomUserDetails.AutorizationCustomUserDetails;

@Component

public class ChatService implements MessageManagement {

	@Value("${Chat.activeTimeout}")
	private static long activeTimeout;
	
	@Autowired
	private ActiveChatHashMap activeChat;


	@Async
	@Override
	public void SendMessage(MessageDTO message, String chatUUID) {
		// TODO Auto-generated method stub
		//send message to all user
		this.activeChat.get(chatUUID).SendMessage(message);
	}
	@Async
	@Override
	public void SawMessage(String chatUUID, MessageViewNotificationDTO message) {
		// TODO Auto-generated method stub
		//Send notification to all user
		this.activeChat.get(chatUUID).SendMessage(message);
	}


	public boolean doesUserHavePermision(String userId,String chaId) {
		
	}

	@Component(value="prototype")
	public  class Chat implements TimeValidationChech.IsStillActive{
	
		
		@Autowired
		private SimpMessagingTemplate messagingTemplate;
		@Autowired
		private TimeValidationChech timeValidation;
		
		
		private String chatId;
		private Set<String> UserChatIDMember=Collections.synchronizedSet(new HashSet<String>());
		private volatile long lastTimeOfUsed=System.currentTimeMillis();
		
		public Chat() {
			
		}
		private void SendMessage(Object MessageDTO) {
			if(this.chatId==null) {
				throw new RuntimeException("chatId cannot be null");
			}
			this.lastTimeOfUsed=System.currentTimeMillis();
			this.UserChatIDMember.forEach((value)->{
				// each value mean user ID path, which sub each device
				//Id of chat is part of DTO object
				messagingTemplate.convertAndSend(value, MessageDTO);
			});
			
		}

		
		
		@Async
		@Override
		public void TimeChech(long actualTime) {
			if(this.chatId==null) {
				throw new RuntimeException("chatId cannot be null");
			}
			if(actualTime<=lastTimeOfUsed+activeTimeout) {
				return;
			}
			//chat is unactive have to be removed from collection
			activeChat.remove(this.chatId);
			//object would not be trigger again
			this.timeValidation.removeObjectFromVerification(this);
			return;
		}

		public void setChatId(String chatId) {
			this.chatId = chatId;
		}


		
		
	}
	
	

}
