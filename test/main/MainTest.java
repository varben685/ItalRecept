package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	/* A programban szereplõ osztályok, metódusok tesztelése*/
	
	Recipe r1; // Létrehoz egy receptet
	ArrayList<Ingredient> list; // Létrehoz egy alapanyogat tartalmazó listát
	Ingredient i1; // Egy alapanyag
	ArrayList<String> d1; // Tárol egy elkészítési leírást
	ArrayList<Recipe> rlist; // Tárolja a recepteket
	
	// Minden teszt elõtt lefut és incializálja az adattagokat
	@Before
	public void setUp() {
		i1 = new Ingredient("Teszt alapanyag",1.5,"dl");
		list = new ArrayList<Ingredient>();
		list.add(i1);
		d1 = new ArrayList<String>();
		d1.add("Ez egy teszt elkészítési leírás.");
		r1 = new Recipe("Teszt recept",list,d1);
		rlist = new ArrayList<Recipe>();
	}

	@Test
	public void testSetName() {
		String res = "Megváltozott név";
		r1.setName("Megváltozott név");
		assertEquals("Recipe name set",res,r1.getName());
	}
	
	@Test
	public void testGetName() {
		String res = "Teszt recept";
		assertEquals("Recipe name get",res,r1.getName());
	}
	
	@Test
	public void testSetQuantity() {
		double d = 4.5;
		i1.setQuantity(4.5);
		assertEquals(d,i1.getQuantity(),0.01);
	}
	
	@Test
	public void testSetDirections() {
		ArrayList<String> d2 = new ArrayList<String>();
		d2.add("Ez egy teszt és erre állítom át a recept leírását.");
		r1.setDirections(d2);
		assertArrayEquals(d2.toArray(),r1.getDirections().toArray());
	}
	
	@Test
	public void testSetIngredients() {
		ArrayList<Ingredient> list2 = new ArrayList<Ingredient>();
		Ingredient i2 = new Ingredient("Egy másik teszt alapanyag",3.5,"dl");
		list2.add(i2);
		r1.setIngredients(list2);
		assertTrue(r1.getIngredients().equals(list2));
	}
	
	@Test
	public void testRemoveIngredient() {
		r1.removeIngredient(i1);
		assertTrue(r1.getIngredients().isEmpty());
	}
	
	@Test
	public void testSearchByName() {
		Recipe r2 = new Recipe("2. recept név",list,d1);
		rlist.add(r2);
		rlist.add(r1);
		String name = "Teszt recept";
		int index = 1;
		
		assertEquals(1,Search.searchByName(rlist, name));
	}
	
	@Test
	public void testSearchByFilteredIngredients() {
		ArrayList<String> in = new ArrayList<String>();
		in.add("Teszt alapanyag");
		rlist.clear();
		Recipe r2 = new Recipe("2. recept név",list,d1);
		rlist.add(r2);
		rlist.add(r1);
		ArrayList<Recipe> jok = new ArrayList<Recipe>();
		jok.add(r2);
		jok.add(r1);
		
		assertTrue(jok.equals(Search.searchByFilteredIngredients(rlist, in)));
	}
	
	@Test
	public void testSearchByIngredientName() {
		Ingredient i2 = new Ingredient("Ezt kell keresni",2.5,"cl");
		list.add(i2);
		assertEquals(i2.getUnit(),Search.searchByIngredientName(list, i2.getName()));
	}
	
	@Test
	public void testReadFile() {
		try {
			FileManagement.readFile("test/main/testfile.txt");
		}
		catch(Exception e) {
			Assert.fail("Hiba történt a fájl beolvasásakor");
		}
	}

}
