package iimcrebClient;

public class ClientView {
	public static void main(String[] args)
	{
		ClientController cc = new ClientController();
		MainWindow mw = new MainWindow(cc);
	}
}
