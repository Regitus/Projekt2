package projekt.main;

import java.io.IOException;
import java.util.Scanner;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

public class MainDing {
	
	public static void main(String[] args)
	{
		String pass = "0000";
		String user = "Bla";
		
		Scanner in = new Scanner(System.in);
		System.out.println("User");
		user = in.next();
		System.out.println("Pass");
		pass = in.next();
		
		String[] arSTring;
		BasicTHMChatServer test = new BasicTHMChatServer();
		try {
			arSTring = test.getUsers(user, pass);
			for (String tmpString : arSTring)//(int i = 0; i<arSTring.length; i++)
			{
				System.out.println(tmpString);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			arSTring = test.getMostRecentMessages(user, pass);
			for (int i = 0; i<arSTring.length; i++)
			{
				System.out.println(arSTring[i]);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			test.sendTextMessage(user, pass, "TimSchoett", "Hi :D");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
