package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileStructureInadequateException extends Exception{
	
	/*Akkor kerül meghívásra, ha a betölteni kívánt fájl szerkezete nem megfelelõ ezt grafikusan 
	meg is jeleníti a felhasználónak*/
	public FileStructureInadequateException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "A fájl szerkezete nem megfelelõ!",
			    "Hibás fájl szerkezet",
			    JOptionPane.ERROR_MESSAGE);
	}

}