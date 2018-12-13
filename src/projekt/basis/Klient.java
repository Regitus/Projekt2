package projekt.basis;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import projekt.nachrichten.BildSender;
import projekt.nachrichten.Sender;
import projekt.nachrichten.TextSender;
import projekt.user.*;

public class Klient
{

	private AktuellerBenutzer benutzer; // = new AktuellerBenutzer();
	private Nutzer nutzer;
	private ArrayList<Gruppe> listeDerGruppen = new ArrayList<Gruppe>();
	TextSender textSenden;
	BildSender bildSenden;

	/**
	 * Erstmaliges Anmelden und Instanz der Nutzerklasse definieren
	 */
	public Klient() {
		anmelden();
		nutzer = new Nutzer(benutzer);

	}

	/**
	 * Neue Anmeldedaten speichern
	 */
	private void anmelden()
	{
		@SuppressWarnings("resource")
		Scanner inAnmelden = new Scanner(System.in);
		benutzer = new AktuellerBenutzer();
		inAnmelden = new Scanner(System.in);
		System.out.println("Benutzernamen eingeben");
		benutzer.setBenutzerName(inAnmelden.nextLine());
		System.out.println("Passwort eingeben");
		benutzer.setPasswort(inAnmelden.nextLine());

		// Neu Anmelden in den Sender Klassen
		textSenden = new TextSender(benutzer.getBenutzerName(), benutzer.getPasswort());
		bildSenden = new BildSender(benutzer.getBenutzerName(), benutzer.getPasswort());

		// Neue Nutzerliste für den User holen
		if (nutzer != null)
		{
			nutzer.holeListeNeu(benutzer);
		}
	}

	/**
	 * Das Hauptmenu des Programmes Weiterverlinkung zum Gruppenmenü und Sendenmenü
	 */

	public void programm()
	{
		Scanner inMain = new Scanner(System.in);
		boolean run = true;
		do
		{
			System.out.println("\n1. Neue Anmeldedaten" + "\n2. Die letzten 100 Nachrichten"
					+ "\n3. Neuer als eine bestimmte ID ausgeben" + "\n4. Senden Menü" + "\n5. Gruppenverwaltung"
					+ "\n6. Alle Nutzer ausgeben" + "\n99 Beenden");
			switch (inMain.nextInt())
			{
			case 1: // Neues anmelden
				anmelden();
				break;

			case 2: // Gibt die letzten 100 Nachrichten aus
				ausgebenNeuesteNachrichten();
				break;

			case 3: // Neuer als eine bestimmte ID
				ausgebenNeuerAlsIDNachrichten();
				break;

			case 4: // Wechselns ins Senden Menü
				zeigeSendenMenu();
				break;

			case 5: // Wechseln ins Gruppen Menü
				zeigeGruppenMenu();
				break;

			case 6: // Alle möglichen Nutzer ausgeben
				ausgebenStringArray(nutzer.getListe());
				break;

			case 99: // Programm beenden
				inMain.close();
				run = false;
				break;

			default:
				break;
			}

		} while (run);
	}

	/**
	 * Startmethode um die bis zu 100 letzten Nachrichten auszugeben
	 */
	private void ausgebenNeuesteNachrichten()
	{
		String[] tmpString;

		// Holen der Nachrichten
		tmpString = textSenden.getNachrichten();
		ausgebenStringArray(tmpString);
	}

	/**
	 * Gibt Nachrichten aus die neuer sind als eine eingegebene ID
	 */
	private void ausgebenNeuerAlsIDNachrichten()
	{

		String[] tmpString;
		@SuppressWarnings("resource")
		Scanner inAlsID = new Scanner(System.in);

		System.out.println("Bitte geben sie eine Nachrichten ID an. Von dieser ID an, werden alle neueren angezeigt.");
		try
		{
			// Nachrichten neuer als ID holen
			tmpString = textSenden.getIDNachrichten(inAlsID.nextLong());
			ausgebenStringArray(tmpString);
		} catch (InputMismatchException e)
		{
			// TODO: handle exception
			System.out.println("Falsche Eingabe, Ausgabe abgebrochen");
		}
	}

