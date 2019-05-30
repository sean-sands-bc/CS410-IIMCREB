package iimcrebServer;

import java.util.Hashtable;

public class ServerModel {
	
	Hashtable<String,String> passwords;
	Hashtable<String,String> statuses;
	Hashtable<String,String> chatlogs;
	
	public ServerModel()
	{
		passwords = new Hashtable<String,String>();
		statuses = new Hashtable<String,String>();
		chatlogs = new Hashtable<String,String>();
	}
	
	public String getPassword(String username)
	{
		return passwords.get(username);
	}
	
	public String getStatus(String username)
	{
		return statuses.get(username);
	}
	
	public void setStatus(String username, String status)
	{
		statuses.put(username, status);
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
