package projekt.user;

public class AktuellerBenutzer {
	
	private String benutzerName = "";
	private String passwort = "";
	
	
	/**
	 * @return Gibt den Benutzernamen zur�ck
	 */
	public String getBenutzerName() {
		return benutzerName;
	}
	/**
	 * @param Setzt den Usernamen
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
	 * @param Gibt das Passwort wieder aus
	 */
	public void setPasswort(String password) {
		this.passwort = password;
	}
	
	

}
