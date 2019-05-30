package iimcrebServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class ServerController {
	int port = 8675;
	
	ServerSocket serverSocket;
	
	ServerModel serverModel;
	
	public ServerController(ServerModel sm)
	{
		serverModel = sm;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		while(true)
		{
			try {
				Socket s = serverSocket.accept();
				ServerClientProtocol scp = new ServerClientProtocol(s,serverModel);
				Thread t = new Thread(scp);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public class ServerClientProtocol implements Runnable
	{
		ServerModel serverModel;
		Socket socket;
		String username;
		ObjectInputStream stringIn;
		ObjectOutputStream stringOut;
		
		public ServerClientProtocol(Socket s, ServerModel sm)
		{
			serverModel = sm;
			socket = s;
			username = "";
			System.out.println("client connection");
			
			try {
				stringOut = new ObjectOutputStream(socket.getOutputStream());
				stringIn  = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("socket connection");
		}

		public void scpRegister()
		{
			String un = "";
			String pw = "";
			try {
				un = (String)stringIn.readObject();
				pw = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(serverModel.usernameExists(un))
			{
				try {
					stringOut.writeObject("taken");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				serverModel.setPassword(un, pw);
				serverModel.setStatus(un, "Offline");
				try {
					stringOut.writeObject("register");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		public void scpLogin()
		{
			String un = "";
			String pw = "";
			try {
				un = (String)stringIn.readObject();
				pw = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!serverModel.usernameExists(un))
			{
				try {
					stringOut.writeObject("missing");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(pw.equals(serverModel.getPassword(un)))
			{
				try {
					stringOut.writeObject("login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try {
					stringOut.writeObject("incorrect");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void scpGetStatus()
		{
			String un = "";
			try {
				un = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String status = serverModel.getStatus(un);
			try {
				stringOut.writeObject(status);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void scpSetStatus()
		{
			String un = "";
			String st = "";
			try {
				un = (String)stringIn.readObject();
				st = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			serverModel.setStatus(un, st);
			try {
				stringOut.writeObject(st);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void run() {
			//	only accept local connections
			if (!socket.getInetAddress().isLoopbackAddress())
			{
				System.out.println("nonlocal address");
			    return;
			}
			
			while(true)
			{
				String command="";
				try {
					System.out.print("serscpcomm ");
					command = (String)stringIn.readObject();
					System.out.println(command);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch(command)
				{
				case "":
					return;
				case "register":
					scpRegister();
					break;
				case "login":
					scpLogin();
					break;
				case "getStatus":
					scpGetStatus();
					break;
				case "setStatus":
					scpSetStatus();
					break;
				
				}
			}
			
		}
		
	}




}
