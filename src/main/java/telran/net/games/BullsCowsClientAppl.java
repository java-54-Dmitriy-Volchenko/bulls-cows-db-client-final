package telran.net.games;

import java.util.*;

import telran.net.TcpClient;
import telran.net.games.service.*;
import telran.view.*;
public class BullsCowsClientAppl {

	private static final int N_DIGITS = 4;
	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 5004;
	
	public static void main(String[] args) {
		String hostIp = args.length>0? args[0]:DEFAULT_HOST;
		
		int port = args.length>1? Integer.parseInt(args[1]):DEFAULT_PORT ;
		InputOutput inputOutput = new SystemInputOutput();
		
		TcpClient tcpClient = new TcpClient(hostIp, port);
		
	
		
		BullsCowsTcpProxy proxy = new BullsCowsTcpProxy(tcpClient);
		List<Item> items = BullsCowsApplicationItems.getItems(proxy, N_DIGITS);
		items.add(Item.of("Exit",
				io -> tcpClient.close(), true));
		Menu menu = new Menu("Bulls Cows Logical Network Game",
				items.toArray(Item[]::new));
		
		menu.perform(inputOutput);
	}


}
