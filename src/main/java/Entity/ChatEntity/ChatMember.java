package Entity.ChatEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity(name="chatMember")
public class ChatMember {

	@Column(name="name_in_chat")
	private String nameInChat;
	@Column(name="administrator")
	private boolean isUserAdministrator;
	
    @JoinColumn(name = "chatId", nullable = false)
	private Chat chat;
	
	@EmbeddedId
	private CompositeKey id;
	
	
	@Embeddable
	public static class CompositeKey implements Serializable{
	
		@Column(name="chatuuid")
		private int chatId;
		@Column(name="useruid")
		private int userId;
		public int getChatId() {
			return chatId;
		}
		public void setChatId(int chatId) {
			this.chatId = chatId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		
	}


	public String getNameInChat() {
		return nameInChat;
	}


	public void setNameInChat(String nameInChat) {
		this.nameInChat = nameInChat;
	}


	public boolean isUserAdministrator() {
		return isUserAdministrator;
	}


	public void setUserAdministrator(boolean isUserAdministrator) {
		this.isUserAdministrator = isUserAdministrator;
	}


	public Chat getChat() {
		return chat;
	}


	public void setChat(Chat chat) {
		this.chat = chat;
	}


	public CompositeKey getId() {
		return id;
	}


	public void setId(CompositeKey id) {
		this.id = id;
	}
	
	
}
