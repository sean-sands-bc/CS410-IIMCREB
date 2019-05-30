package iimcrebServer;

public class ServerView {
	public static void main(String[] args)
	{
		ServerController sc = new ServerController();
		ServerWindow sw = new ServerWindow();
		sc.start();
	}

}



