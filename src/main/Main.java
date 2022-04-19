package main;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import appearance.Menu;


public class Main {
	/*Ez az osztály a program fõ osztálya itt vannak a program fõ elemei például az összes receptet 
	tartalmazó lista és maga a program is ezzel az osztállyal indítható.*/
	
	public static ArrayList<Recipe> recipes = new ArrayList<Recipe>(); // Az összes receptet tartalmazó lista
	public static HashSet<String> ingredientsSetString = new HashSet<String>(); // Az összes alapanyag nevét tároló hashset
	public static HashSet<Ingredient> ingredientsSet = new HashSet<Ingredient>(); // Az összes alapanyagot tároló hashset
	public static Collator coll = Collator.getInstance(new Locale("hu","HU")); // A megfelelõ Magyar ABC betûrendért felelõs
	
	// Végig megy az összes recepten és egy recepten belül végig megy az alapanyagokon majd egy listában kiírja az alapanyagok nevét
	public static ArrayList<String> listIngredientsString(){
		for(int i=0;i<recipes.size();i++) {
			for(int j=0;j<recipes.get(i).getIngredients().size();j++) {
				ingredientsSetString.add(recipes.get(i).getIngredients().get(j).getName());
			}
		}
		
		ArrayList<String> list = new ArrayList<String>(ingredientsSetString);
		Collections.sort(list,coll);
		return list;
	}
	
	// Végig megy az összes recepten és egy recepten belül végig megy az alapanyagokon majd egy listában elmenti õket
	public static ArrayList<Ingredient> listIngredients(){
		for(int i=0;i<recipes.size();i++) {
			for(int j=0;j<recipes.get(i).getIngredients().size();j++) {
				ingredientsSet.add(recipes.get(i).getIngredients().get(j));
			}
		}
		ArrayList<Ingredient> list= new ArrayList<Ingredient>(ingredientsSet);
		return list;
	}
	
	// Ezzel a függvénnyel indítható a program, meghívja a fájl beolvasásokat és a megjeleníti a fõmenüt
	public static void main(String[] args) {
		FileManagement.readFile("src/main/register.txt");
		FileManagement.readIngredientsFile();
		Menu.main(args);
		

	}

}
