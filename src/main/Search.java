package main;

import java.util.ArrayList;

public class Search {
	
	/* Ebben az oszt�lyban tal�lhat�ak a keres�si m�veletek, ha b�rmilyen keres�s van a 
	programban akkor az innen ker�l megh�v�sra */
	
	/* V�gig megy a param�terk�nt kapott list�n �s �sszehasonl�tja a receptek nev�t a param�terben kapott stringgel, 
	amennyiben van pontos egyez�s akkor visszaadja a list�ban szerepl� index�t */
	public static int searchByName(ArrayList<Recipe> r,String recipename) {
		int index =0;
		for(int i=0;i<r.size();i++) {
			if(r.get(i).getName().equals(recipename)) {
				index =i;
			}
		}
		return index;
	}
	
	/* V�gig megy a param�terk�nt kapott list�n �s �sszehasonl�tja az alapanyagok nev�t a param�terben kapott stringgel, 
	amennyiben van pontos egyez�s akkor visszaadja az alapanyag nev�t */
	public static String searchByIngredientName(ArrayList<Ingredient> r,String ingredientname) {
		for(int i=0;i<r.size();i++) {
			if(r.get(i).getName().equals(ingredientname)) {
				return r.get(i).getUnit();
			}
		}
		return null;
	}
	
	/* V�gig megy a param�terk�nt kapott list�n �s �sszehasonl�tja a recept nev�t a param�terben kapott stringgel, 
	amennyiben van b�rmilyen egyez�s akkor visszaadja a receptet */
	public static ArrayList<Recipe> searchByText(ArrayList<Recipe> list,String text){
		ArrayList<Recipe> recipes= new ArrayList<Recipe>();
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
				recipes.add(list.get(i));
			}
		}
		return recipes;
	}
	
	/* V�gig megy az �sszes recepten �s egy recept �sszes alapanyag�n majd �sszehasonl�tja a param�terk�nt kapott alapanyagokkal,
	 * ha van pontos egyez�s akkor a receptet belerakja egy k�l�n list�ba. Madj ezt a list�t visszadja.
	 * */
	public static ArrayList<Recipe> searchByFilteredIngredients(ArrayList<Recipe> recipes,ArrayList<String> filtered){
		ArrayList<Recipe> filterRecipes = new ArrayList<Recipe>();
		ArrayList<String> list = new ArrayList<String>();
		for(int i =0;i<recipes.size();i++) {
			ArrayList<Ingredient> ingredients = recipes.get(i).getIngredients();
			list.clear();
			for(int j=0;j<ingredients.size();j++) {
				list.add(ingredients.get(j).getName());
			}
			if(list.containsAll(filtered)==true) {
				filterRecipes.add(recipes.get(i));
			}
			
		}
		return filterRecipes;
		
	}

}
