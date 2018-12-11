package projekt.nachrichten;

import de.thm.oop.chat.base.server.BasicTHMChatServer;

import java.io.IOException;

public abstract class Sender
{
    protected String benutzerName;
    protected String passwort;

    protected BasicTHMChatServer server;
    
    /**
     * Anmeldedaten übergeben
     * @param benutzerName 
     * @param passwort
     */
    protected Sender(String benutzerName, String passwort)
    {
        this.benutzerName = benutzerName;
        this.passwort = passwort;

        this.server = new BasicTHMChatServer();
    }
    
    /**
     * Logik zum versenden der Nachricht
     * @param empfaenger Empfänger Name als String
     * @param daten	Text oder Dateipfad
     * @return True wenn gesendet, falls wenn nicht
     */
    public boolean senden(String empfaenger, String daten)
    {
        try
        {
            return sendenZuServer(empfaenger, daten);
        }
        catch (IOException ex)
        {
            System.out.println("Keine Verbindung zum Server oder der Benutzername bzw. das Passwort ist falsch.");
            return false;
        }
    }

    protected abstract boolean sendenZuServer(String empfaenger, String daten) throws IOException, IllegalArgumentException;
}