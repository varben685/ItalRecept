package main;
import java.util.ArrayList;

public class Recipe implements Comparable<Recipe> {
	/* Egy receptet t�rol� oszt�ly*/
	
	private String name; // A recept nev�t t�rolja 
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); // Egy recepthez tartoz� alapanyagokat t�rolja
	private ArrayList<String> directions = new ArrayList<String>(); // Egy recepthez tartoz� elk�sz�t�si le�r�st t�rolja
	
	// AZ oszt�ly konstruktorra, be�ll�tja a param�terk�nt kapott �rt�keket.
	public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> directions) {
		this.name = name;
		this.ingredients = ingredients;
		this.directions = directions;
	}
	
	// Visszaadja a name adattag �rt�k�t
	public String getName() {
		return name;
	}
	
	// Visszaadja az ingredients adattag elem�t
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	// Visszaadja a directions adattag elem�t
	public ArrayList<String> getDirections(){
		return directions;
	}
	
	// Be�ll�tja a name adattag �rt�k�t a kapott param�terre
	public void setName(String name) {
		this.name = name;
	}
	
	// Be�ll�tja a directions adattag �rt�k�t a kapott param�terre
	public void setDirections(ArrayList<String> directions) {
		this.directions = directions;
	}
	
	// Be�ll�tja az ingredients adattag �rt�k�t a kapott param�terre
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	// Kit�rli a param�terben kapott alapanyagot a receptb�l
	public void removeIngredient(Ingredient ingredient) {
		
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getName().equals(ingredient.getName())) {
				ingredients.remove(i);
			}
		}
	}
	
	// Ki�rja egy recept nev�t
	public String toString() {
		return name;
	}

	// �sszes hasonl�t k�t receptet a nev�k alapj�n
	public int compareTo(Recipe o) {
		return this.getName().compareTo(o.getName());
	}

}
