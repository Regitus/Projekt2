package projekt.basis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import de.thm.oop.chat.base.server.BasicTHMChatServer;
import projekt.nachrichten.BildSender;
import projekt.nachrichten.TextSender;
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
		TextSender text = new TextSender(benutzer.getBenutzerName(), benutzer.getPasswort());
		BildSender bild = new BildSender(benutzer.getBenutzerName(), benutzer.getPasswort());
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
				in.close();
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
	 * Liest die 100 letzten Nachrichten aus und gibt sie als String Array zurück
	 * @return String[]; String Array mit bis zu 100 Einträgen in der ausgehende und eingehene Nachrichten des User gelistet sind
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
	 * @param arString; Array eines String übergeben und wird auf der Konsole übergeben
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
	 * Steuerungsmenü für die Gruppenverwaltung
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
				auflistenGruppeUeberName();
				break;
				
			case 3:
				//Methode zur Erstellung einer neuen Gruppe. Erst ID ausgeben und dann solange IDs eingeben bis man 0 eingibt.
				//Bei 0 beenden und Gruppe erstellen
				erstelleNeueGruppe();
				break;
				
			case 4:
				//Lösche Gruppe mit der ID, 0 zum abbrechen
				break;
			case 99:
				runGruppe = false;
				break;
				

			default:
				break;
			}
		
		}while(runGruppe);
		
		
	}
	
	/**
	 * Erstellen einer neuen Gruppe und anhängen an die ArrayList der Gruppe
	 */
	private void erstelleNeueGruppe() {
		// TODO Auto-generated method stub
		ArrayList<Integer> idLIste = new ArrayList<Integer>(); //Dynamische Liste für ID Werte
		String[] tmpListeNutzer;								//Stringarray zum übergeben an Gruppe. Größe wird aufgrungd der ArrayList definiert
		int idWert;												//Eingabewert der ID
		
		ausgebenStringArray(nutzer.getListe());
		System.out.println("Bitte geben sie nacheinander die ID und bestaetigen sie jede ID mit Enter."
				+ "\nZum beenden bitte 0 tippen");
		try
		{
			do // IDs einlesen über eine do-while Schleife
			{
				idWert = in.nextInt();
				if (idWert != 0 && idWert >= 1 && idWert <= nutzer.getArrayLaenge())
				{
					idLIste.add(idWert);
				}
			}while(idWert != 0); //Ende IDs einlesen
			
			
			if (idLIste.size() != 0) //Liste muss natürlich etwas enthalten
			{
				tmpListeNutzer = new String[idLIste.size()];
				
				for (int i = 0; i<idLIste.size(); i++)
				{
					
					tmpListeNutzer[i] = nutzer.getNameDurchID(idLIste.get(i)); //Name aus der Nutzer KLasse holen
					
				}
				
				//Der Gruppe einen Namen geben und die Gruppe erstellen
				System.out.println("Bitte nun noch den Namen der Gruppe angeben");
				listeDerGruppen.add(new Gruppe(in.next(), tmpListeNutzer));
			}
			
			
		}
		catch(InputMismatchException e)
		{
			System.out.println("Fehlerhafte Eingabe. Gruppenerstellung wird abgebrochen");
		}
	}
	
	/**
	 * Gruppen über Namen auflisten
	 */
	private void auflistenGruppeUeberName() {
		Gruppe tmpGruppe;
		for (int i = 0; i<listeDerGruppen.size(); i++)
		{
			tmpGruppe = listeDerGruppen.get(i);
			System.out.println(i+1 + ". " + tmpGruppe.getGruppenName());
		}
		
	}
	

}
