package iimcrebClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

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
	
	public int register(String username, String password)
	{
		try {
			stringOut.writeObject("register");
			System.out.println("cliregreg");
			stringOut.writeObject(username);
			System.out.println("clireguser");
			stringOut.writeObject(password);
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
		status = st;
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
	

}
