package iimcrebServer;

import java.io.IOException;
import java.net.*;

public class ServerController {
	int port = 8675;
	
	ServerSocket serverSocket;
	
	public ServerController()
	{
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
				ServerClientProtocol scp = new ServerClientProtocol(s);
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
		
		Socket socket;
		String username;
		
		public ServerClientProtocol(Socket s)
		{
			socket = s;
			System.out.println("client connection");
		}

		@Override
		public void run() {
			//	only accept local connections
			if (!socket.getInetAddress().isLoopbackAddress())
			{
				System.out.println("nonlocal address");
			    return;
			}
			
		}
		
	}




}
