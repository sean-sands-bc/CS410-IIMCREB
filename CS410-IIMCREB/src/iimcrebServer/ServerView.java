package iimcrebServer;

public class ServerView {
	public static void main(String[] args)
	{
		ServerSQLLink s=new ServerSQLLink();
		ServerModel sm = new ServerModel(s);
		ServerController sc = new ServerController(sm);
		ServerWindow sw = new ServerWindow();
		sc.start();
	}

}



