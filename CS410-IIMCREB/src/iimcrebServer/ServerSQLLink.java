package iimcrebServer;
import java.sql.*;
import java.util.*;
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
	String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while(c != '\n' && c != -1) {
                buffer.append((char)c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
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
	
	public void setPassword(String username, String password) {
		try {
			String query = "insert into UID values (?,?,?,?)";
			PreparedStatement p = conn.prepareStatement (query);
			p.clearParameters();
			p.setString(1, username);
			p.setString(2, "Offline");
			p.setString(3, password);
			p.setString(4, "0");
			p.executeUpdate();
		}catch(SQLException ex) {
			//System.out.println(ex);
		}
	}
	
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
	
	
	public void printUserNames() {
		try {
			String query1="select * from UID";
			PreparedStatement p = conn.prepareStatement(query1);
    		p.clearParameters();
    		ResultSet r1= p.executeQuery();
    		while(r1.next()){
    			String name=r1.getString(1);
    			System.out.println(name);
    		}
		}
		catch(SQLException ex) { 
            System.out.println("Could not find an account matching that user name and password.");
    	}
	}
}
