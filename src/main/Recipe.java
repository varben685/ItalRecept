package main;
import java.util.ArrayList;

public class Recipe implements Comparable<Recipe> {
	/* Egy receptet tároló osztály*/
	
	private String name; // A recept nevét tárolja 
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); // Egy recepthez tartozó alapanyagokat tárolja
	private ArrayList<String> directions = new ArrayList<String>(); // Egy recepthez tartozó elkészítési leírást tárolja
	
	// AZ osztály konstruktorra, beállítja a paraméterként kapott értékeket.
	public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> directions) {
		this.name = name;
		this.ingredients = ingredients;
		this.directions = directions;
	}
	
	// Visszaadja a name adattag értékét
	public String getName() {
		return name;
	}
	
	// Visszaadja az ingredients adattag elemét
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	// Visszaadja a directions adattag elemét
	public ArrayList<String> getDirections(){
		return directions;
	}
	
	// Beállítja a name adattag értékét a kapott paraméterre
	public void setName(String name) {
		this.name = name;
	}
	
	// Beállítja a directions adattag értékét a kapott paraméterre
	public void setDirections(ArrayList<String> directions) {
		this.directions = directions;
	}
	
	// Beállítja az ingredients adattag értékét a kapott paraméterre
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	// Kitörli a paraméterben kapott alapanyagot a receptbõl
	public void removeIngredient(Ingredient ingredient) {
		
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getName().equals(ingredient.getName())) {
				ingredients.remove(i);
			}
		}
	}
	
	// Kiírja egy recept nevét
	public String toString() {
		return name;
	}

	// Összes hasonlít két receptet a nevük alapján
	public int compareTo(Recipe o) {
		return this.getName().compareTo(o.getName());
	}

}
