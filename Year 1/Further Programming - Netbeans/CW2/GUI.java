/**
 * GUI.java
 * The class that contains the methods to create the GUI that interacts with 
 * Futoshiki to allow the average user to play the game.
 * @author Alex Reddington : 134291
 * @date May 2016
*/

// Package and libray imports
package uk.ac.sussex.ianw.fp.futoshiki;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class GUI extends javax.swing.JFrame {
    
//Declaration of arrays containing the constraints and numbers
    Futoshiki puzzle;
    JTextField[][] numbers;
    JLabel[][] rowConstraints;
    JLabel[][] columnConstraints;
    
    // Constructor, initalizing arrays and calling inital methods to generate and print GUI

    /**
     *
     * @param puzzle
     */
    public GUI(Futoshiki puzzle) {
        this.puzzle = puzzle; 
        numbers = new JTextField[5][5];
        rowConstraints = new JLabel[5][4];
        columnConstraints = new JLabel[5][4];
        initComponents();
        generateGrid();
        printGrid();
    }
    /**
     * generateGrid Method
     * Used to pull the numbers and constraints and store in local arrays 
     * that are connected to the GUI.
     */

    private void generateGrid() {
        for (int x = 0; x < 5; x++) { // Iterates through whole of x
            for (int y = 0; y < 5; y++) { // Iterates through whole of y
                JTextField box = new JTextField();// Produces JTextField object
                box.setText(puzzle.getSquare(x, y).getSymbol()); //Sets value of text to slot of game array
                if (!" ".equals(puzzle.getSquare(x, y).getSymbol())) { //Used to set non editible field for 
                    box.setEditable(false); // initally populated fields
                    box.setForeground(Color.green);
                } else {
                    box.setForeground(Color.white);
                }
                box.setHorizontalAlignment(JTextField.CENTER); // Centers text
                box.setFont(new Font("", Font.PLAIN, 45)); // Text formatting
                box.setBackground(Color.black); // Sets Background
                final int x1 = x; // Final X Cordidante
                final int y1 = y; // Final Y Cordidante
                box.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent action) { // Action Listener for JTextField
                        int number; 
                        String value = box.getText(); // Pulls value in box entered by user
                        if (" ".equals(value)) {
                            number = 0; //Checks for blank
                        } else {
                            number = Integer.parseInt(value.trim()); // switches string to integer
                        }
                        final int value1 = number; // sets final integer value
                        puzzle.setSquare(x1, y1, value1); // stores value in Futoshiki array.
                    }
                });
                numbers[x][y] = box; // stores into local array
            }
        }

        for (int x = 0; x < 5; x++) { // Iterates though whole of x
            for (int y = 0; y < 4; y++) { // Iterates though whole of x
                JLabel row = new JLabel(); // New Jlabel object
                row.setText(puzzle.getRowConstraint(x, y).getSymbol()); // Pulls constraint from game and sets value
                row.setHorizontalAlignment(JLabel.CENTER); // Center formatting
                row.setFont(new Font("", Font.PLAIN, 30)); // font formatting
                rowConstraints[x][y] = row; // stores in local array
            }
        }

        for (int x = 0; x < 5; x++) {// itterates though x
            for (int y = 0; y < 4; y++) { //itterates though y
                JLabel column = new JLabel(); // new jlabel object
                column.setText(puzzle.getColumnConstraint(x, y).getSymbol()); // pulls constraint from game and sets value
                column.setHorizontalAlignment(JLabel.CENTER); // center formatting
                column.setFont(new Font("", Font.PLAIN, 30)); // font formatting
                columnConstraints[x][y] = column; // stores in local array
            }
        }
    }
    
    /**
     * printGrid Method
     * Used to store JLabel and JTextField's to grid in 
     * appropriate format.
     */

    private void printGrid() {
        int y = 0; // inital y value
        for (int a = 0; a < 4; a++) { // runs the print 4 times
            for (int i = 0; i < 4; i++) { // interates x position each time
                game_area.add(numbers[y][i]); // stores number 
                game_area.add(rowConstraints[y][i]); // stores constraint
            }
            game_area.add(numbers[y][4]); // stores end of row number
            for (int i = 0; i < 4; i++) { // iterates x position each time
                game_area.add(columnConstraints[i][y]); //stores constraint
                game_area.add(new JLabel("")); // prints blank
            }
            game_area.add(columnConstraints[4][y]);// print end of row constraint
            y++; // increments y
        }
        for (int a = 0; a < 4; a++) { // prints final row, iterates 4 times
            game_area.add(numbers[4][a]); // prints number
            game_area.add(rowConstraints[4][a]); // prints constraint
        }
        game_area.add(numbers[4][4]);// prints final number at end of grid (5,5)
    }
    
    /**
     * saveGame Method
     * Used to convert Futoshiki object into byte code for Storage.
     */

    private void saveGame() {
        ObjectOutputStream out = null; // Initalizes inital output stream
        try {
            out = new ObjectOutputStream(new FileOutputStream("Futoshiki_Save.ser")); // creates file with appropriate name
            out.writeObject(puzzle); // wrties puzzle to file
            out.close(); // closes file 
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex); // catches errors
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex); // catches erros
            }
        }

    }
    
    /**
     * saveGame Method
     * Used to convert saved object in byte code back into object and run game from this object.
     */
    private void loadGame() {
        FileInputStream input = null; // input stream is set to null 
        try {
            input = new FileInputStream("Futoshiki_Save.ser"); // sets source file 
            try {
                ObjectInputStream output = new ObjectInputStream(input); // Sets puzzle to input stream declared as input.
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);// Catches errors
            }
            puzzle = new Futoshiki(); // creates new Futoshiki Object called object;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);// Catches errors
        } finally {
            try {
                input.close();// closes file import
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);// catches errors
            }
        }
    }

    @SuppressWarnings("unchecked")
   
   /**
    * initComponents
    * Method used to construct GUI
    */
    private void initComponents() {
        //Initalization of GUI components
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        button_panel = new javax.swing.JPanel();
        solve = new javax.swing.JButton();
        load_game = new javax.swing.JButton();
        save_game = new javax.swing.JButton();
        legality_check = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        button_spacer_1 = new javax.swing.JPanel();
        button_spacer_2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        title_box = new javax.swing.JLabel();
        title_controls = new javax.swing.JLabel();
        title_game = new javax.swing.JLabel();
        button_spacer_3 = new javax.swing.JPanel();
        button_spacer_4 = new javax.swing.JPanel();
        game_area = new javax.swing.JPanel();
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jEditorPane1);
        //Inital box for game creation, associated settings for formatting
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Futoshiki Puzzle");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        button_panel.setBackground(new java.awt.Color(0, 0, 0));
        button_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        // Solve button parameters
        solve.setBackground(new java.awt.Color(102, 102, 102));
        solve.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        solve.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        solve.setText("Solve");
        solve.setToolTipText("");
        solve.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        solve.setMaximumSize(new java.awt.Dimension(80, 25));
        solve.setMinimumSize(new java.awt.Dimension(80, 25));
        solve.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) { //Action listener for solve button
                String result = ("" + puzzle.solve()); // calls solve method
                JOptionPane.showMessageDialog(null, result, "Solve Result", JOptionPane.ERROR_MESSAGE); // prints solve boolean result in option pane
                game_area.removeAll();// clears JPanel
                generateGrid();// regenrates grid
                printGrid();// prints grid
                game_area.updateUI();// updates JPanel
            }
        });
        //Load game button parameters
        load_game.setBackground(new java.awt.Color(102, 102, 102));
        load_game.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        load_game.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        load_game.setText("Load Game");
        load_game.setToolTipText("");
        load_game.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        load_game.setMaximumSize(new java.awt.Dimension(80, 25));
        load_game.setMinimumSize(new java.awt.Dimension(80, 25));
        load_game.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {// Action listener for load game
                loadGame(); // calls load game
                game_area.removeAll();//clears jpanel
                generateGrid();// generates grid
                printGrid();// prints grid
                game_area.updateUI();// updates jpanel
            }
        });
        //Save game button parameters
        save_game.setBackground(new java.awt.Color(102, 102, 102));
        save_game.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        save_game.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        save_game.setText("Save Game");
        save_game.setToolTipText("");
        save_game.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        save_game.setMaximumSize(new java.awt.Dimension(80, 25));
        save_game.setMinimumSize(new java.awt.Dimension(80, 25));
        save_game.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Save Game Action Listener
                saveGame(); // Calls Save Game method
            }
        });
        //Legality Check Button Parameters
        legality_check.setBackground(new java.awt.Color(102, 102, 102));
        legality_check.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        legality_check.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        legality_check.setText("Legality Check");
        legality_check.setToolTipText("");
        legality_check.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        legality_check.setMaximumSize(new java.awt.Dimension(80, 25));
        legality_check.setMinimumSize(new java.awt.Dimension(80, 25));
        legality_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Action Listener for Legality
                String result;// declares string used for result 
                result = ("" + puzzle.isLegal() + "\n" + puzzle.getProblems()); //too string for message to print
                JOptionPane.showMessageDialog(null, result, "Legality Result", JOptionPane.ERROR_MESSAGE); // creation of JoptionPane to display message
            }
        });
        //Reset Button Parameters
        reset.setBackground(new java.awt.Color(102, 102, 102));
        reset.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reset.setForeground(javax.swing.UIManager.getDefaults().getColor("textHighlight"));
        reset.setText("Reset");
        reset.setToolTipText("");
        reset.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        reset.setMaximumSize(new java.awt.Dimension(80, 25));
        reset.setMinimumSize(new java.awt.Dimension(80, 25));
        reset.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) { // Action listener for Reset
                puzzle = new Futoshiki();// Creates new Futoshiki object
                puzzle.fillPuzzle(); // fills puzzle in Futoshiki
                game_area.removeAll();// removes all from jpanel
                generateGrid();// generates grid
                printGrid();// prints grid
                game_area.updateUI();// updates jpanel
            }
        });
        // More formatting
        button_spacer_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout button_spacer_1Layout = new javax.swing.GroupLayout(button_spacer_1);
        button_spacer_1.setLayout(button_spacer_1Layout);
        button_spacer_1Layout.setHorizontalGroup(
                button_spacer_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        button_spacer_1Layout.setVerticalGroup(
                button_spacer_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 65, Short.MAX_VALUE)
        );

        button_spacer_2.setBackground(new java.awt.Color(255, 255, 255));
        button_spacer_2.setPreferredSize(new java.awt.Dimension(0, 65));

        javax.swing.GroupLayout button_spacer_2Layout = new javax.swing.GroupLayout(button_spacer_2);
        button_spacer_2.setLayout(button_spacer_2Layout);
        button_spacer_2Layout.setHorizontalGroup(
                button_spacer_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        button_spacer_2Layout.setVerticalGroup(
                button_spacer_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 65, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout button_panelLayout = new javax.swing.GroupLayout(button_panel);
        button_panel.setLayout(button_panelLayout);
        button_panelLayout.setHorizontalGroup(
                button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(button_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(solve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(legality_check, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                .addComponent(save_game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(load_game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_spacer_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_spacer_2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addContainerGap())
        );
        button_panelLayout.setVerticalGroup(
                button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button_spacer_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(load_game, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(save_game, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(legality_check, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(solve, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button_spacer_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
        );

        title_box.setBackground(new java.awt.Color(0, 0, 0));
        title_box.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        title_box.setForeground(new java.awt.Color(255, 255, 255));
        title_box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_box.setText("Futoshiki Puzzle");
        title_box.setToolTipText("");
        title_box.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        title_box.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(title_box, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 86, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(title_box, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        title_controls.setBackground(new java.awt.Color(255, 255, 255));
        title_controls.setFont(new java.awt.Font("Tahoma", 0, 44)); // NOI18N
        title_controls.setForeground(new java.awt.Color(0, 0, 0));
        title_controls.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_controls.setText("Controls");
        title_controls.setOpaque(true);

        title_game.setBackground(new java.awt.Color(255, 255, 255));
        title_game.setFont(new java.awt.Font("Tahoma", 0, 44)); // NOI18N
        title_game.setForeground(new java.awt.Color(0, 0, 0));
        title_game.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_game.setText("Game ");
        title_game.setOpaque(true);

        button_spacer_3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout button_spacer_3Layout = new javax.swing.GroupLayout(button_spacer_3);
        button_spacer_3.setLayout(button_spacer_3Layout);
        button_spacer_3Layout.setHorizontalGroup(
                button_spacer_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 89, Short.MAX_VALUE)
        );
        button_spacer_3Layout.setVerticalGroup(
                button_spacer_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        button_spacer_4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout button_spacer_4Layout = new javax.swing.GroupLayout(button_spacer_4);
        button_spacer_4.setLayout(button_spacer_4Layout);
        button_spacer_4Layout.setHorizontalGroup(
                button_spacer_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 77, Short.MAX_VALUE)
        );
        button_spacer_4Layout.setVerticalGroup(
                button_spacer_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        game_area.setBackground(new java.awt.Color(255, 255, 255));
        game_area.setPreferredSize(new Dimension(620, 550));

        javax.swing.GroupLayout game_areaLayout = new javax.swing.GroupLayout(game_area);
        game_area.setLayout(new GridLayout(9, 9));
        game_areaLayout.setHorizontalGroup(
                game_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 640, Short.MAX_VALUE)
        );
        game_areaLayout.setVerticalGroup(
                game_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(title_controls, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(title_game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(button_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(button_spacer_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(game_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button_spacer_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(title_game)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(title_controls, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(button_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(button_spacer_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button_spacer_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(game_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())))
        );

        pack();
    }                   

    /**
     * Main Method
     * Called when program is run to create instance of GUI and all code
     * associated with it
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Futoshiki game = new Futoshiki();
        game.fillPuzzle();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI(game).setVisible(true);
            }
        });
    }

    // Variables declarations used within                    
    private javax.swing.JPanel button_panel;
    private javax.swing.JPanel button_spacer_1;
    private javax.swing.JPanel button_spacer_2;
    private javax.swing.JPanel button_spacer_3;
    private javax.swing.JPanel button_spacer_4;
    private javax.swing.JPanel game_area;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton legality_check;
    private javax.swing.JButton load_game;
    private javax.swing.JButton reset;
    private javax.swing.JButton save_game;
    private javax.swing.JButton solve;
    private javax.swing.JLabel title_box;
    private javax.swing.JLabel title_controls;
    private javax.swing.JLabel title_game;                  
}
