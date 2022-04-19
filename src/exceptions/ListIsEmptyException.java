package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ListIsEmptyException extends Exception{
	
	/*
	Akkor kerül meghívásra, ha a felhasználó olyan listából szeretne törölni, ami üres és nem 
	tartalmaz semmilyen adatot. Errõl grafikus megjelenítéssel értesíti a felhasználót
	 */
	public ListIsEmptyException(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "Üres listából nincsen mit törölni!",
			    "Üres lista",
			    JOptionPane.ERROR_MESSAGE);
	}

}
