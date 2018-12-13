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
	 * Anmeldedaten �bergeben
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
     * @return Gibt zur�ck, ob das Bild geschickt wurde.
     * @throws IOException Fehlerhafte Eingabe
     * @throws IllegalArgumentException Nicht erlaubtes Argument
     */
    @Override
    protected boolean sendenZuServer(String empfaenger, String daten) throws IOException, IllegalArgumentException
    {
        String dateiTyp = getDateiTyp(daten);	//Dateiendung ermitteln und pr�fen
        if(!dateiTyp.equals("jpg") && !dateiTyp.equals("png"))
        {
            return false;
        }

        try	//Dateipfad auslesen und pr�fen ob alles okay ist
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
            System.out.println("Aus Sicherheitsgr�nden darf die Datei nicht ge�ffnet werden.");
        }
        catch (IOException ex)
        {
            System.out.println("Ein Fehler ist w�hrend des Auslesens der Datei aufgetreten.");
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