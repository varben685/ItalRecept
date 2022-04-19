package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class IngredientParameterIsEmptyException extends Exception{
	
	/* Akkor ker�l megh�v�sra, ha a felhaszn�l� egy alapanyag megad�sakor vagy l�trehoz�sakor 
	�resen hagyja az adatok mez�it.*/
	public IngredientParameterIsEmptyException(JFrame frame,String  a) {
		JOptionPane.showMessageDialog(frame,
			    "Nem lehet �res a "+ a+ " �s a n�v mez�!",
			    "Alapanyag beviteli mez� �res",
			    JOptionPane.ERROR_MESSAGE);
	}
	public IngredientParameterIsEmptyException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "Nem lehet �res a n�v mez�!",
			    "Alapanyag beviteli mez� �res",
			    JOptionPane.ERROR_MESSAGE);
	}

}