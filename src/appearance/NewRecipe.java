package appearance;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import exceptions.IngredientParameterIsEmptyException;
import main.Ingredient;
import main.Main;
import main.Recipe;
import main.Search;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewRecipe extends JFrame {
	/* Ez az osztály jeleníti meg egy új recept létrehozásához szükséges ablakot, illetve kezeli az 
	ablak használata során felmerülõ felhasználói interakciókat. */

	private JPanel contentPane; // Erre a panelre kerülnek az ablakban használt gombok, listák és szövegek
	private JTextField txtRecipeName; // A recept nevét tartalmazó beviteli mezõ
	private JTextField txtIngredientName; // Az alapanyag nevét tartalmazó beviteli mezõ
	private JTextField txtUnit; // Az alapanyag mértékegységét tartalmazó beviteli mezõ
	private JTextField txtQuantity; // Az alapanyag mennyiségét tartalmazó beviteli mezõ
	private JTextPane txtDirections = new JTextPane(); // A recept elkészítésének leírását tartalmazó beviteli mezõ
	public static ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); // Lista ami tárolja a recepthez eddig hozzáadott alapanyagokat
	private int selectedIndex=0; // Az összes alapanyagot tartalmazó listában kiválaszott elem indexét tárolja
	private DefaultListModel dlm2 = new DefaultListModel();
	private static NewRecipe frame = new NewRecipe();

	/**
	 * Láthatóvá teszi az ablakot és létrehozza a keretet
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public NewRecipe() {
		
		JList lstRegisterIngredients = new JList();
		lstRegisterIngredients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = (String) lstRegisterIngredients.getSelectedValue();
				txtIngredientName.setText(selected);
				txtUnit.setText(Search.searchByIngredientName(Main.listIngredients(), selected));
				
				
			}
		});
		lstRegisterIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultListModel dlm = new DefaultListModel();
				for(int i=0;i<Main.listIngredientsString().size();i++) {
					dlm.addElement(Main.listIngredientsString().get(i));
				}
				lstRegisterIngredients.setModel(dlm);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				DefaultListModel dlm = new DefaultListModel();
				for(int i=0;i<Main.listIngredientsString().size();i++) {
					dlm.addElement(Main.listIngredientsString().get(i));
				}
				lstRegisterIngredients.setModel(dlm);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u00DAj recept l\u00E9trehoz\u00E1sa");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 321, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNv, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNv, 10, SpringLayout.WEST, contentPane);
		lblNv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNv);
		
		txtRecipeName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtRecipeName, 5, SpringLayout.NORTH, lblNv);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtRecipeName, -682, SpringLayout.EAST, contentPane);
		contentPane.add(txtRecipeName);
		txtRecipeName.setColumns(10);
		
		JLabel lblAlapanyagok = new JLabel("Alapanyagok:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAlapanyagok, 25, SpringLayout.SOUTH, txtRecipeName);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAlapanyagok, 10, SpringLayout.WEST, contentPane);
		lblAlapanyagok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblAlapanyagok);
		
		JList lstIngredients = new JList();
		lstIngredients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedIndex = lstIngredients.getSelectedIndex();
			}
		});
		lstIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lstIngredients, 16, SpringLayout.SOUTH, lblAlapanyagok);
		sl_contentPane.putConstraint(SpringLayout.WEST, lstIngredients, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lstIngredients, -205, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lstIngredients, -672, SpringLayout.EAST, contentPane);
		lstIngredients.setBackground(SystemColor.controlShadow);
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, lstRegisterIngredients, 0, SpringLayout.NORTH, lstIngredients);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lstRegisterIngredients, -205, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lstRegisterIngredients, -91, SpringLayout.EAST, contentPane);
		lstRegisterIngredients.setBackground(SystemColor.controlShadow);
		
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 12, SpringLayout.SOUTH, lblAlapanyagok);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -666, SpringLayout.EAST, contentPane);
		scrollPane.setViewportView(lstIngredients);
		lstIngredients.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane2 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane2, 580, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane2, -96, SpringLayout.EAST, contentPane);
		scrollPane2.setViewportView(lstRegisterIngredients);
		lstRegisterIngredients.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(scrollPane2);
		
		JLabel lblANyilvntartsban = new JLabel("A nyilv\u00E1ntart\u00E1sban");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblANyilvntartsban, 428, SpringLayout.EAST, txtRecipeName);
		lblANyilvntartsban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblANyilvntartsban);
		
		JLabel lblEddigSzereplAlapanyagok = new JLabel("eddig szerepl\u0151 alapanyagok:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane2, 23, SpringLayout.SOUTH, lblEddigSzereplAlapanyagok);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblANyilvntartsban, -4, SpringLayout.NORTH, lblEddigSzereplAlapanyagok);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEddigSzereplAlapanyagok, 0, SpringLayout.NORTH, lblAlapanyagok);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblEddigSzereplAlapanyagok, -60, SpringLayout.EAST, contentPane);
		lblEddigSzereplAlapanyagok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblEddigSzereplAlapanyagok);
		
		JButton btnNewButton = new JButton("Hozz\u00E1ad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					main.Ingredient newIngredient = new main.Ingredient(txtIngredientName.getText(),Double.parseDouble(txtQuantity.getText()),txtUnit.getText());
					boolean b = false;
					if(ingredients.isEmpty()==false) {
						for(Ingredient i: ingredients) {
							if(i.getName().equals(newIngredient.getName())) {
								for(int j=0;j<dlm2.size();j++) {
									if(dlm2.get(j).equals(i.getQuantity()+" "+i.getUnit()+" "+i.getName())) {
										dlm2.remove(j);
										break;
									}
								}
								i.setQuantity(newIngredient.getQuantity());
								i.setUnit(newIngredient.getUnit());
								b = true;
								break;
							}
						}
						if(b!=true) {
							ingredients.add(newIngredient);
						}
						
					}
					else {
						ingredients.add(newIngredient);
					}
					for(int i=0;i<ingredients.size();i++) {
						if(!dlm2.contains(ingredients.get(i).toString())) {
							
							dlm2.addElement(ingredients.get(i).toString());
						}
					}
					lstIngredients.setModel(dlm2);
				}
				catch(Exception m) {
					new IngredientParameterIsEmptyException(null,"mennyiség");
				}
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 32, SpringLayout.EAST, lstIngredients);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnNewButton);
		
		JLabel lblNv_1 = new JLabel("N\u00E9v:");
		lblNv_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNv_1);
		
		txtIngredientName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtIngredientName, 127, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNv_1, -1, SpringLayout.NORTH, txtIngredientName);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNv_1, -80, SpringLayout.WEST, txtIngredientName);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtIngredientName, -52, SpringLayout.WEST, scrollPane2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lstRegisterIngredients, 85, SpringLayout.EAST, txtIngredientName);
		txtIngredientName.setColumns(10);
		contentPane.add(txtIngredientName);
		
		JLabel lblNv_1_1 = new JLabel("M\u00E9rt\u00E9kegys\u00E9g:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNv_1_1, 31, SpringLayout.SOUTH, lblNv_1);
		lblNv_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNv_1_1);
		
		txtUnit = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtUnit, 29, SpringLayout.SOUTH, txtIngredientName);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNv_1_1, -20, SpringLayout.WEST, txtUnit);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtUnit, 0, SpringLayout.WEST, txtIngredientName);
		txtUnit.setColumns(10);
		contentPane.add(txtUnit);
		
		JLabel lblNv_1_1_1 = new JLabel("Mennyis\u00E9g:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNv_1_1_1, 0, SpringLayout.WEST, lblNewLabel);
		lblNv_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNv_1_1_1);
		
		txtQuantity = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtQuantity, 32, SpringLayout.SOUTH, txtUnit);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNv_1_1_1, -1, SpringLayout.NORTH, txtQuantity);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtQuantity, 0, SpringLayout.EAST, txtIngredientName);
		txtQuantity.setColumns(10);
		contentPane.add(txtQuantity);
		
		JLabel lblElksztsLersa = new JLabel("Elk\u00E9sz\u00EDt\u00E9s le\u00EDr\u00E1sa:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -24, SpringLayout.NORTH, lblElksztsLersa);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblElksztsLersa, 44, SpringLayout.SOUTH, lstIngredients);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblElksztsLersa, 0, SpringLayout.EAST, txtRecipeName);
		lblElksztsLersa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblElksztsLersa);
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDirections, 11, SpringLayout.SOUTH, lblElksztsLersa);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDirections, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtDirections, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(txtDirections);
		
		JButton btnSaveRecipe = new JButton("Ment\u00E9s");
		btnSaveRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Igen",
                "Nem"};
				int n = JOptionPane.showOptionDialog(frame,
						"Biztosan elszeretné menteni a receptet?",
						"Recept mentése",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,  
						options, 
						options[0]); 
				if(n==0) {
					String[] directionsString = txtDirections.getText().split("\\. ");
					ArrayList<String> directions = new ArrayList<String>();
					Collections.addAll(directions, directionsString);
					ArrayList<String> directionS = (ArrayList<String>) directions.clone();
					ArrayList<Ingredient> ingredientS = (ArrayList<Ingredient>) ingredients.clone();
					main.Recipe recipe = new Recipe(txtRecipeName.getText(),ingredientS,directionS);
					Main.recipes.add(recipe);
					ingredients.clear();
					dlm2.clear();
					directions.clear();
					txtRecipeName.setText("");
					txtIngredientName.setText("");
					txtUnit.setText("");
					txtQuantity.setText("");
					txtDirections.setText("");
					
					Menu.recipesdlm.clear();
					for(int i=0;i<Main.recipes.size();i++) {
						Menu.recipesdlm.addElement(Main.recipes.get(i).getName());
					}
					Menu.lstRecipes.setModel(Menu.recipesdlm);
				}
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDirections, -252, SpringLayout.WEST, btnSaveRecipe);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane2, -90, SpringLayout.NORTH, btnSaveRecipe);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSaveRecipe, -53, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSaveRecipe, -118, SpringLayout.EAST, contentPane);
		btnSaveRecipe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnSaveRecipe);
		
		JButton btnTrl = new JButton("T\u00F6r\u00F6l");
		btnTrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dlm2.remove(selectedIndex);
					ingredients.remove(selectedIndex);
				}
				catch(Exception m) {
					new exceptions.ListIsEmptyException(null);
				}
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnTrl, 194, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnTrl, -18, SpringLayout.WEST, lblNv_1_1_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -33, SpringLayout.NORTH, btnTrl);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, btnTrl);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnTrl, 0, SpringLayout.SOUTH, txtQuantity);
		btnTrl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnTrl);
	}
}
