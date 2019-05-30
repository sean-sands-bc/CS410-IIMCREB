package iimcrebServer;

public class ServerView {
	public static void main(String[] args)
	{
		ServerModel sm = new ServerModel();
		ServerController sc = new ServerController(sm);
		ServerWindow sw = new ServerWindow();
		sc.start();
	}

}



