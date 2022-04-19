package main;

public class Ingredient {
	
	// Egy alapanyagr�l t�rol tulajdons�gokat
	
	private String name; // T�rolja az alapanyag nev�t
	private double quantity; // T�rolja az alapanyag mennyis�g�t
	private String unit; // T�rolja az alapanyag mennyis�g�t amiben majd a mennysig�g�t szeretn�nk megadni
	
	/* Az oszt�ly egyik konstruktora. A param�terk�nt kapott �rt�keket be�ll�tja az adattagoknak. 
		Akkor ker�l megh�v�sra ha a felhaszn�l� megtud adni menny�s�get */
	public Ingredient(String name,double quantity,String unit) {
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	/* Az oszt�ly egyik konstruktora. A param�terk�nt kapott �rt�keket be�ll�tja az adattagoknak. 
	 * Akkor ker�l megh�v�sra, ha a felhasz�l� csak sim�n egy alapanyagok hozz l�tre
	 */
	public Ingredient(String name,String unit) {
		this.name = name;
		this.unit = unit;
	}
	
	// Visszadja a name adattag �rt�ket
	public String getName() {
		return name;
	}
	
	// Visszadja a quantity adattag �rt�ket
	public double getQuantity() {
		return quantity;
	}
	
	//Be�ll�tja a quantity adattag �rt�ket a param�terk�nt kapott �rt�kre
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	//Be�ll�tja a unit adattag �rt�ket a param�terk�nt kapott �rt�kre
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	// Visszadja a unit adattag �rt�ket
	public String getUnit() {
		return unit;
	}
	
	// Visszadja az alapanyag ki�rat�sakor haszn�lt stringet
	public String toString() {
		if(unit==null) {
			return name;
		}
		return quantity+" "+unit+" "+name;
	}

}
