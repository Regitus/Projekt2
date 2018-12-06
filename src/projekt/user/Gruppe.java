package projekt.user;

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
	
	
	public String getGruppenName() {
		return gruppenName;
	}

	/**
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
