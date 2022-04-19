package appearance;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.FileManagement;
import main.Ingredient;
import main.Main;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class NewIngredient extends JFrame {
	
	/* Létrehoz egy új ablakot, ahol a felhasználónak lehetõsége van létrehozni egy új alapanyagot */
	
	private JPanel contentPane; // Erre a panelra kerülnek a gombok, szövegek
	private JTextField txtName; // Az alapanyag nevének beviteli mezõje
	private JTextField txtUnit; // Az alapanyag mértékegységének beviteli mezõje
	public static HashSet<Ingredient> ingredientsSet = new HashSet<Ingredient>(); // Tárolja az eddigi létrehozott alapanyagokat

	/**
	 * Az oztály fõ függvénye létrehozza a kerete és megjeleníti az ablakot
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewIngredient frame = new NewIngredient();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Létrehozza az ablak elemeit majd hozzáadja a kerethez. Illetve kezeli a felhaszálói interakciokat
	 */
	public NewIngredient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u00DAj alapanyag l\u00E9trehoz\u00E1sa");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 55, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNv, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNv, -111, SpringLayout.SOUTH, contentPane);
		lblNv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNv);
		
		JLabel lblMrtkegysg = new JLabel("M\u00E9rt\u00E9kegys\u00E9g:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMrtkegysg, 23, SpringLayout.SOUTH, lblNv);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblMrtkegysg, 10, SpringLayout.WEST, contentPane);
		lblMrtkegysg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblMrtkegysg);
		
		txtName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, txtName, 87, SpringLayout.EAST, lblNv);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtName, 0, SpringLayout.SOUTH, lblNv);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUnit = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtUnit, 5, SpringLayout.NORTH, lblMrtkegysg);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtUnit, 6, SpringLayout.EAST, lblMrtkegysg);
		txtUnit.setColumns(10);
		contentPane.add(txtUnit);
		
		JButton btnSave = new JButton("Ment\u00E9s");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtName.getText().equals("")) {
					main.Ingredient ingredient = new main.Ingredient(txtName.getText(), txtUnit.getText());
					Main.ingredientsSet.add(ingredient);
					Main.ingredientsSetString.add(ingredient.getName());
					ingredientsSet.add(ingredient);
					Menu.dlmIngredients.clear();
					for(int i=0;i<Main.listIngredientsString().size();i++) {
						Menu.dlmIngredients.addElement(Main.listIngredientsString().get(i));
					}
					Menu.lstIngredients.setModel(Menu.dlmIngredients);
					txtName.setText("");
					txtUnit.setText("");
					JOptionPane.showMessageDialog(null, "Sikeres mentés!");
				}
				else {
					new exceptions.IngredientParameterIsEmptyException(null);
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSave, 100, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, contentPane);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnSave);
	}
}
