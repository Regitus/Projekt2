package projekt.nachrichten;

import java.io.IOException;

public class TextSender extends Sender
{
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
}