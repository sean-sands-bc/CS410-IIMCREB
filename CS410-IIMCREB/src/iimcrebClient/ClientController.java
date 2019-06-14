package iimcrebClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

public class ClientController {
	int port = 8675;
	String host = "localhost";
	Socket socket;
	String username;
	String status;
	
	ObjectInputStream stringIn;
	ObjectOutputStream stringOut;
	
	public ClientController()
	{
		try {
			socket = new Socket(host,port);
			stringIn = new ObjectInputStream(socket.getInputStream());
			stringOut = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int register(String username, String password, String email)
	{
		try {
			stringOut.writeObject("register");
			System.out.println("cliregreg");
			stringOut.writeObject(username);
			System.out.println("clireguser");
			stringOut.writeObject(password);
			System.out.println("cliregpass");
			stringOut.writeObject(email);
			System.out.println("cliregpass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String response = "";
		try {
			response = (String) stringIn.readObject();
			System.out.print("cliregresp ");
			System.out.println(response);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response.equals("register")) { return 0; }
		if(response.equals("taken")) { return 1; }
		return 3;
	}
	
	public int login(String username, String password)
	{
		try {
			stringOut.writeObject("login");
			stringOut.writeObject(username);
			stringOut.writeObject(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String response = "";
		try {
			response = (String) stringIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response.equals("login")) { return 0; }
		if(response.equals("missing")) { return 1; }
		if(response.equals("incorrect")) { return 2; }
		return 3;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String un)
	{
		username = un;
	}
	
	public String getStatus()
	{
		try {
			stringOut.writeObject("getStatus");
			stringOut.writeObject(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			status = (String)stringIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public void setStatus(String st)
	{
		try {
			stringOut.writeObject("setStatus");
			stringOut.writeObject(username);
			stringOut.writeObject(st);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//status = st;
		try {
			status = (String)stringIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getFriendStatus(String friendname)
	{
		try {
			stringOut.writeObject("getStatus");
			stringOut.writeObject(friendname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String frst = "";
		try {
			frst = (String)stringIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return frst;
	}
	
	public void sendMsg(String friendname, String msg)
	{
		try {
			stringOut.writeObject("sendMsg");
			stringOut.writeObject(username);
			stringOut.writeObject(friendname);
			stringOut.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String updateLog(String friendname)
	{
		try {
			stringOut.writeObject("updateLog");
			stringOut.writeObject(username);
			stringOut.writeObject(friendname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String log="";
		try {
			Object o=stringIn.readObject();
			
			System.out.println(o);
			if(stringIn.available()>0)
			stringIn.readObject();
			
			
			log = (String)o;
			System.out.println(log);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return log;
	}

	public void addFriend(String friendname)
	{
		try {
			stringOut.writeObject("addFriend");
			stringOut.writeObject(username);
			stringOut.writeObject(friendname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delFriend(String friendname)
	{
		try {
			stringOut.writeObject("delFriend");
			stringOut.writeObject(username);
			stringOut.writeObject(friendname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getFriends()
	{
		try {
			stringOut.writeObject("getFriends");
			stringOut.writeObject(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	public LinkedList<String> getUsers(){
		try {
			stringOut.writeObject("getUsers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedList<String> users=new LinkedList<String>();
		try {
			users = (LinkedList<String>)stringIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}
	

}