	/**
	 * Methode zum allgemeinen Ausgeben eines String Array auf der Konsole
	 * 
	 * @param arString; Array eines String übergeben und wird auf der Konsole
	 *        übergeben
	 */
	private void ausgebenStringArray(String[] arString)
	{
		if (arString != null)
		{
			for (String text : arString)
			{
				System.out.println(text);
			}
		}
	}
	/* SENDEN MENÜ */

	/**
	 * Das Menü für das versenden der Nachricht
	 */
	private void zeigeSendenMenu()
	{
		@SuppressWarnings("resource")
		Scanner inSend = new Scanner(System.in);
		boolean runSenden = true;
		try
		{
			do
			{
				System.out.println("1. Nachricht an Person mit ID mit Liste angezeigt"
						+ "\n2. Nachricht an Person mit ID ohne Liste angezeigt" + "\n3. Nachricht an Gruppe"
						+ "\n4. Bildnachricht an Person mit ID mit Liste angezeigt"
						+ "\n5. Bildnachricht an Person mit ID ohne Liste angezeigt" + "\n6. Bildnachricht an Gruppe"
						+ "\n99 Zurueck");

				switch (inSend.nextInt())
				{
				case 1: // Nachrichten verschicken und vorher ID liste anzeigen
					ausgebenStringArray(nutzer.getListe());
					versendeNachricht(textSenden);
					break;
				case 2: // Nachricht verschicken
					versendeNachricht(textSenden);

					break;
				case 3: // An Gruppe schicken
					versendeNachrichtAnGruppe(textSenden);
					break;
				case 4: // Bildnachricht mit ID Liste vorher
					ausgebenStringArray(nutzer.getListe());
					versendeNachricht(bildSenden);
					break;
				case 5: // Bildnachricht
					versendeNachricht(bildSenden);
					break;
				case 6: // Bildnachricht Gruppe
					versendeNachrichtAnGruppe(bildSenden);
					break;
				case 99:
					runSenden = false;
					break;

				default:
					break;
				}

			} while (runSenden);
		} catch (InputMismatchException e)
		{
			System.out.print("Fehlerhafte Eingabe. Bitte erneut eingeben");
		}

	}

	/**
	 * Einfache Textnachricht versenden
	 * @param sender; Instant von TextSender oder BildSender an die Methode übergeben
	 */
	private void versendeNachricht(Sender sender)
	{
		int tmpID;
		String name;

		@SuppressWarnings("resource")
		Scanner inNachricht = new Scanner(System.in);
		// Erst ID des Empfängers einholen
		System.out.println("Bitte die ID angeben:");
		tmpID = Integer.parseInt(inNachricht.nextLine());
		name = nutzer.getNameDurchID(tmpID);
		if (name != null)
		{
			// Dann Text einholen und abschicken
			if (sender instanceof BildSender)
			{
				// Dateipfad einholen
				System.out.println("Nun bitte den Dateipfad komplett angeben:");
				
			}else System.out.println("Nun bitte den Text eingeben:");
			
			sender.senden(nutzer.getNameDurchID(tmpID), inNachricht.nextLine());
		} else
			System.out.println("Ungültige ID");
	}

	/**
	 * Nachricht an Gruppe versenden.
	 * @param sender; Instant von TextSender oder BildSender an die Methode übergeben
	 */
	private void versendeNachrichtAnGruppe(Sender sender)
	{
		Gruppe tmpGruppe;
		String[] tmpListePersonen;
		int tmpID;
		String nachrichtstext;

		@SuppressWarnings("resource")
		Scanner inAnGruppe = new Scanner(System.in);

		if (listeDerGruppen.size() != 0)
		{
			auflistenGruppeUeberName(); // Erst ID der Gruppen angeben
			// Dann ID eingeben lassen
			System.out.println("Bitte die ID der Gruppe angeben");
			tmpID = Integer.parseInt(inAnGruppe.nextLine());
			if (tmpID <= listeDerGruppen.size() && tmpID != 0)
			{
				tmpGruppe = listeDerGruppen.get(tmpID - 1);
				tmpListePersonen = tmpGruppe.getListe();
				
				// Text einholen
				if (sender instanceof BildSender)
				{
					System.out.println("Nun bitte den Dateipfad komplett angeben:");
					
				} else 	System.out.println("Bitte geben sie nun den Text an");
				
				nachrichtstext = inAnGruppe.nextLine();

				for (String person : tmpListePersonen)
				{
					if (!sender.senden(person, nachrichtstext)) // Solange Nachricht versenden true zurückgibt weitermachen
					{
						break;
					}
				}

			} else
			{
				System.out.println("Fehlerhafte ID");
			}
		} else
		{
			System.out.println("Keine Gruppen bisher erstellt");
		}

	}


