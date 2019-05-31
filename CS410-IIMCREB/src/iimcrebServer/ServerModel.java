package iimcrebServer;

import java.util.Hashtable;

public class ServerModel {
	ServerSQLLink s;
	
	Hashtable<String,String> passwords;
	Hashtable<String,String> statuses;
	Hashtable<String,String> chatlogs;
	
	public ServerModel(ServerSQLLink s)
	{
		this.s=s;
		passwords = new Hashtable<String,String>();
		statuses = new Hashtable<String,String>();
		chatlogs = new Hashtable<String,String>();
	}
	
	public boolean usernameExists(String username)
	{
		//return passwords.containsKey(username);
		return s.getName(username);
	}
	
	public String getPassword(String username)
	{
		//return passwords.get(username);
		return s.getPassword(username);
	}
	
	public void setPassword(String username, String password)
	{
		//passwords.put(username, password);
		s.setPassword(username, password);
	}
	
	public String getStatus(String username)
	{
		//return statuses.get(username);
		return s.getStatus(username);
	}
	
	public void setStatus(String username, String status)
	{
		//statuses.put(username, status);
		s.setStatus(username, status);
	}
	
	public String getChatlog(String un1, String un2)
	{
		if(un1.compareTo(un2)<0)
		{
			return chatlogs.get(un1+","+un2);
		}
		else
		{
			return chatlogs.get(un2+","+un1);
		}
	}

}
