package Entity.MessageEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import DTO.MessageDTO.MessageDTO;

@Entity(name="Message")
public class MessageEntity {
	@Column(name="message_text")
	private String textMessage;
	
	@Column(name="sender_uuid")
	private int sender_id;
	
	@Column(name="delivery_time")
	private Date deliveryTime;
	
	@Column(name="does_message_text")
	private boolean doesMessageText;
	@Column(name="answer_other_message_uuid")
	private String answer_other_message_uuid;
	
	@Column(name="chat_order")
	private int messageOrderInChat;
	
	@EmbeddedId
	private CompositeKey primaryKey;
	
	
	public MessageEntity() {
		
	}
	
	public MessageEntity(MessageDTO message) {
		
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public int getSender_id() {
		return sender_id;
	}

	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public boolean isDoesMessageText() {
		return doesMessageText;
	}

	public void setDoesMessageText(boolean doesMessageText) {
		this.doesMessageText = doesMessageText;
	}

	public String getAnswer_other_message_uuid() {
		return answer_other_message_uuid;
	}

	public void setAnswer_other_message_uuid(String answer_other_message_uuid) {
		this.answer_other_message_uuid = answer_other_message_uuid;
	}

	public int getMessageOrderInChat() {
		return messageOrderInChat;
	}

	public void setMessageOrderInChat(int messageOrderInChat) {
		this.messageOrderInChat = messageOrderInChat;
	}

	public CompositeKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(CompositeKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	
	@Embeddable
	public static class CompositeKey implements Serializable{
		@Column(name="chat_uuid")
		private int chatId;

		//message id is generated on client Side
		@Column(name="message_uuid")	
		private String messageID;

		public int getChatId() {
			return chatId;
		}

		public void setChatId(int chatId) {
			this.chatId = chatId;
		}

		public String getMessageID() {
			return messageID;
		}

		public void setMessageID(String messageID) {
			this.messageID = messageID;
		}
		

	}
}

