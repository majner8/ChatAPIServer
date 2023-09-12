package Entity.ChatEntity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="chat")
public class Chat {

	@Id
	@Column(name="chatuuid")
	private int chatId;
	@Column(name="chat_name")
	private String nameOfChat;
	
	@OneToMany(mappedBy="userId")
	private Set<ChatMember> memberOfChat;

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public String getNameOfChat() {
		return nameOfChat;
	}

	public void setNameOfChat(String nameOfChat) {
		this.nameOfChat = nameOfChat;
	}

	public Set<ChatMember> getMemberOfChat() {
		return memberOfChat;
	}

	public void setMemberOfChat(Set<ChatMember> memberOfChat) {
		this.memberOfChat = memberOfChat;
	}
	
	
}
