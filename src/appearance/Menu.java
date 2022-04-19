package appearance;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

import main.FileManagement;
import main.Ingredient;
import main.Main;
import main.Recipe;
import main.Search;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu {
	
	/* Ez az osztály a program fõmenüjét jeleníti meg a felhasználó számára. Az osztálynak vannak 
	private, public és protected adattagjai is */
	
	private int ingredientIndex =0; // Tárolja a kiválasztott alapanyag indexét
	private int sortedIngredientIndex =0; // A szûrt alapanyag listában kiválaszott alapanyag indexét tárolja
	private DefaultListModel dlm = new DefaultListModel(); // Fõ alap lista modell
	private ArrayList<String> selectedIngredients = new ArrayList<String>(); // Tárolja a kiválaszott alapanyagok nevét Stringként
	private JFrame frame; // Ez a fõ keret
	private String selectedRecipe = ""; // Tárolja a kiválaszott recept nevét
	public String fileName =""; // Tárolja a kiválaszott fájl nevét/elérési útvonalát
	public static DefaultListModel dlmIngredients = new DefaultListModel(); // Az alapanyag lista modellje
	public static JList lstIngredients = new JList(); // Alapanygokat tároló JList
	protected static DefaultListModel recipesdlm = new DefaultListModel(); // A receptek lista modellje
	protected static JList lstRecipes = new JList(); // Recepteket tároló JList

	/**
	 * Létrehozza az ablakot
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Meghívja a megfelelõ beállításokat
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Létrehozza az ablak elemeit és beállítja a tulajdonságait. Illetve figyeli és kezeli a felhasználói interkakciókat
	 */
	public void initialize() {
		
		frame = new JFrame();
		
		
		JList lstIngredientsList = new JList();
		JList lstDircetionsList = new JList();
		JScrollPane scrollPane4 = new JScrollPane();
		JScrollPane scrollPane3 = new JScrollPane();
		JLabel lblDirectionsList = new JLabel("Elk\u00E9sz\u00EDt\u00E9si folyamat:");
		JLabel lblIngredientsList = new JLabel("Sz\u00FCks\u00E9ges alapanyagok:");
		JLabel lblRecipeName = new JLabel("");
		JLabel lblRecipesName = new JLabel("N\u00E9v:");
		JList lstSortedIngredients = new JList();
		
		JButton btnDeleteRecipe = new JButton("T\u00F6rl\u00E9s");
		
		
		lstSortedIngredients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sortedIngredientIndex = lstSortedIngredients.getSelectedIndex();
			}
		});
		
		JButton btnEditRecipe = new JButton("Szerkeszt\u00E9s");
		btnEditRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRecipe.main(null,selectedRecipe);
			}
		});
		btnDeleteRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Igen",
                "Nem"};
				int n = JOptionPane.showOptionDialog(frame,
						"Biztosan törölni szeretné a receptet?",
						"Recept törlése",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,  
						options, 
						options[0]); 
				if(n==0) {
					Main.recipes.remove(Search.searchByName(Main.recipes, selectedRecipe));
					recipesdlm.clear();
					for(int i=0;i<Main.recipes.size();i++) {
						recipesdlm.addElement(Main.recipes.get(i).getName());
					}
					lstRecipes.setModel(recipesdlm);
				}
				
			}
		});
		
		scrollPane3.setVisible(false);
		scrollPane4.setVisible(false);
		lblRecipesName.setVisible(false);
		lblRecipeName.setVisible(false);
		lblIngredientsList.setVisible(false);
		lblDirectionsList.setVisible(false);
		btnEditRecipe.setVisible(false);
		btnDeleteRecipe.setVisible(false);

		frame.addWindowListener(new WindowAdapter() {
			
			public void windowOpened(WindowEvent e) {
				
				for(int i=0;i<Main.listIngredientsString().size();i++) {
					dlmIngredients.addElement(Main.listIngredientsString().get(i));
				}
				lstIngredients.setModel(dlmIngredients);
			}
		});
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(1000, 700));
		frame.setTitle("Italrecept nyilv\u00E1ntart\u00F3 2021");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		
		lstIngredients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ingredientIndex = lstIngredients.getSelectedIndex();
				
			}
		});
		
		lstRecipes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedRecipe = (String) lstRecipes.getSelectedValue();
				int index = Search.searchByName(Main.recipes, selectedRecipe);
				
				scrollPane3.setVisible(true);
				scrollPane4.setVisible(true);
				lblRecipesName.setVisible(true);
				lblRecipeName.setVisible(true);
				lblIngredientsList.setVisible(true);
				lblDirectionsList.setVisible(true);
				btnEditRecipe.setVisible(true);
				btnDeleteRecipe.setVisible(true);
				
				lblRecipeName.setText(Main.recipes.get(index).getName());
				
				DefaultListModel dlm = new DefaultListModel();
				ArrayList<Ingredient> ingredientsList = Main.recipes.get(index).getIngredients();
				
				DefaultListModel dlm2 = new DefaultListModel();
				ArrayList<String> directionsList = Main.recipes.get(index).getDirections();
				
				for(int i=0;i<ingredientsList.size();i++) {
					dlm.addElement(ingredientsList.get(i).toString());
				}
				lstIngredientsList.setModel(dlm);
				
				for(int i=0;i<directionsList.size();i++) {
					dlm2.addElement(directionsList.get(i).toString());
				}
				lstDircetionsList.setModel(dlm2);
				
			}
		});
		lstRecipes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		
		
		
		JButton btnNewRecipe = new JButton("\u00DAj recept hozz\u00E1ad\u00E1sa");
		btnNewRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecipe.main(null);
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnNewRecipe, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewRecipe, -463, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewRecipe, -707, SpringLayout.EAST, frame.getContentPane());
		btnNewRecipe.setMinimumSize(new Dimension(130, 22));
		btnNewRecipe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnNewRecipe);
		
		JButton btnListRecipes = new JButton("\u00D6sszes recept list\u00E1z\u00E1sa");
		btnListRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipesdlm.clear();
				
				for(int i=0;i<Main.recipes.size();i++) {
					recipesdlm.addElement(Main.recipes.get(i).getName());
				}
				lstRecipes.setModel(recipesdlm);
				
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnListRecipes, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnListRecipes, -388, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnListRecipes, 0, SpringLayout.EAST, btnNewRecipe);
		btnListRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnListRecipes);
		
		
		lstRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lstRecipes.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lstRecipes, 87, SpringLayout.SOUTH, btnListRecipes);
		springLayout.putConstraint(SpringLayout.WEST, lstRecipes, 0, SpringLayout.WEST, btnNewRecipe);
		springLayout.putConstraint(SpringLayout.EAST, lstRecipes, 474, SpringLayout.EAST, btnNewRecipe);
		lstRecipes.setVisibleRowCount(2);
		lstRecipes.setBackground(SystemColor.controlShadow);
		JScrollPane scrollPane1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 74, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, -736, SpringLayout.EAST, frame.getContentPane());
	    scrollPane1.setViewportView(lstRecipes);
	    lstRecipes.setLayoutOrientation(JList.VERTICAL);
		frame.getContentPane().add(scrollPane1);
		
		JLabel lblRecipes = new JLabel("Receptek");
		springLayout.putConstraint(SpringLayout.WEST, lblRecipes, 128, SpringLayout.WEST, frame.getContentPane());
		lblRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblRecipes);
		
		JLabel lblNewLabel = new JLabel("Egy recept r\u00E9szletes le\u00EDr\u00E1s\u00E1hoz");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -725, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lstRecipes, -16, SpringLayout.NORTH, lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEgyReceptetA = new JLabel("v\u00E1lassza ki a list\u00E1b\u00F3l!");
		springLayout.putConstraint(SpringLayout.SOUTH, lblEgyReceptetA, -217, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 6, SpringLayout.SOUTH, lblEgyReceptetA);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, lblEgyReceptetA);
		springLayout.putConstraint(SpringLayout.WEST, lblEgyReceptetA, 0, SpringLayout.WEST, scrollPane1);
		lblEgyReceptetA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblEgyReceptetA);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lstIngredients, 0, SpringLayout.NORTH, btnNewRecipe);
		springLayout.putConstraint(SpringLayout.WEST, lstIngredients, 91, SpringLayout.EAST, btnNewRecipe);
		springLayout.putConstraint(SpringLayout.SOUTH, lstIngredients, -384, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lstIngredients, 321, SpringLayout.EAST, lstRecipes);
		lstIngredients.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lstIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lstIngredients.setVisibleRowCount(2);
		lstIngredients.setBackground(SystemColor.controlShadow);
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 54, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 86, SpringLayout.EAST, btnNewRecipe);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 23, SpringLayout.SOUTH, btnListRecipes);
	    scrollPane.setViewportView(lstIngredients);
	    lstIngredients.setLayoutOrientation(JList.VERTICAL);
	    
		frame.getContentPane().add(scrollPane);
		
		
		lstSortedIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lstSortedIngredients, 356, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lstSortedIngredients, 212, SpringLayout.EAST, scrollPane1);
		springLayout.putConstraint(SpringLayout.SOUTH, lstSortedIngredients, -90, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lstSortedIngredients, -321, SpringLayout.EAST, frame.getContentPane());
		lstSortedIngredients.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JScrollPane scrollPane2 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -309, SpringLayout.WEST, scrollPane2);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane2, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane2, 826, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane2, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane2, -23, SpringLayout.EAST, frame.getContentPane());
	    scrollPane2.setViewportView(lstSortedIngredients);
	    lstSortedIngredients.setLayoutOrientation(JList.VERTICAL);
		lstSortedIngredients.setBackground(SystemColor.controlShadow);
		frame.getContentPane().add(scrollPane2);
		
		JLabel lblIngredients = new JLabel("Alapanyagok");
		springLayout.putConstraint(SpringLayout.SOUTH, lblIngredients, -6, SpringLayout.NORTH, scrollPane);
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblIngredients);
		
		JLabel lblSortedIngredients = new JLabel("Sz\u00FCrt alapanyagok");
		springLayout.putConstraint(SpringLayout.NORTH, lblSortedIngredients, 0, SpringLayout.NORTH, lblIngredients);
		springLayout.putConstraint(SpringLayout.WEST, lblSortedIngredients, 0, SpringLayout.WEST, scrollPane2);
		lblSortedIngredients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblSortedIngredients);
		
		JButton btnAddSorted = new JButton("Hozz\u00E1ad");
		btnAddSorted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIngredients.add(Main.listIngredientsString().get(ingredientIndex));
				
				for(int i=0;i<selectedIngredients.size();i++) {
					if(!dlm.contains(selectedIngredients.get(i))) {
						dlm.addElement(selectedIngredients.get(i));
					}
					
				}
				lstSortedIngredients.setModel(dlm);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAddSorted, 138, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAddSorted, 95, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnAddSorted, -100, SpringLayout.WEST, scrollPane2);
		btnAddSorted.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnAddSorted);
		
		JButton btnDeleteSorted = new JButton("T\u00F6rl\u00E9s");
		btnDeleteSorted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dlm.remove(sortedIngredientIndex);
					selectedIngredients.remove(sortedIngredientIndex);
				}
				catch(Exception m) {
					new exceptions.ListIsEmptyException(frame);
				}
			}
		});
		btnDeleteSorted.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnDeleteSorted);
		
		JButton btnKeress = new JButton("Sz\u0171r\u00E9s");
		btnKeress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel dlm = new DefaultListModel();
				ArrayList<Recipe> recipes = Search.searchByFilteredIngredients(Main.recipes, selectedIngredients);
				for(int i=0;i<recipes.size();i++) {
					dlm.addElement(recipes.get(i).getName());
				}
				lstRecipes.setModel(dlm);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnKeress, 0, SpringLayout.NORTH, btnDeleteSorted);
		springLayout.putConstraint(SpringLayout.EAST, btnKeress, -6, SpringLayout.WEST, btnDeleteSorted);
		btnKeress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnKeress);
		
		JTextArea txtSearchByName = new JTextArea();
		
		txtSearchByName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String text = txtSearchByName.getText();
				ArrayList<Recipe> recipes = Search.searchByText(Main.recipes, text);
				DefaultListModel dlm = new DefaultListModel();
				for(int i=0;i<recipes.size();i++) {
					dlm.addElement(recipes.get(i).getName());
				}
				lstRecipes.setModel(dlm);
			}
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, lblRecipes, 6, SpringLayout.SOUTH, txtSearchByName);
		springLayout.putConstraint(SpringLayout.WEST, txtSearchByName, 92, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtSearchByName, 0, SpringLayout.SOUTH, btnDeleteSorted);
		springLayout.putConstraint(SpringLayout.EAST, txtSearchByName, -765, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(txtSearchByName);
		
		JLabel lblSearch = new JLabel("Keres\u00E9s receptn\u00E9v alapj\u00E1n");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblSearch);
		
		JButton btnSaveRegister = new JButton("Nyilv\u00E1ntart\u00E1s ment\u00E9se");
		btnSaveRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManagement.writeFile(Main.recipes);
				FileManagement.writeIngredientsFile();
				JOptionPane.showMessageDialog(frame, "Sikeres mentés!");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSaveRegister, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSaveRegister, -707, SpringLayout.EAST, frame.getContentPane());
		btnSaveRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnSaveRegister);
		
		JButton btnLoadRegister = new JButton("Nyilv\u00E1ntart\u00E1s bet\u00F6lt\u00E9se f\u00E1jlb\u00F3l");
		btnLoadRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser chooser = new JFileChooser();
					if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
	                    fileName = chooser.getSelectedFile().getAbsolutePath();
	                    if(fileName!="") {
	                    	Main.recipes.clear();
	                    	FileManagement.readFile(fileName);
	                    	Menu.recipesdlm.clear();
	    					for(int i=0;i<Main.recipes.size();i++) {
	    						Menu.recipesdlm.addElement(Main.recipes.get(i).getName());
	    					}
	    					Menu.lstRecipes.setModel(Menu.recipesdlm);
	                    }
	                }
				}
				catch(Exception m) {
					new exceptions.FileStructureInadequateException(frame);
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, lblIngredients, 113, SpringLayout.EAST, btnLoadRegister);
		springLayout.putConstraint(SpringLayout.WEST, btnLoadRegister, 20, SpringLayout.WEST, frame.getContentPane());
		btnLoadRegister.setMinimumSize(new Dimension(163, 22));
		btnLoadRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnLoadRegister);
		
		JLabel lblNewRecipe = new JLabel("\u00DAj recept hozz\u00E1ad\u00E1sa a nyilv\u00E1ntart\u00E1shoz");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewRecipe, 148, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSaveRegister, -10, SpringLayout.NORTH, lblNewRecipe);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewRecipe, 6, SpringLayout.SOUTH, lblNewRecipe);
		springLayout.putConstraint(SpringLayout.WEST, lblNewRecipe, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewRecipe, 0, SpringLayout.EAST, btnLoadRegister);
		lblNewRecipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblNewRecipe);
		
		JLabel lblLoadRegister = new JLabel("Nyilv\u00E1ntart\u00E1s bet\u00F6lt\u00E9se kiv\u00E1lasztott f\u00E1jlb\u00F3l");
		springLayout.putConstraint(SpringLayout.WEST, lblLoadRegister, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblLoadRegister, -647, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblLoadRegister, -746, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnLoadRegister, 6, SpringLayout.SOUTH, lblLoadRegister);
		lblLoadRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblLoadRegister);
		
		JLabel lblSaveRegister = new JLabel("Jelenlegi nyilv\u00E1ntart\u00E1s ment\u00E9se");
		springLayout.putConstraint(SpringLayout.NORTH, btnSaveRegister, 6, SpringLayout.SOUTH, lblSaveRegister);
		springLayout.putConstraint(SpringLayout.WEST, lblSaveRegister, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSaveRegister, -779, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblSaveRegister, 77, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoadRegister, -6, SpringLayout.NORTH, lblSaveRegister);
		lblSaveRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblSaveRegister);
		
		JLabel lblListRecipes = new JLabel("\u00D6sszes recept megjelen\u00EDt\u00E9se");
		springLayout.putConstraint(SpringLayout.WEST, lblListRecipes, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblListRecipes, -815, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnListRecipes, 6, SpringLayout.SOUTH, lblListRecipes);
		springLayout.putConstraint(SpringLayout.NORTH, lblListRecipes, 14, SpringLayout.SOUTH, btnNewRecipe);
		lblListRecipes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblListRecipes);
		
		JLabel lblSearchyByIngredients = new JLabel("Receptek sz\u0171r\u00E9se alapanyagokk\u00E9nt");
		springLayout.putConstraint(SpringLayout.NORTH, lblSearchyByIngredients, 0, SpringLayout.NORTH, lblLoadRegister);
		springLayout.putConstraint(SpringLayout.WEST, lblSearchyByIngredients, -426, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSearchyByIngredients, -216, SpringLayout.EAST, frame.getContentPane());
		lblSearchyByIngredients.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblSearchyByIngredients);
		
		JLabel lblTextIngredients = new JLabel("Ebben a list\u00E1ban tal\u00E1lhat\u00F3 a receptekben lev\u0151 \u00F6sszes alapanyag");
		springLayout.putConstraint(SpringLayout.EAST, lblSearch, -24, SpringLayout.WEST, lblTextIngredients);
		springLayout.putConstraint(SpringLayout.NORTH, lblTextIngredients, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, lblTextIngredients, 299, SpringLayout.WEST, frame.getContentPane());
		lblTextIngredients.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblTextIngredients);
		
		JLabel lblLehetsgVanj = new JLabel("Lehet\u0151s\u00E9g van \u00FAj alapanyagok hozz\u00E1ad\u00E1s\u00E1hoz");
		springLayout.putConstraint(SpringLayout.NORTH, lblSearch, -6, SpringLayout.NORTH, lblLehetsgVanj);
		springLayout.putConstraint(SpringLayout.NORTH, lblLehetsgVanj, 2, SpringLayout.SOUTH, lblTextIngredients);
		springLayout.putConstraint(SpringLayout.WEST, lblLehetsgVanj, 299, SpringLayout.WEST, frame.getContentPane());
		lblLehetsgVanj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblLehetsgVanj);
		
		JButton btnAddNewIngredient = new JButton("\u00DAj alapanyag");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddNewIngredient, 0, SpringLayout.NORTH, btnDeleteSorted);
		springLayout.putConstraint(SpringLayout.EAST, btnAddNewIngredient, 0, SpringLayout.EAST, scrollPane);
		btnAddNewIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewIngredient.main(null);
			}
		});
		btnAddNewIngredient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnAddNewIngredient);
		
		JLabel lblVlasszonKiEgy = new JLabel("V\u00E1lasszon ki egy alapanyagot");
		springLayout.putConstraint(SpringLayout.NORTH, lblVlasszonKiEgy, 0, SpringLayout.NORTH, btnSaveRegister);
		springLayout.putConstraint(SpringLayout.WEST, lblVlasszonKiEgy, 70, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, lblVlasszonKiEgy, -69, SpringLayout.WEST, scrollPane2);
		lblVlasszonKiEgy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblVlasszonKiEgy);
		
		JLabel lblsAdjaHozz = new JLabel("\u00C9s adja hozz\u00E1 a sz\u0171r\u00E9shez");
		springLayout.putConstraint(SpringLayout.WEST, lblsAdjaHozz, 80, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblsAdjaHozz, -6, SpringLayout.NORTH, btnAddSorted);
		springLayout.putConstraint(SpringLayout.EAST, lblsAdjaHozz, -10, SpringLayout.EAST, lblVlasszonKiEgy);
		lblsAdjaHozz.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblsAdjaHozz);
		
		JLabel lblEbbenAListban = new JLabel("Ebben a list\u00E1ban tal\u00E1lhat\u00F3ak a jelenlegi sz\u0171r\u00E9sben");
		springLayout.putConstraint(SpringLayout.WEST, lblEbbenAListban, 711, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblTextIngredients, -40, SpringLayout.WEST, lblEbbenAListban);
		springLayout.putConstraint(SpringLayout.EAST, lblEbbenAListban, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteSorted, 0, SpringLayout.EAST, lblEbbenAListban);
		springLayout.putConstraint(SpringLayout.NORTH, lblEbbenAListban, 6, SpringLayout.SOUTH, scrollPane2);
		lblEbbenAListban.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblEbbenAListban);
		
		JLabel lblHasznltAlapanyagok = new JLabel("haszn\u00E1lt alapanyagok");
		springLayout.putConstraint(SpringLayout.NORTH, lblHasznltAlapanyagok, 2, SpringLayout.SOUTH, lblEbbenAListban);
		springLayout.putConstraint(SpringLayout.EAST, lblLehetsgVanj, -71, SpringLayout.WEST, lblHasznltAlapanyagok);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteSorted, 9, SpringLayout.SOUTH, lblHasznltAlapanyagok);
		springLayout.putConstraint(SpringLayout.WEST, lblHasznltAlapanyagok, 0, SpringLayout.WEST, lblEbbenAListban);
		springLayout.putConstraint(SpringLayout.EAST, lblHasznltAlapanyagok, -20, SpringLayout.EAST, lblEbbenAListban);
		lblHasznltAlapanyagok.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblHasznltAlapanyagok);
		
		JLabel lblReceptnvSzerintiKeresshez = new JLabel("Receptn\u00E9v szerinti keres\u00E9shez \u00EDrja be a receptnev\u00E9t");
		springLayout.putConstraint(SpringLayout.NORTH, lblReceptnvSzerintiKeresshez, 7, SpringLayout.SOUTH, lblLehetsgVanj);
		springLayout.putConstraint(SpringLayout.WEST, lblReceptnvSzerintiKeresshez, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblReceptnvSzerintiKeresshez, -60, SpringLayout.WEST, btnAddNewIngredient);
		lblReceptnvSzerintiKeresshez.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblReceptnvSzerintiKeresshez);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lblRecipesName, 4, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, lblRecipesName, 53, SpringLayout.EAST, scrollPane1);
		lblRecipesName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblRecipesName);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lblRecipeName, 6, SpringLayout.SOUTH, lblRecipesName);
		springLayout.putConstraint(SpringLayout.WEST, lblRecipeName, 19, SpringLayout.EAST, scrollPane1);
		lblRecipeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblRecipeName);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lblIngredientsList, -6, SpringLayout.NORTH, lblNewLabel);
		lblIngredientsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblIngredientsList);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lblDirectionsList, -6, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblDirectionsList, -101, SpringLayout.EAST, frame.getContentPane());
		lblDirectionsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblDirectionsList);
		
		
		springLayout.putConstraint(SpringLayout.WEST, lblIngredientsList, 0, SpringLayout.WEST, lstIngredientsList);
		springLayout.putConstraint(SpringLayout.WEST, lstIngredientsList, 62, SpringLayout.EAST, lblRecipesName);
		springLayout.putConstraint(SpringLayout.NORTH, lstIngredientsList, 2, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.SOUTH, lstIngredientsList, 201, SpringLayout.NORTH, scrollPane1);
		lstIngredientsList.setVisibleRowCount(2);
		lstIngredientsList.setLayoutOrientation(JList.VERTICAL);
		lstIngredientsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lstIngredientsList.setBackground(SystemColor.controlShadow);
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane3, 0, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane3, 0, SpringLayout.WEST, lblIngredients);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane3, 0, SpringLayout.SOUTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane3, -21, SpringLayout.EAST, lblLehetsgVanj);
		scrollPane3.setViewportView(lstIngredientsList);
		lstIngredientsList.setLayoutOrientation(JList.VERTICAL);
		frame.getContentPane().add(scrollPane3);
		
		
		springLayout.putConstraint(SpringLayout.EAST, lstIngredientsList, -36, SpringLayout.WEST, lstDircetionsList);
		springLayout.putConstraint(SpringLayout.NORTH, lstDircetionsList, 0, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, lstDircetionsList, -169, SpringLayout.WEST, btnKeress);
		springLayout.putConstraint(SpringLayout.SOUTH, lstDircetionsList, 0, SpringLayout.SOUTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, lstDircetionsList, 0, SpringLayout.EAST, btnDeleteSorted);
		lstDircetionsList.setVisibleRowCount(2);
		lstDircetionsList.setLayoutOrientation(JList.VERTICAL);
		lstDircetionsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lstDircetionsList.setBackground(SystemColor.controlShadow);
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane4, 0, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane4, -318, SpringLayout.EAST, scrollPane2);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane4, 0, SpringLayout.SOUTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane4, 0, SpringLayout.EAST, scrollPane2);
		scrollPane4.setViewportView(lstDircetionsList);
		lstDircetionsList.setLayoutOrientation(JList.VERTICAL);
		frame.getContentPane().add(scrollPane4);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, btnEditRecipe, 44, SpringLayout.SOUTH, lblRecipeName);
		springLayout.putConstraint(SpringLayout.WEST, btnEditRecipe, 6, SpringLayout.EAST, scrollPane1);
		btnEditRecipe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnEditRecipe);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteRecipe, 6, SpringLayout.SOUTH, btnEditRecipe);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteRecipe, 28, SpringLayout.EAST, scrollPane1);
		btnDeleteRecipe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnDeleteRecipe);
		
		
		
		
	}
}
