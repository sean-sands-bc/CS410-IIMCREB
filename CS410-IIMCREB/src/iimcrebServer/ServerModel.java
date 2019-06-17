package iimcrebServer;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class ServerModel {
	ServerSQLLink s;
	
	public ServerModel(ServerSQLLink s)
	{
		this.s=s;
	}
	
	public boolean usernameExists(String username)
	{
		return s.getName(username);
	}
	
	public String getPassword(String username)
	{
		return s.getPassword(username);
	}
	
	public void setPassword(String username, String password, String email)
	{
		//s.newUser(username,password);
		s.newUser(username, password, email);
	}
	
	public String getStatus(String username)
	{
		return s.getStatus(username);
	}
	
	public void setStatus(String username, String status)
	{
		s.setStatus(username, status);
	}
	
	public HashMap<Timestamp,String> getMessages(String user1, String user2){
		return s.getAllMessages(user1, user2);
	}
	public void sendMessage(String user1, String user2, String message) {
		s.sendMessage(user1, user2, message);
	}
	public LinkedList<String> getUsers(){
		return s.userNames();
	}
	public LinkedList<String> getFriendList(String username){
		//System.out.println(s.getFriendsList(username).getFirst());
		return s.getFriendsList(username);
	}

	public void addNewFriend(String friender, String friendee){
		s.newFriend(friender,friendee);
	}

}