	/* GRUPPENVERWALTUNG */

	/**
	 * Steuerungsmenü für die Gruppenverwaltung
	 */
	private void zeigeGruppenMenu()
	{
		boolean runGruppe = true;

		@SuppressWarnings("resource")
		Scanner inGruppe = new Scanner(System.in);
		try
		{

			do
			{

				System.out.println("\n1. Zeige alle Gruppen mit ihren Namen" + "\n2. Neue Gruppe" + "\n99 Zurueck");

				switch (inGruppe.nextInt())
				{

				case 1:
					// Methode die die ArrayList durcharbeitet und Name ausgibt mit einer ID
					auflistenGruppeUeberName();
					break;

				case 2:
					// Methode zur Erstellung einer neuen Gruppe. Erst ID ausgeben und dann solange
					// IDs eingeben bis man 0 eingibt.
					// Bei 0 beenden und Gruppe erstellen
					erstelleNeueGruppe();
					break;

				case 99:
					runGruppe = false;
					break;

				default:
					break;
				}

			} while (runGruppe);
		} catch (InputMismatchException e)
		{
			System.out.print("Fehlerhafte Eingabe. Bitte erneut eingeben");
		}

	}

	/**
	 * Erstellen einer neuen Gruppe und anhängen an die ArrayList der Gruppe
	 */
	private void erstelleNeueGruppe()
	{
		@SuppressWarnings("resource")
		Scanner inNeueGruppe = new Scanner(System.in);
		// TODO Auto-generated method stub
		ArrayList<Integer> idLIste = new ArrayList<Integer>(); // Dynamische Liste für ID Werte
		String[] tmpListeNutzer; // Stringarray zum übergeben an Gruppe. Größe wird aufgrungd der ArrayList
									// definiert
		int idWert; // Eingabewert der ID

		ausgebenStringArray(nutzer.getListe());
		System.out.println("Bitte geben sie nacheinander die ID und bestaetigen sie jede ID mit Enter."
				+ "\nZum beenden bitte 0 tippen");
		try
		{
			do // IDs einlesen über eine do-while Schleife
			{
				idWert = Integer.parseInt(inNeueGruppe.nextLine());
				if (idWert != 0 && idWert >= 1 && idWert <= nutzer.getArrayLaenge())
				{
					idLIste.add(idWert);
				}
			} while (idWert != 0); // Ende IDs einlesen

			if (idLIste.size() != 0) // Liste muss natürlich etwas enthalten
			{
				tmpListeNutzer = new String[idLIste.size()];

				for (int i = 0; i < idLIste.size(); i++)
				{

					tmpListeNutzer[i] = nutzer.getNameDurchID(idLIste.get(i)); // Name aus der Nutzer KLasse holen

				}

				// Der Gruppe einen Namen geben und die Gruppe erstellen
				System.out.println("Bitte nun noch den Namen der Gruppe angeben");
				listeDerGruppen.add(new Gruppe(inNeueGruppe.nextLine(), tmpListeNutzer));
			}

		} catch (InputMismatchException e)
		{
			System.out.println("Fehlerhafte Eingabe. Gruppenerstellung wird abgebrochen");
		}
	}

	/**
	 * Gruppen über Namen auflisten
	 */
	private void auflistenGruppeUeberName()
	{
		Gruppe tmpGruppe;
		for (int i = 0; i < listeDerGruppen.size(); i++)
		{
			tmpGruppe = listeDerGruppen.get(i);
			System.out.println(i + 1 + ". " + tmpGruppe.getGruppenName());
		}

	}

}
