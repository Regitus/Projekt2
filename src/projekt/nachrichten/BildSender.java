package projekt.nachrichten;

import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class BildSender extends Sender
{
    public BildSender(String benutzerName, String passwort)
    {
        super(benutzerName, passwort);
    }

    @Override
    protected boolean sendenZuServer(String empfaenger, String daten) throws IOException, IllegalArgumentException
    {
        Pair<InputStream, String> datei = getDatei(daten);
        if(datei == null)
        {
            return false;
        }

        server.sendImageMessage(benutzerName, passwort, empfaenger, datei.getValue(), datei.getKey());
        return true;
    }

    private Pair<InputStream, String> getDatei(String pfad)
    {
        String dateiTyp = getDateiTyp(pfad);
        if(dateiTyp != "jpg" && dateiTyp != "png")
        {
            return null;
        }

        try
        {
            String typ = Files.probeContentType(Paths.get(pfad));
            InputStream stream = new FileInputStream(pfad);

            return new Pair<>(stream, typ);
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

        return null;
    }

    private String getDateiTyp(String pfad)
    {
        int index = pfad.lastIndexOf('.');
        return (index > 0) ? pfad.substring(index + 1) : "";
    }
}