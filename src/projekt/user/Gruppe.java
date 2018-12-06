package projekt.user;

/**
 * Jede Instanz einer Gruppe speichert eine Gruppe ab.
 * Dabei wird auch der Name der Gruppe mitgespeichert
 * @author David, Rene, Tim
 *
 */
public class Gruppe extends Personen {
	
	private String gruppenName;
	
	/**
	 * @return the gruppenName
	 */
	
	public Gruppe(String name, String[] liste)
	{
		listePersonen = liste;
		setGruppenName(name);
	}
	
	
	/**
	 * Rückgabe des Namen der Gruppe
	 * @return String; Name der Gruppe
	 */
	public String getGruppenName() {
		return gruppenName;
	}

	/**
	 * Setzen des Namen der Gruppe
	 * @param gruppenName the gruppenName to set
	 */
	public void setGruppenName(String gruppenName) {
		this.gruppenName = gruppenName;
	}



	@Override
	public String[] getListe() {
		// TODO Auto-generated method stub
		return listePersonen;
	}

}
