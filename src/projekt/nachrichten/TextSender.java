package projekt.nachrichten;

import java.io.IOException;

public class TextSender extends Sender
{
	/**
	 * Anmeldedaten abspeichern
	 * @param benutzerName
	 * @param passwort
	 */
    public TextSender(String benutzerName, String passwort)
    {
        super(benutzerName, passwort);
    }

    @Override
    protected boolean sendenZuServer(String empfaenger, String daten) throws IOException, IllegalArgumentException
    {
        server.sendTextMessage(benutzerName, passwort, empfaenger, daten);
        return true;
    }
    
    /**
	 * Liest die 100 letzten Nachrichten aus und gibt sie als String Array zurück
	 * 
	 * @return String[]; String Array mit bis zu 100 Einträgen in der ausgehende und
	 *         eingehene Nachrichten des User gelistet sind
	 */
	public String[] getNachrichten()
	{
		String[] error;
		try
		{
			return server.getMostRecentMessages(benutzerName, passwort);

		} catch (IllegalArgumentException e)
		{
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		} catch (IOException e)
		{
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		}

	}
	
	/**
	 * 
	 * @param id Die eingegebene ID von der aus alle Nachrichten angezeigt werden die neuer sind
	 * @return String Array mit den Nachrichten
	 */
	public String[] getIDNachrichten(long id)
	{
		String[] error;
		try
		{
			return server.getMessages(benutzerName, passwort, id);

		} catch (IllegalArgumentException e)
		{
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		} catch (IOException e)
		{
			error = new String[1];
			error[0] = "Fehler beim Anmelden!";
			return error;
		}

	}
}