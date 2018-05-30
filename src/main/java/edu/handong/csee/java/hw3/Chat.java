package edu.handong.csee.java.hw3;

/**
 * This is the class that store the chat data
 * 
 * @author seonamjin
 *
 */
public class Chat {
	private String name;
	private String time;
	private String message;
	
	/**
	 * This is the constructor
	 * @param name is kakao talk id
	 * @param time is the chatting time
	 * @param message is the chatting message
	 */
	public Chat(String name, String time, String message) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setTime(time);
		this.setMessage(message); 
	}
	/**
	 * This is the getter of name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is the setter of name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This is the getter of time
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * This is the setter of time
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * This is the getter of message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * This is the setter of message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
