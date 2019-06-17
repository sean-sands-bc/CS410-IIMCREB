package iimcrebServer;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;

public class ServerSQLLink {
	Connection conn = null;
	
	public ServerSQLLink() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/IIMCREBData?serverTimezone=UTC&useSSL=TRUE";
            String user, pass;
            user = "student";
            pass = "password";
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException e){
            System.out.println ("Could not load the driver");
        }

        catch (SQLException ex) {
            System.out.println(ex);
        } 
		//Removed to work, but may be needed somewhere
//		finally {
//            if (s.conn != null) {
//                try {
//                    s.conn.close();
//                } catch (SQLException e) { /* ignored */}
//            }
//        }
    }
//	String readEntry(String prompt) {
//        try {
//            StringBuffer buffer = new StringBuffer();
//            System.out.print(prompt);
//            System.out.flush();
//            int c = System.in.read();
//            while(c != '\n' && c != -1) {
//                buffer.append((char)c);
//                c = System.in.read();
//            }
//            return buffer.toString().trim();
//        } catch (IOException e) {
//            return "";
//        }
//    }
	//Determines if the given name is already in the database
	public boolean getName(String username) {
		try {
			String query = "select user_name from UID where user_name = ?";
			PreparedStatement p = conn.prepareStatement (query);
			p.clearParameters();
    		p.setString(1,username);
    		ResultSet r = p.executeQuery();
    		if(r.first())
    			return true;
    		else 
    			return false;
    	}catch(SQLException ex) { 
            return false;
    	}
	}
	//Returns the password associated with a given username
	public String getPassword(String username) {
		try {
			String query = "select password from UID where user_name = ?";
			PreparedStatement p = conn.prepareStatement (query);
			p.clearParameters();
    		p.setString(1,username);
    		ResultSet r = p.executeQuery();
    		String pass="";
    		while(r.next())
    			pass=r.getString(1);
    		return pass;
    	}catch(SQLException ex) { 
            return "";
    	}
	}
	//Creates a new user along with all their data
	public void newUser(String username, String password, String email) {
		try {
			String query = "insert into UID values (?,?,?,?)";
			PreparedStatement p = conn.prepareStatement (query);
			p.clearParameters();
			p.setString(1, username);
			p.setString(2, "Offline");
			p.setString(3, password);
			p.setString(4, email);
			p.executeUpdate();
		}catch(SQLException ex) {
			//System.out.println(ex);
		}
	}
	//Returns the status of a given username
	public String getStatus(String username) {
		try {
			String query = "select status from UID where user_name = ?";
			PreparedStatement p = conn.prepareStatement (query);
			p.clearParameters();
    		p.setString(1,username);
    		ResultSet r = p.executeQuery();
    		String stat="";
    		while(r.next())
    			stat=r.getString(1);
    		return stat;
    	}catch(SQLException ex) { 
            return "";
    	}
	}
	//Sets the status for a user
	public void setStatus(String username, String status) {
		try {
			String query="update UID set status=? where user_name=?";
			PreparedStatement p = conn.prepareStatement (query);
			p.setString(1, status);
			p.setString(2, username);
			p.executeUpdate();
		}catch(SQLException ex) {
			
		}
	}
	//Returns a HashMap, sorted by time, of messages send by one user to another
	public HashMap<Timestamp,String> getMessages(String sender, String reciever) {
		HashMap<Timestamp, String> messages=new HashMap<Timestamp, String>();
		try {
			String query="select * from ChattingDatabase where user_name_send = ? and user_name_recieve=?";
			PreparedStatement p = conn.prepareStatement(query);
    		p.clearParameters();
    		p.setString(1, sender);
			p.setString(2, reciever);
    		ResultSet r= p.executeQuery();
    		while(r.next()){
    			Timestamp time=r.getTimestamp(3);
    			String message=r.getString(1)+": "+r.getString(4);
    			messages.put(time, message);
    		}
		}
		catch(SQLException ex) { 
			System.out.println("error");
    	}
		return messages;
	}
	//Adds a message to the ChattingDatabase
	public void sendMessage(String sender, String reciever, String message) {
		try {
			String query= "insert into ChattingDatabase values (?,?,?,?)";
			PreparedStatement p = conn.prepareStatement(query);
    		p.clearParameters();
    		p.setString(1, sender);
			p.setString(2, reciever);
			java.util.Date date = new Date();
			Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
			p.setTimestamp(3, sqlDate);
			p.setString(4, message);
			p.executeUpdate();
		}
		catch(SQLException ex) { 
			System.out.println("error");
    	}
	}
	//Returns a HashMap, sorted by time, of all messages sent between users
	public HashMap<Timestamp,String> getAllMessages(String user1, String user2) {
		HashMap<Timestamp, String> oneToTwo=getMessages(user1,user2);
		HashMap<Timestamp, String> twoToOne=getMessages(user2,user1);
		oneToTwo.putAll(twoToOne);
		return oneToTwo;
	}
	//Returns a list of everyone the username is friends list
	public LinkedList<String> getFriendsList(String username){
		LinkedList<String> friends=new LinkedList<String>();
		try {
			String query="select user_name_friendee from FriendList where user_name_friender = ?";
			PreparedStatement p = conn.prepareStatement(query);
    		p.clearParameters();
    		p.setString(1, username);
    		ResultSet r= p.executeQuery();
    		while(r.next()){
    			String friend=r.getString(1);
    			friends.add(friend);
    		}
		}
		catch(SQLException ex) { 
			System.out.println("error");
    	}
		if(friends.size()==0){
			return new LinkedList<String>();
		}else{
			return friends;
		}
	}
	//Allows for a user to be added to a friendslist
	public void newFriend(String friender, String friendee) {
		try {
			String query= "insert into FriendList values (?,?)";
			PreparedStatement p = conn.prepareStatement(query);
    		p.clearParameters();
    		p.setString(1, friender);
			p.setString(2, friendee);
			p.executeUpdate();
		}
		catch(SQLException ex) { 
			System.out.println("error");
    	}
	}
	
	//Returns all the usernames in the database
		public LinkedList<String> userNames() {
			LinkedList<String> allUsers=new LinkedList<String>();
			try {
				String query1="select * from UID";
				PreparedStatement p = conn.prepareStatement(query1);
	    		p.clearParameters();
	    		ResultSet r1= p.executeQuery();
	    		while(r1.next()){
	    			String name=r1.getString(1);
	    			allUsers.add(name);
	    		}
			}
			catch(SQLException ex) { 
	            System.out.println("Could not find an account matching that user name and password.");
	    	}
			return allUsers;
		}
}
