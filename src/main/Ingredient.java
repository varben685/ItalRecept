package main;

public class Ingredient {
	
	// Egy alapanyagról tárol tulajdonságokat
	
	private String name; // Tárolja az alapanyag nevét
	private double quantity; // Tárolja az alapanyag mennyiségét
	private String unit; // Tárolja az alapanyag mennyiségét amiben majd a mennysigégét szeretnénk megadni
	
	/* Az osztály egyik konstruktora. A paraméterként kapott értékeket beállítja az adattagoknak. 
		Akkor kerül meghívásra ha a felhasználó megtud adni mennyíséget */
	public Ingredient(String name,double quantity,String unit) {
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	/* Az osztály egyik konstruktora. A paraméterként kapott értékeket beállítja az adattagoknak. 
	 * Akkor kerül meghívásra, ha a felhaszáló csak simán egy alapanyagok hozz létre
	 */
	public Ingredient(String name,String unit) {
		this.name = name;
		this.unit = unit;
	}
	
	// Visszadja a name adattag értéket
	public String getName() {
		return name;
	}
	
	// Visszadja a quantity adattag értéket
	public double getQuantity() {
		return quantity;
	}
	
	//Beállítja a quantity adattag értéket a paraméterként kapott értékre
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	//Beállítja a unit adattag értéket a paraméterként kapott értékre
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	// Visszadja a unit adattag értéket
	public String getUnit() {
		return unit;
	}
	
	// Visszadja az alapanyag kiíratásakor használt stringet
	public String toString() {
		if(unit==null) {
			return name;
		}
		return quantity+" "+unit+" "+name;
	}

}
