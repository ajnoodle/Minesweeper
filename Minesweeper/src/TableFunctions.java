public class TableFunctions {
    String[][] table;
    int bombCount;
    int boardX;
    int boardY;
    public TableFunctions(int x, int y, float bombRate, int xClick, int yClick) {
        table = new String[x][y];
        bombCount = (int) ((x*y)/bombRate);
        boardX = x;
        boardY = y;
        //fill table with 0s
        for (int xint = 0; xint < x; xint++){
            for(int yint = 0; yint < y; yint++){
                table[xint][yint] = "0";
            }
        }

        //place no bomb spots
        table[xClick][yClick] = "x";
        if (yClick != 0) {
            table[xClick][yClick - 1] = "x";
        }
        if (xClick != x-1 && yClick != 0) {
            table[xClick + 1][yClick - 1] = "x";
        }
        if (xClick != x-1) {
            table[xClick + 1][yClick] = "x";
        }
        if (xClick != x-1 && yClick != y-1) {
            table[xClick + 1][yClick + 1] = "x";
        }
        if (yClick != y-1) {
            table[xClick][yClick + 1] = "x";
        }
        if (xClick != 0 && yClick != y-1) {
            table[xClick - 1][yClick + 1] = "x";
        }
        if (xClick != 0) {
            table[xClick - 1][yClick] = "x";
        }
        if (xClick != 0 && yClick != 0){
            table[xClick - 1][yClick-1] = "x";
        }

    }

    public void debug(){
        for (int xint = 0; xint < boardX; xint++){
            for(int yint = 0; yint < boardY; yint++){
               String value = table[xint][yint];
               System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
