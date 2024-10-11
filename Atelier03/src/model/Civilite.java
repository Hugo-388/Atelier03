package model;

public class Civilite {
	/** Attributs de la classe 
	 * civiliteIdt : Identifiant 
	 * civiliteLbl : Libellé long
	 * civiliteLbc : libellé court 
	 * **/ 
	private int civiliteIdt; 
	private String civiliteLbl; 
	private String civiliteLbc;



	public Civilite(int civiliteIdt, String civiliteLbl, String civiliteLbc) {
		super();
		this.civiliteIdt = civiliteIdt;
		this.civiliteLbl = civiliteLbl;
		this.civiliteLbc = civiliteLbc;
	}

	public String affichage() {
		return "Civilite [civiliteIdt=" + civiliteIdt + ", civiliteLbl=" + civiliteLbl + ", civiliteLbc=" + civiliteLbc + "]";
	} 

	@Override
	public String toString() {
		return civiliteLbl;
	}

	public int getCiviliteIdt() {
		return civiliteIdt;
	}

	public String getCiviliteLbl() {
		return civiliteLbl;
	}

	public void setCiviliteLbl(String civiliteLbl) {
		this.civiliteLbl = civiliteLbl;
	}


}
