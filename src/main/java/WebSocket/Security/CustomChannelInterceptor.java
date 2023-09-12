package WebSocket.Security;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import ChatManagement.ChatService;
import Config.PathConfig;
import Security.CustomUserDetails;

public class CustomChannelInterceptor implements ChannelInterceptor {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private ChatService Chatservice;
	
	/**
	 *Metod send response chat/AccesDenied/chatID if user do not have enought permision(is not part of chat)   */
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        CustomUserDetails user=(CustomUserDetails) accessor.getUser();
       
        if(SimpMessageType.SUBSCRIBE==accessor.getMessageType()) {
        	//message is subs attemp, verify if user have enought permision
        	//client can start sub only itself
        	if(!accessor.getDestination().equals(user.getStringUserId())) {
        		//user do not have permision to sub path
                throw new SecurityException("Access Denied, user do not have permision to sub path"+accessor.getDestination());
        	}
        	return message;
        }
        if(accessor.getMessageType()==SimpMessageType.MESSAGE) {
        	//all Message have to have first part variable
            String[] destinationID=accessor.getDestination().split("/");
            //chat request, first variable ID have to be chatID	
            if(destinationID[1].equals(PathConfig.ChatPreflix)) {
            	if(this.Chatservice.doesUserHavePermision(user.getUserId(), null))
            		//user cannot send message to chat where he is not member
            	this.messagingTemplate.convertAndSendToUser(user.getUsername(), "chat/AccesDenied/"+destinationID[0], null);
            	return null;
            }    
        }
        
        
		return message;
	}
}
