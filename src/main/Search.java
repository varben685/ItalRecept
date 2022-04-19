package main;

import java.util.ArrayList;

public class Search {
	
	/* Ebben az osztályban találhatóak a keresési mûveletek, ha bármilyen keresés van a 
	programban akkor az innen kerül meghívásra */
	
	/* Végig megy a paraméterként kapott listán és összehasonlítja a receptek nevét a paraméterben kapott stringgel, 
	amennyiben van pontos egyezés akkor visszaadja a listában szereplõ indexét */
	public static int searchByName(ArrayList<Recipe> r,String recipename) {
		int index =0;
		for(int i=0;i<r.size();i++) {
			if(r.get(i).getName().equals(recipename)) {
				index =i;
			}
		}
		return index;
	}
	
	/* Végig megy a paraméterként kapott listán és összehasonlítja az alapanyagok nevét a paraméterben kapott stringgel, 
	amennyiben van pontos egyezés akkor visszaadja az alapanyag nevét */
	public static String searchByIngredientName(ArrayList<Ingredient> r,String ingredientname) {
		for(int i=0;i<r.size();i++) {
			if(r.get(i).getName().equals(ingredientname)) {
				return r.get(i).getUnit();
			}
		}
		return null;
	}
	
	/* Végig megy a paraméterként kapott listán és összehasonlítja a recept nevét a paraméterben kapott stringgel, 
	amennyiben van bármilyen egyezés akkor visszaadja a receptet */
	public static ArrayList<Recipe> searchByText(ArrayList<Recipe> list,String text){
		ArrayList<Recipe> recipes= new ArrayList<Recipe>();
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
				recipes.add(list.get(i));
			}
		}
		return recipes;
	}
	
	/* Végig megy az összes recepten és egy recept összes alapanyagán majd összehasonlítja a paraméterként kapott alapanyagokkal,
	 * ha van pontos egyezés akkor a receptet belerakja egy külön listába. Madj ezt a listát visszadja.
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
