import java.util.Objects;
import java.util.Random;

public class TableFunctions {
    String[][] table;
    int bombCount;
    int boardX;
    int boardY;
    Random r = new Random();
    /**
     * @link TableFunctions
     * Build table to create positions of all the bombs
     * based on table width and height, what square is clicked first,
     * and the rate of bombs spawning
     * @param x How wide the table should be
     * @param y How long the table should be
     * @param bombRate The percent of bombs that should be added
     * @param clickX X cord of what tile was first pressed
     * @param clickY Y cord of what tile was first pressed
     */
    public TableFunctions(int x, int y, float bombRate, int clickX, int clickY) {
        table = new String[x][y];
        bombRate = bombRate/100;
        bombCount = (int) ((x*y)*bombRate);
        boardX = x;
        boardY = y;
        //fill table with 0s
        for (int xint = 0; xint < x; xint++){
            for(int yint = 0; yint < y; yint++){
                table[xint][yint] = "0";
            }
        }

        //region place no bomb spots
        table[clickY][clickX] = "x";
        if (clickX != 0) {
            table[clickY][clickX - 1] = "x";
        }
        if (clickY != x-1 && clickX != 0) {
            table[clickY + 1][clickX - 1] = "x";
        }
        if (clickY != x-1) {
            table[clickY + 1][clickX] = "x";
        }
        if (clickY != x-1 && clickX != y-1) {
            table[clickY + 1][clickX + 1] = "x";
        }
        if (clickX != y-1) {
            table[clickY][clickX + 1] = "x";
        }
        if (clickY != 0 && clickX != y-1) {
            table[clickY - 1][clickX + 1] = "x";
        }
        if (clickY != 0) {
            table[clickY - 1][clickX] = "x";
        }
        if (clickY != 0 && clickX != 0){
            table[clickY - 1][clickX-1] = "x";
        }
        //endregion

//place bomb spots
        int bombCounter = 0;
        while (bombCounter < bombCount){
        int randomX = r.nextInt(x);
        int randomY = r.nextInt(y);

        if(Objects.equals(table[randomX][randomY], "0")){
            table[randomX][randomY] = "b";
            bombCounter = bombCounter + 1;
        }
        }
    }

    /**
     * @link debug
     * show cheat sheet of where every bomb is and where
     * the player first clicked + surrounding tiles
     */
    public void debug(){
        for (int xint = 0; xint < boardX; xint++){
            for(int yint = 0; yint < boardY; yint++){
               String value = table[xint][yint];
               System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println(bombCount);
    }
}
