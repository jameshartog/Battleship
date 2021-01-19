/**
 * Battleship with only 1 ship in a 5 x 5 grid.
 *
 * @author (JJH)
 * @version (1.0)
 */
import java.util.Scanner;

public class BattleshipMachine
{
    //Hidden board has most column and row terms swapped
    //Variables
    public static Scanner userScan = new Scanner(System.in);
    public static String[][] board = new String [5][5];
    public static String[][] hiddenBoard = new String [5][5];
    public static boolean repeat = true;
    static void clear() {
        System.out.print('\u000C');
    }
    
    static void rules() {
        String rules = "yes";
        while (rules.equals("yes")) {
            System.out.println("Battleship 1.0");
            System.out.println("By JJ Hartog");
            System.out.println("-----------------------------------------------");            System.out.println("Rules:");
            System.out.println("The 5-length enemy Battleship is within a 5 x 5 grid");
            System.out.println("The Battleship is either horizontal or verticle");
            System.out.println("-----------------------------------------------");
            System.out.println("Key:");
            System.out.println("[     ] = Unknown");
            System.out.println("[ Hit ] = Hit the Battleship");
            System.out.println("[Empty] = Missed the Battleship");
            System.out.println("-----------------------------------------------");
            System.out.println("WARNING: Fire Selection is very sensitive to incorrect inputs");
            System.out.println("To continue input (start)");
            rules = userScan.next();
        }
    }
    
    static String[][] createBoard() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                board[r][c] = "     ";
                //System.out.print("[" + board[r][c] + "]");
            }
            //System.out.println();
        }
        
        return board;
    }
    
    static String[][] createHidden() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                hiddenBoard[r][c] = " ";
                //System.out.print("[" + hiddenBoard[r][c] + "]");
            }
            //System.out.println();
        }
        
        return hiddenBoard;
    }
    
    static void printBoard() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print("[" + board[r][c] + "]");
            }
            System.out.println();
        }
        
    }
    
    static void printHidden() {
        clear();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print("[" + hiddenBoard[r][c] + "]");
            }
            System.out.println();
        }
        
    }
    
    public static int row;
    public static int column;
    public static int direction;
    static void spawnShip() {
        createHidden();
        int min = 0;
        int max = 4;
        //Row & Column
        row = (int)(Math.random() * (max - min + 1) + min);
        column = (int)(Math.random() * (max - min + 1) + min);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Inputs position
        if (direction == 0) {
            for (int r = 0; r < 5; r++) {
                hiddenBoard[column][r] = "X";
            }
        }
        else if (direction == 1) {
            for (int c = 0; c < 5; c++) {
                hiddenBoard[c][row] = "X";
            }
        }
    }
    
    public static int i;
    static void firer() {
        System.out.println("-----------Fire Control------------");
        System.out.print("x-coordinate fire position: ");
        int x = userScan.nextInt();
        System.out.print("y-coordinate fire position: ");
        int y = userScan.nextInt();
        clear();
        if (hiddenBoard[y-1][x-1].equals("X")) {
            System.out.println("Last Shot: Hit!");
            hiddenBoard[y-1][x-1] = "F";
            board[y-1][x-1] = " Hit ";
        }
        else if (hiddenBoard[y-1][x-1].equals(" ")) {
            System.out.println("Last Shot: Miss!");
            board[y-1][x-1] = "Empty";
        }
        else {
            System.out.println("Error");
            System.out.println("Please Restart the Program");
            int temp = userScan.nextInt();
        }
        i++;
        printBoard();   
        
    }
    
    static void fireControl() {
        printBoard();
        do {
            firer();
        }
        //essentially any X exists
        while (hiddenBoard[column][0].equals("X") || hiddenBoard[column][1].equals("X")
        || hiddenBoard[column][2].equals("X") || hiddenBoard[column][3].equals("X") 
        || hiddenBoard[column][4].equals("X") || hiddenBoard[0][row].equals("X") 
        || hiddenBoard[1][row].equals("X") || hiddenBoard[2][row].equals("X") 
        || hiddenBoard[3][row].equals("X") || hiddenBoard[4][row].equals("X"));
        clear();
        System.out.println("You have sunken the enemy Battleship in " + i + " turns");
        System.out.println("GAME OVER");
    }
    
    public static void main(String[] args) {
        
        do {  
            i = 0;
            rules();
            createBoard();
            spawnShip();
            
            fireControl();
            //repeater
            System.out.print("Would you like to play again? (true/false): ");
            repeat = userScan.nextBoolean();
        } while (repeat);
    }
}