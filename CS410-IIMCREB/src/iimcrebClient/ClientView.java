package iimcrebClient;

public class ClientView {
	public static void main(String[] args)
	{
		ClientController cc = new ClientController();
		MainWindow mw = new MainWindow(cc);
		//AddFriend af = new AddFriend(cc);
		//DeleteFriend df = new DeleteFriend(cc);
		//ChatWindow cw = new ChatWindow(cc,"test2");
	}

}
