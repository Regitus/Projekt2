package projekt.user;

import java.io.IOException;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

/**
 * Diese Klasse speichert die komplette Nutzerliste ab, die der Server für den User zur Verfügung stellt.
 * Wird intern gespeichert um den Aufruf zum Server zum verringern, kann aber aktualisiert werden
 * @author David, Rene, Tim
 *
 */
public class Nutzer extends Personen {
	
	private BasicTHMChatServer server;
	
	/**
	 * Beim erstellen einer Instanz dieser Klasse wird automatisch die Nutzerliste vom Server abgerufen.
	 * @param benutzer; Instanz der AktuellenBenutzer Klasse mit Anmeldedaten
	 */
	public Nutzer(AktuellerBenutzer benutzer) {
		aktualisieren(benutzer);
	}
	
	/**
	 * Erneutes aktualisieren der Nutzerliste vom Server
	 * @param benutzer
	 */
	private void aktualisieren(AktuellerBenutzer benutzer)
	{
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
	
	/**
	 * Anfordern des Namen eines Nutzer.
	 * @param id Nummer der Person aus der Listenausgabe. (Intern dann ID-1)
	 * @return	Gibt den Namen als String wieder
	 */
	public String getNameDurchID(int id)
	{
		return listePersonen[id-1];
		
	}
	
	public void holeListeNeu(AktuellerBenutzer benutzer)
	{
		aktualisieren(benutzer);
	}
	
	

}
