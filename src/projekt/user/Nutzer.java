package projekt.user;

import java.io.IOException;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

public class Nutzer extends Personen {
	
	private BasicTHMChatServer server;
	
	public Nutzer(AktuellerBenutzer benutzer) {
		server = new BasicTHMChatServer();
		try {
			listePersonen = server.getUsers(benutzer.getBenutzerName(), benutzer.getPasswort());
		} catch (IllegalArgumentException e) {
			System.out.println("Fehlerhafte Anmeldedaten");
		} catch (IOException e) {
			System.out.println("Fehlerhafte Anmeldedaten");
		}
		
	}
	
	

	@Override
	public String[] getListe() {
		// TODO Auto-generated method stub
		String[] tmpArString = new String[listePersonen.length];
		
		for( int i = 0; i<listePersonen.length; i++)
		{
			tmpArString[i] = (i+1) + ". " + listePersonen[i];
		}
		
		return tmpArString;
	}

}
