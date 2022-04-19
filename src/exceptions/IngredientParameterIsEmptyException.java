package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class IngredientParameterIsEmptyException extends Exception{
	
	/* Akkor kerül meghívásra, ha a felhasználó egy alapanyag megadásakor vagy létrehozásakor 
	üresen hagyja az adatok mezõit.*/
	public IngredientParameterIsEmptyException(JFrame frame,String  a) {
		JOptionPane.showMessageDialog(frame,
			    "Nem lehet üres a "+ a+ " és a név mezõ!",
			    "Alapanyag beviteli mezõ üres",
			    JOptionPane.ERROR_MESSAGE);
	}
	public IngredientParameterIsEmptyException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "Nem lehet üres a név mezõ!",
			    "Alapanyag beviteli mezõ üres",
			    JOptionPane.ERROR_MESSAGE);
	}

}