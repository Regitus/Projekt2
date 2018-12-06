package projekt.basis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import de.thm.oop.chat.base.server.BasicTHMChatServer;
import projekt.user.*;

public class Klient {
	
	private AktuellerBenutzer benutzer; // = new AktuellerBenutzer(); 
	private BasicTHMChatServer server = new BasicTHMChatServer();
	private Nutzer nutzer;
	private ArrayList<Gruppe> listeDerGruppen = new ArrayList<Gruppe>();
	private Scanner in;
	
	
	public Klient()
	{
		anmelden();
		nutzer = new Nutzer(benutzer);
	}
	
	/**
	 * Neue Anmeldedaten speichern
	 */
	private void anmelden()
	{
		benutzer = new AktuellerBenutzer();
		in = new Scanner(System.in);
		System.out.println("Benutzernamen eingeben");
		benutzer.setBenutzerName(in.next());
		System.out.println("Passwort eingeben");
		benutzer.setPasswort(in.next());
	}
	
	/**
	 * Das Hauptmenu des Programmes
	 */
	
	public void programm()
	{
		boolean run = true;
		do
		{
			System.out.println( 	"1. Neue Anmeldedaten" +
									"\n2. Die letzten 100 Nachrichten" + 
									"\n3. Alle Nutzer ausgeben" + 
									"\n99 Beenden");
			switch (in.nextInt()) {
			case 1:
				anmelden();
				break;
				
			case 2:
				ausgebenNeuesteNachrichten();
				break;
				
			case 3:
				ausgebenStringArray(nutzer.getListe());
				break;
				
			case 4:
				zeigeGruppenMenu();
				break;
			case 99:
				run = false;
				break;
				

			default:
				break;
			}
		
		}while(run);
	}
	
	
	/**
	 * Startmethode um die bis zu 100 letzten Nachrichten auszugeben
	 */
	private void ausgebenNeuesteNachrichten()
	{
		String[] tmpString;
		tmpString = getNachrichten();
		if (tmpString != null)
		{
			ausgebenStringArray(tmpString);
		}
	}
	
	/**
	 * Liest die 100 letzten Nachrichten aus und gibt sie als String Array zur�ck
	 * @return String[]; String Array mit bis zu 100 Eintr�gen in der ausgehende und eingehene Nachrichten des User gelistet sind
	 */
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
	
	/**
	 * Methode zum allgemeinen Ausgeben eines String Array auf der Konsole
	 * @param arString; Array eines String �bergeben und wird auf der Konsole �bergeben
	 */
	private void ausgebenStringArray(String[] arString)
	{
		for (String text : arString)
		{
			System.out.println(text);
		}
	}
	
	
	/* GRUPPENVERWALTUNG */
	
	/**
	 * Steuerungsmen� f�r die Gruppenverwaltung
	 */
	private void zeigeGruppenMenu() {
		boolean runGruppe = true;
		do
		{
			System.out.println( 	"1. Nachricht an Gruppe" +
									"\n2. Zeige alle Gruppen mit ihren Namen" + 
									"\n3. Neue Gruppe" +
									"\n4. Gruppe loeschen" + 
									"\n99 Zurueck");
			switch (in.nextInt()) {
			case 1:
				//Nachrichtmethode
				break;
				
			case 2:
				//Methode die die ArrayList durcharbeitet und Name ausgibt mit einer ID
				break;
				
			case 3:
				//Methode zur Erstellung einer neuen Gruppe. Erst ID ausgeben und dann solange IDs eingeben bis man 0 eingibt.
				//Bei 0 beenden und Gruppe erstellen
				break;
				
			case 4:
				//L�sche Gruppe mit der ID, 0 zum abbrechen
				break;
			case 99:
				runGruppe = false;
				break;
				

			default:
				break;
			}
		
		}while(runGruppe);
		
		
	}
	

}