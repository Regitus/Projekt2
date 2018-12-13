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
	 * �ber den Konstruktor wird die Gruppe mit einem Namen und einer Liste der Gruppe mit ihren direkten Namen gespeichert
	 * @param name Dies ist der Gruppen name
	 * @param liste String Array mit der Liste der Nutzer der Gruppe
	 */
	public Gruppe(String name, String[] liste)
	{
		listePersonen = liste;
		gruppenName = name;
	}
	
	
	/**
	 * R�ckgabe des Namen der Gruppe
	 * @return String; Name der Gruppe
	 */
	public String getGruppenName() {
		return gruppenName;
	}

	@Override
	public String[] getListe() {
		// TODO Auto-generated method stub
		return listePersonen;
	}

}
