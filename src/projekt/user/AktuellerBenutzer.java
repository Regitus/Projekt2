package projekt.user;

/**
 * Anmeldedaten des Benutzers werden hier gespeichert.
 * !ACHTUNG! Passwort im Klartext intern temporär gesichert
 * @author David, Rene, Tim
 *
 */
public class AktuellerBenutzer {
	
	private String benutzerName = "";
	private String passwort = "";
	
	
	/**
	 * @return Gibt den Benutzernamen zurück
	 */
	public String getBenutzerName() {
		return benutzerName;
	}
	/**
	 * Benutzernamen speichern
	 * @param userName Setzt den Benutzernamen
	 * 
	 */
	public void setBenutzerName(String userName) {
		this.benutzerName = userName;
	}
	
	
	/**
	 * Passwort Variable: Achtung Klartext!
	 * 
	 * @return Speichert das Passwort
	 */
	public String getPasswort() {
		return passwort;
	}
	/**
	 * @param password Gibt das Passwort wieder aus
	 */
	public void setPasswort(String password) {
		this.passwort = password;
	}
	
	

}
