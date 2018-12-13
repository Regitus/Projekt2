package projekt.nachrichten;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class BildSender extends Sender
{
	/**
	 * Anmeldedaten übergeben
	 * @param benutzerName Benutzername als String
	 * @param passwort	Passwort im Klartext als String
	 */
    public BildSender(String benutzerName, String passwort)
    {
        super(benutzerName, passwort);
    }

    /**
     * Liest die Bild-Datei und sendet sie zum Server
     * @param empfaenger derjenige, der das Bild bekommt
     * @param daten der Dateipfad des Bildes
     * @return Gibt zurück, ob das Bild geschickt wurde.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Override
    protected boolean sendenZuServer(String empfaenger, String daten) throws IOException, IllegalArgumentException
    {
        String dateiTyp = getDateiTyp(daten);	//Dateiendung ermitteln und prüfen
        if(!dateiTyp.equals("jpg") && !dateiTyp.equals("png"))
        {
            return false;
        }

        try	//Dateipfad auslesen und prï¿½fen ob alles okay ist
        {
            String typ = Files.probeContentType(Paths.get(daten));
            InputStream stream = new FileInputStream(daten);

            server.sendImageMessage(benutzerName, passwort, empfaenger, typ, stream);
        }
        catch (FileSystemNotFoundException ex)
        {
            System.out.println("Die Datei wurde nicht gefunden.");
        }
        catch (SecurityException ex)
        {
            System.out.println("Aus Sicherheitsgründen darf die Datei nicht geöffnet werden.");
        }
        catch (IOException ex)
        {
            System.out.println("Ein Fehler ist während des Auslesens der Datei aufgetreten.");
        }

        return false;
    }

    /**
     * Ermitteln der Dateiendung
     * @param pfad	Dateipfad
     * @return	String mit Endung
     */
    private String getDateiTyp(String pfad)
    {
        int index = pfad.lastIndexOf('.');
        return (index > 0) ? pfad.substring(index + 1) : "";
    }
}