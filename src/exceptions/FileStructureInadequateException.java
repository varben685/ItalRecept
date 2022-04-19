package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileStructureInadequateException extends Exception{
	
	/*Akkor ker�l megh�v�sra, ha a bet�lteni k�v�nt f�jl szerkezete nem megfelel� ezt grafikusan 
	meg is jelen�ti a felhaszn�l�nak*/
	public FileStructureInadequateException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "A f�jl szerkezete nem megfelel�!",
			    "Hib�s f�jl szerkezet",
			    JOptionPane.ERROR_MESSAGE);
	}

}