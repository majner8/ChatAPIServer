package ChatManagement;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DTO.MessageDTO.MessageDTO;
import DTO.MessageDTO.MessageViewNotificationDTO;
import Main.BeanSetting.ActiveChatHashMap;
import Repository.ChatRepository.ChatRepository;
import Security.CustomUserDetails.AutorizationCustomUserDetails;

@Component

public class ChatService implements MessageManagement {

	
	@Value("${Chat.activeTimeout}")
	private static long activeTimeout;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private ActiveChatHashMap activeChat;
	@Autowired
	private ChatRepository chatRepo;

	@Async
	@Override
	public void SendMessage(MessageDTO message, int chatUUID) {
		// TODO Auto-generated method stub
		//send message to all user
		this.getChat(chatUUID).SendMessage(message);
	}
	@Async
	@Override
	public void SawMessage(int chatUUID, MessageViewNotificationDTO message) {
		// TODO Auto-generated method stub
		//Send notification to all user
		this.getChat(chatUUID).SendMessage(message);
	}


	public boolean doesUserHavePermision(String userId,int chaId) {
		Chat chat=this.getChat(chaId);
		if(chat==null) {
			//invalid chat, id was not recognize
			throw new ChatException("chatId was not recognize");
		}
		
		return chat.doesUserHavePermision(userId);
	}
	
	@Scheduled(fixedRate = 180000 )  
	public void TimeChech() {
    	long actualTime=System.currentTimeMillis();
    	synchronized(this.activeChat) {
    		this.activeChat.forEach((K,V)->{
    			this.TimeChech(actualTime, K, V);
    		});
    	}
	}
	
	@Async
	private  void TimeChech(long actualTime,String chatId,Chat chat) {
		chat.TimeChech(actualTime, chatId);
	}
	
	/**
	 * @return active Chat from connection or create new one 
	 * @return null if chat with appropriate Id does not exist*/
	private Chat getChat(int chatID) {
		Chat chat=this.activeChat.get(activeChat);
		if(chat!=null) {
			return chat;
		}
		Entity.ChatEntity.Chat chatEn=this.chatRepo.getById(chatID);
		if(chatEn==null) {
			return null;
		}
		return new Chat(chatEn);
	}
	public  class Chat{
		
		
		private Set<String> UserChatIDMember=Collections.synchronizedSet(new HashSet<String>());
		private volatile long lastTimeOfUsed=System.currentTimeMillis();
		
		public Chat(Entity.ChatEntity.Chat entityChat) {
			
		}
	

		private void SendMessage(Object MessageDTO) {
			this.lastTimeOfUsed=System.currentTimeMillis();
			this.UserChatIDMember.forEach((value)->{
				// each value mean user ID path, which sub each device
				//Id of chat is part of DTO object
				messagingTemplate.convertAndSend(value, MessageDTO);
			});
			
		}
	
		private void TimeChech(long actualTime,String chatId) {
			if(actualTime<=lastTimeOfUsed+activeTimeout) {
				return;
			}
			//chat is unactive have to be removed from collection
			activeChat.remove(chatId);
			//object would not be trigger again
			return;
		}

		

		private boolean doesUserHavePermision(String userId) {
			return this.UserChatIDMember.contains(userId);
		}
		
		
	}
	
	
	public static class ChatException extends RuntimeException{
		public ChatException(String message) {
			super(message);
		}
	}

}
