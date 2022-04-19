package main;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import appearance.Menu;


public class Main {
	/*Ez az oszt�ly a program f� oszt�lya itt vannak a program f� elemei p�ld�ul az �sszes receptet 
	tartalmaz� lista �s maga a program is ezzel az oszt�llyal ind�that�.*/
	
	public static ArrayList<Recipe> recipes = new ArrayList<Recipe>(); // Az �sszes receptet tartalmaz� lista
	public static HashSet<String> ingredientsSetString = new HashSet<String>(); // Az �sszes alapanyag nev�t t�rol� hashset
	public static HashSet<Ingredient> ingredientsSet = new HashSet<Ingredient>(); // Az �sszes alapanyagot t�rol� hashset
	public static Collator coll = Collator.getInstance(new Locale("hu","HU")); // A megfelel� Magyar ABC bet�rend�rt felel�s
	
	// V�gig megy az �sszes recepten �s egy recepten bel�l v�gig megy az alapanyagokon majd egy list�ban ki�rja az alapanyagok nev�t
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
	
	// V�gig megy az �sszes recepten �s egy recepten bel�l v�gig megy az alapanyagokon majd egy list�ban elmenti �ket
	public static ArrayList<Ingredient> listIngredients(){
		for(int i=0;i<recipes.size();i++) {
			for(int j=0;j<recipes.get(i).getIngredients().size();j++) {
				ingredientsSet.add(recipes.get(i).getIngredients().get(j));
			}
		}
		ArrayList<Ingredient> list= new ArrayList<Ingredient>(ingredientsSet);
		return list;
	}
	
	// Ezzel a f�ggv�nnyel ind�that� a program, megh�vja a f�jl beolvas�sokat �s a megjelen�ti a f�men�t
	public static void main(String[] args) {
		FileManagement.readFile("src/main/register.txt");
		FileManagement.readIngredientsFile();
		Menu.main(args);
		

	}

}
