package iimcrebClient;

import java.io.IOException;
import java.net.*;

public class ClientController {
	int port = 8675;
	String host = "localhost";
	Socket socket;
	String username;
	
	public ClientController()
	{
		try {
			socket = new Socket(host,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int register(String username, String password)
	{
		
		return 0;		
	}
	
	public int login(String username, String password)
	{
		
		return 0;
	}
	
	public String getStatus()
	{
		return new String();
	}
	
	public int setStatus()
	{
		return 0;
	}
	
	public String getFriendStatus(String username)
	{
		return new String();
	}
	

}
