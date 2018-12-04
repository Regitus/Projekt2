package projekt.Basis;

import java.io.IOException;
import java.util.Scanner;

import de.thm.oop.chat.base.server.BasicTHMChatServer;
import projekt.User.*;

public class Hauptprogramm {
	
	private AktuellerBenutzer benutzer; // = new AktuellerBenutzer(); 
	private BasicTHMChatServer server = new BasicTHMChatServer();
	private Scanner in;
	
	
	public Hauptprogramm()
	{
		anmelden();
	}
	
	private void anmelden()
	{
		benutzer = new AktuellerBenutzer();
		in = new Scanner(System.in);
		System.out.println("User");
		benutzer.setBenutzerName(in.next());
		System.out.println("Pass");
		benutzer.setPasswort(in.next());
	}
	
	public void programm()
	{
		boolean run = true;
		do
		{
			System.out.println( 	"1. Neue Anmeldedaten" +
									"\n2. Die letzten 100 Nachrichten" + 
									"\n99 Beenden");
			switch (in.nextInt()) {
			case 1:
				anmelden();
				break;
				
			case 2:
				ausgebenNeuesteNachrichten();
				break;
				
			case 99:
				run = false;
				break;
				

			default:
				break;
			}
		
		}while(run);
	}
	
	private void ausgebenNeuesteNachrichten()
	{
		String[] tmpString;
		tmpString = getNachrichten();
		if (tmpString != null)
		{
			for (String nachricht : tmpString)
			{
				System.out.println(nachricht);
			}
		}
	}
	
	private String[] getNachrichten()
	{
		String[] error;
		try {
			return server.getMostRecentMessages(benutzer.getBenutzerName(), benutzer.getPasswort());
			
		} catch (IllegalArgumentException e) {
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		} catch (IOException e) {
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		}
		
	}
	

}
