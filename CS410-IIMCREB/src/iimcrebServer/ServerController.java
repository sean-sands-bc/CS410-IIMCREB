package iimcrebServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

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
			String em="";
			try {
				un = (String)stringIn.readObject();
				pw = (String)stringIn.readObject();
				em=(String)stringIn.readObject();
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
				serverModel.setPassword(un, pw, em);
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
				serverModel.setStatus(un, "Online");
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
		public void scpSendMsg()
		{
			String sender = "";
			String reciever="";
			String message = "";
			try {
				sender = (String)stringIn.readObject();
				reciever = (String)stringIn.readObject();
				message = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			serverModel.sendMessage(sender, reciever, message);
			System.out.println(message);

		}

		public void scpUpdateLog()
		{
			String user1="";
			String user2="";
			try {
				user1 = (String)stringIn.readObject();
				user2 = (String)stringIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuilder allMessages=new StringBuilder();
			HashMap<Timestamp, String> messages= serverModel.getMessages(user1, user2);
			LinkedList<Timestamp> keys=new LinkedList<Timestamp>();
			for(Timestamp time: messages.keySet()) {
				keys.add(time);
			}
			keys.sort(Comparator.naturalOrder());
			for(Timestamp time: keys) {
				String message=messages.get(time);
				allMessages.append(message+"\n");
			}
			try {
				stringOut.writeObject(allMessages.toString());
				System.out.println(allMessages.toString());
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
				case "sendMsg":
					scpSendMsg();
					break;
				case "updateLog":
					scpUpdateLog();
					break;
				case "addFriend":
					scpAddFriend();
					break;
				/*case "delFriend":
					scpDelFriends();
					break;*/
				case "getFriends":
					scpGetFriends();
					break;
				case "getUsers":
					scpGetUsers();
					break;
				}
			}
			
		}

		private void scpGetFriends() {
			// TODO Auto-generated method stub
		String un = "";
		try{
			un = (String)stringIn.readObject();
			LinkedList<String> frList = serverModel.getFriendList(un);
			if(frList.isEmpty()) {
				stringOut.writeObject(frList);
			}else{
				//LinkedList<String> a = new LinkedList<String>();
				stringOut.writeObject(frList);
			}
		}catch (ClassNotFoundException | IOException e){

			}
		}

	/*	private void scpDelFriends() {
			// TODO Auto-generated method stub

		}*/

		private void scpAddFriend() {
			// TODO Auto-generated method stub
			LinkedList<String> frList = new LinkedList<String>();
			String currentUser = "";
			String addUser = "";
			try{
				currentUser = (String)stringIn.readObject();
				addUser = (String)stringIn.readObject();
			}catch (ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
			serverModel.addNewFriend(currentUser,addUser);
		}








		public void scpGetUsers()
		{
			LinkedList<String> users= serverModel.getUsers();
			try {
				stringOut.writeObject(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}




}
