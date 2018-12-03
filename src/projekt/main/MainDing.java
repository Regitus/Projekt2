package projekt.main;

import java.io.IOException;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

public class MainDing {
	
	public static void main(String[] args)
	{
		String pass = "0000";
		String[] arSTring;
		BasicTHMChatServer test = new BasicTHMChatServer();
		try {
			arSTring = test.getUsers("Rene_Terry", pass);
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
		try {
			arSTring = test.getMostRecentMessages("Rene_Terry", pass);
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
		
	}

}
