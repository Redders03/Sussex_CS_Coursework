import java.util.Random;

/**
 *
 * @author Alex Reddington
 * @author Date : March - April 2016
 * @version 1.0
 */
public class FutoshikiPuzzle {

    /**
     */
    public FutoshikiSquare[][] grid;
    String[][] rowConstraints;
    String[][] columnConstraints;

    /**
     * Instantiates a new futoshiki puzzle.
     *
     * @param size the size of the grid 
     */
    public FutoshikiPuzzle(int size) {
        grid = new FutoshikiSquare[size][size]; //first slot = row
        for(int i = 0; i < size; i++){
            for(int z = 0; z < size; z++){
                grid[i][z] = new FutoshikiSquare();
            }
        }
        rowConstraints = new String[size][size - 1];
        columnConstraints = new String[size - 1][size];
    }
    
    /**
     * Sets the square.
     *
     * @param rowID the row slot id
     * @param columnID the column slot id
     * @param setInt the actual number to set
     */
    public void setSquare(int rowID, int columnID, int setInt) {
        grid[rowID][columnID].setI(setInt);
    }

    /**
     * Sets the row constraint.
     *
     * @param rowID the row slot id
     * @param columnID the column slot id
     * @param rowConstraint the actual row constraint
     */
    public void setRowConstraint(int rowID, int columnID, String rowConstraint) {
        rowConstraints[rowID][columnID] = rowConstraint;
    }

    /**
     * Sets the column constraint.
     *
     * @param rowID the row slot id
     * @param columnID the column slot id
     * @param columnConstraint the actual column constraint
     */
    public void setColumnConstraint(int rowID, int columnID, String columnConstraint) {
        columnConstraints[rowID][columnID] = columnConstraint;
    }

