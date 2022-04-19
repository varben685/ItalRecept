package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class FileManagement {
	
	/*Ez az osztály felelõs a fileokkal való mûveletekért írja és olvassa az „register.txt” és az 
	„ingredients.txt” fájlt. */
	
	// A paraméterben kapott fájl beolvassa. Végig megy a fájl minden során és feldolgozza a kapott adatot
	public static void readFile(String path) {
		try {
			FileReader fr = new FileReader(path,StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String line = br.readLine();
				
				if(line == null) {
					break;
				}
				
				String[] array = line.split(";");
				String[] ingredientsString = array[1].split("/");
				String[] directions = array[2].split("\\. ");
				
				ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
				ArrayList<String> directionsList = new ArrayList<String>();
				
				for(int i =0;i<ingredientsString.length;i++) {
					String[] ingredient = ingredientsString[i].split("-");
					ingredients.add(new Ingredient(ingredient[0],Double.parseDouble(ingredient[1]),ingredient[2]));
					
				}
				for(int i=0;i<directions.length;i++) {
					directionsList.add(directions[i]);
				}
				Main.recipes.add(new Recipe(array[0],ingredients,directionsList));
			}
			Collections.sort(Main.recipes);
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// Végig megy a paraméterben kapott listán és kiírja az adatokat egy fájlba
	public static void writeFile(ArrayList<Recipe> recipes) {
		try {
			FileWriter fw = new FileWriter("src/main/register.txt",StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(fw);
			String line ="";
			for(int i=0;i<recipes.size();i++) {
				line+=recipes.get(i).getName();
				line+=";";
				for(int j=0;j<recipes.get(i).getIngredients().size();j++) {
					line+=recipes.get(i).getIngredients().get(j).getName();
					line+="-";
					line+=recipes.get(i).getIngredients().get(j).getQuantity();
					line+="-";
					if(j!=recipes.get(i).getIngredients().size()-1) {
						line+=recipes.get(i).getIngredients().get(j).getUnit();
						line+="/";
					}
					else {
						line+=recipes.get(i).getIngredients().get(j).getUnit();
					}
					
				}
				line+=";";
				for(int j=0;j<recipes.get(i).getDirections().size();j++) {
					if(j!=recipes.get(i).getDirections().size()-1) {
						line+=recipes.get(i).getDirections().get(j);
						line+=". ";
					}
					else {
						line+=recipes.get(i).getDirections().get(j);
					}
					
				}
				if(i!=recipes.size()-1) {
					line+="\n";
				}
			}
			pw.print(line);
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Végig megy a fájl összes során és beolvassa feldolgozza egy sor tartalmát
	public static void readIngredientsFile() {
		FileReader fr;
		try {
			fr = new FileReader("src/main/ingredients.txt",StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line= br.readLine();
				if(line==null) {
					break;
				}
				String[] array = line.split(";");
				main.Ingredient ingredient = new Ingredient(array[0],array[1]);
				Main.ingredientsSet.add(ingredient);
				Main.ingredientsSetString.add(ingredient.getName());
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Az ujonnan létrehozott alapanyagokat írja ki egy fájlba
	public static void writeIngredientsFile() {
		
		try {
			FileWriter fw = new FileWriter("src/main/ingredients.txt",StandardCharsets.UTF_8,true);
			PrintWriter pw = new PrintWriter(fw);
			String line ="";
			ArrayList<Ingredient> list= new ArrayList<Ingredient>(appearance.NewIngredient.ingredientsSet);
			for(int i=0;i<list.size();i++) {
				
				line+=list.get(i).getName()+";"+list.get(i).getUnit();
				if(i!=list.size()-1) {
					line+="\n";
				}
			}
			pw.print(line);
			
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
