package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ListIsEmptyException extends Exception{
	
	/*
	Akkor ker�l megh�v�sra, ha a felhaszn�l� olyan list�b�l szeretne t�r�lni, ami �res �s nem 
	tartalmaz semmilyen adatot. Err�l grafikus megjelen�t�ssel �rtes�ti a felhaszn�l�t
	 */
	public ListIsEmptyException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "�res list�b�l nincsen mit t�r�lni!",
			    "�res lista",
			    JOptionPane.ERROR_MESSAGE);
	}

}