    /**
     * Implement square.
     */
    private void implementSquare() {
        int x, y;
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            x = random.nextInt(grid.length);
            y = random.nextInt(grid.length);
            setSquare(x, y, random.nextInt(grid.length));
        }
    }

    /**
     * Implement row constraint.
     */
    private void implementRowConstraint() {
        int x, y, z;
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            x = random.nextInt(grid.length);
            y = random.nextInt(grid.length - 1);
            z = random.nextInt(2 - 1 + 1) + 1;
            switch (z) {
                case 1:
                    setRowConstraint(x, y, "<");
                    break;
                case 2:
                    setRowConstraint(x, y, ">");
                    break;
            }
        }
    }

    /**
     * Implement column constraint.
     */
    private void implementColumnConstraint() {
        int x, y, z;
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            x = random.nextInt(grid.length - 1);
            y = random.nextInt(grid.length);
            z = random.nextInt(2 - 1 + 1) + 1;
            switch (z) {
                case 1:
                    setColumnConstraint(x, y, "^");
                    break;
                case 2:
                    setColumnConstraint(x, y, "v");
                    break;
            }
        }
    }

    /**
     * Fill puzzle.
     */
    public void fillPuzzle() {
        implementSquare();
        implementRowConstraint();
        implementColumnConstraint();
    }

    /**
     *Produces string to be printed
     */
    @Override
    public String toString() {
        String output = "";
        for (int a = 0; a < grid.length; a++) {
            for (int z = 0; z < grid.length; z++) {
                output += ("---   ");
            }
            output += ("\n");

            for (int i = 0; i < grid.length; i++) {
                if (grid[a][i].getI() != 0) {
                    output += ("|" + grid[a][i].getI() + "|" + " ");

                } else {
                    output += ("|" + " " + "|" + " ");
                }
                if (i < grid.length - 1 && rowConstraints[a][i] != null) {
                    output += (rowConstraints[a][i] + " ");
                } else {
                    output += ("  ");
                }
            }
            output += ("\n");

            for (int z = 0; z < grid.length; z++) {
                output += ("---   ");
            }
            output += ("\n");
            for (int i = 0; i < grid.length; i++) {
                if (a < grid.length - 1 && columnConstraints[a][i] != null) {
                    output += (" " + columnConstraints[a][i] + " ");
                    output += ("   ");
                } else {
                    output += ("      ");
                }
            }
            output += ("\n");
        }
        return (output);
    }

    /**
     * Check row.
     *
     * @param iter the iter
     * @return the string
     */
    private String checkRow(int iter) {
        String errors = "";
        int check;
        for (int i = 0; i < grid.length - 1; i++) {
            check = grid[iter][i].getI();
            for (int z = i + 1; z < grid.length; z++) {
                if (check != 0 || grid[iter][z].getI() != 0) {
                    if (check == grid[iter][z].getI()) {
                        errors += ("Duplication on row between " + iter + i + " and " + iter + z + "\n");
                    }
                }
            }
        }
        return errors;
    }

    /**
     * Check column.
     *
     * @param iter the iter
     * @return the string
     */
    private String checkColumn(int iter) {
        String errors = "";
        int check;
        for (int i = 0; i < grid.length - 1; i++) {
            check = grid[i][iter].getI();
            for (int z = i + 1; z < grid.length; z++) {
                if (check != 0 || grid[z][iter].getI() != 0) {
                    if (check == grid[z][iter].getI()) {
                        errors += ("Duplication on column between " + i + iter + " and " + z + iter + "\n");
                    }
                }
            }
        }
        return errors;
    }

    /**
     * Check row constraints.
     *
     * @param iter the iter
     * @return the string
     */
    private String checkRowConstraints(int iter) {
        String constraint;
        String errors = "";
        for (int i = 0; i < grid.length - 1; i++) {
            if (rowConstraints[iter][i] != null) {
                constraint = rowConstraints[iter][i];
                if (grid[iter][i].getI() != 0 && grid[iter][i + 1].getI() != 0) {
                    if ("<".equals(constraint)) {
                        if (!(grid[iter][i].getI() < grid[iter][i + 1].getI())) {
                            errors += ("Row Constraint error between cordinates " + iter + i + " and " + iter + (i + 1) + "\n");
                        }
                    } else if (">".equals(constraint)) {
                        if (!(grid[iter][i].getI() > grid[iter][i + 1].getI())) {
                            errors += ("Row Constraint error between cordinates " + iter + i + " and " + iter + (i + 1) + "\n");
                        }
                    }
                }
            }
        }
        return errors;
    }

    /**
     * Check column constraints.
     *
     * @param iter the iter
     * @return the string
     */
    private String checkColumnConstraints(int iter) {
        String constraint;
        String errors = "";
        for (int i = 0; i < grid.length - 1; i++) {
            if (columnConstraints[i][iter] != null) {
                constraint = columnConstraints[i][iter];
                if (grid[i][iter].getI() != 0 && grid[i + 1][iter].getI() != 0) {
                    if ("^".equals(constraint)) {
                        if (!(grid[i][iter].getI() < grid[i + 1][iter].getI())) {
                            errors += ("Column Constraint error between cordinates  A " + i + iter + constraint + (i + 1) + iter + "\n");
                        }
                    } else if ("v".equals(constraint)) {
                        if (!(grid[i][iter].getI() > grid[i + 1][iter].getI())) {
                            errors += ("Column Constraint error between cordinates  B " + i + iter + constraint + (i + 1) + iter + "\n");
                        }
                    }
                }
            }
        }
        return errors;
    }

    ;
        
        /**
         * Checks if the puzzle is legal.
         *
         * @return true, if is legal
         */
    public boolean isLegal() {
        boolean legal = true;
        String errors = "";
        for (int i = 0; i < grid.length; i++) {
            if (!checkRow(i).equals("")) {
                errors += checkRow(i);
                legal = false;
            }
            if (!checkColumn(i).equals("")) {
                errors += checkColumn(i);
                legal = false;
            }
            if (!checkRowConstraints(i).equals("")) {
                errors += checkRowConstraints(i);
                legal = false;
            }
            if (!checkColumnConstraints(i).equals("")) {
                errors += checkColumnConstraints(i);
                legal = false;
            }
        }
        System.out.println(getProblems(errors));
        return legal;
    }

    /**
     *
     * @param errors
     * @return
     */
    public String getProblems(String errors) {
        return errors;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FutoshikiPuzzle instance = new FutoshikiPuzzle(5);
        instance.fillPuzzle();
        System.out.println(instance);
        System.out.println("Legality: " + instance.isLegal());
    }
    
    // The following methods below are used for test methods
    // located within the FutoshikiPuzzleTest.java file.

    /**
     *
     * @param x
     * @param y
     * @return
     */
    
    public int getSquare(int x,int y){
        return grid[x][y].getI();
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public String getRowConstraint(int x,int y){
        return rowConstraints[x][y];
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public String getColumnConstraint(int x, int y){
        return columnConstraints[x][y];
    }

}
