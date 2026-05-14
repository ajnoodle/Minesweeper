import java.util.Objects;
import java.util.Random;

public class TableFunctions {
    String[][] table;
    int bombCount;
    int boardX;
    int boardY;
    Random r = new Random();

    /**
     * @param x        How wide the table should be
     * @param y        How long the table should be
     * @param bombRate The percent of bombs that should be added
     * @param clickX   X cord of what tile was first pressed
     * @param clickY   Y cord of what tile was first pressed
     * @link TableFunctions
     * Build table to create positions of all the bombs
     * based on table width and height, what square is clicked first,
     * and the rate of bombs spawning
     */
    public TableFunctions(int x, int y, float bombRate, int clickX, int clickY) {
        table = new String[x][y];
        bombRate = bombRate / 100;
        bombCount = (int) ((x * y) * bombRate);
        boardX = x;
        boardY = y;
        //fill table with 0s
        for (int xint = 0; xint < x; xint++) {
            for (int yint = 0; yint < y; yint++) {
                table[xint][yint] = "0";
            }
        }

        //region place no bomb spots
        table[clickY][clickX] = "x";
        if (clickX != 0) {
            table[clickY][clickX - 1] = "x";
        }
        if (clickY != x - 1 && clickX != 0) {
            table[clickY + 1][clickX - 1] = "x";
        }
        if (clickY != x - 1) {
            table[clickY + 1][clickX] = "x";
        }
        if (clickY != x - 1 && clickX != y - 1) {
            table[clickY + 1][clickX + 1] = "x";
        }
        if (clickX != y - 1) {
            table[clickY][clickX + 1] = "x";
        }
        if (clickY != 0 && clickX != y - 1) {
            table[clickY - 1][clickX + 1] = "x";
        }
        if (clickY != 0) {
            table[clickY - 1][clickX] = "x";
        }
        if (clickY != 0 && clickX != 0) {
            table[clickY - 1][clickX - 1] = "x";
        }
        //endregion

//place bomb spots
        int bombCounter = 0;
        while (bombCounter < bombCount) {
            int randomX = r.nextInt(x);
            int randomY = r.nextInt(y);

            if (Objects.equals(table[randomX][randomY], "0")) {
                table[randomX][randomY] = "b";
                bombCounter = bombCounter + 1;
            }
        }

        //add numbers
        for (int xint = 0; xint < x; xint++) {
            for (int yint = 0; yint < y; yint++) {
                int bombNumber = 0;

                if (!table[yint][xint].equals("b")) {
                    if (xint != 0) {
                        if (table[yint][xint - 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != x - 1 && xint != 0) {
                        if (table[yint + 1][xint - 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != x - 1) {
                        if (table[yint + 1][xint].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != x - 1 && xint != y - 1) {
                        if (table[yint + 1][xint + 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (xint != y - 1) {
                        if (table[yint][xint + 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != 0 && xint != y - 1) {
                        if (table[yint - 1][xint + 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != 0) {
                        if (table[yint - 1][xint].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }
                    if (yint != 0 && xint != 0) {
                        if (table[yint - 1][xint - 1].equals("b")) {
                            bombNumber = bombNumber + 1;
                        }
                    }

                    if (bombNumber > 0) {
                        table[yint][xint] = Integer.toString(bombNumber);
                    }
                }
            }
        }
    }

    public void clearTable(int clickX, int clickY) {

    }


    /**
     * @link debug
     * show cheat sheet of where every bomb is and where
     * the player first clicked + surrounding tiles
     */
    public void debug() {
        for (int xint = 0; xint < boardX; xint++) {
            for (int yint = 0; yint < boardY; yint++) {
                String value = table[xint][yint];
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println(bombCount);
    }

    public boolean checkBomb(int x, int y) {
        if (table[y][x].equals("b")) {
            return true;
        } else {
            return false;
        }
    }

    public void flagTile(int y, int x) {
        table[y][x] = "F" + table[y][x];
    }

    public void deflagTile(int y, int x) {
        table[y][x] = table[y][x].replaceFirst("F", "");
    }

    public boolean checkForFlag(int y, int x) {
        if (table[y][x].startsWith("F")) {
            return true;
        } else {
            return false;
        }
    }

    public void revealNeighbors(int x, int y) {
        //check if numbers are on the table
        if (x >= 0 && x < table.length) {
            if (y >= 0 && y < table.length) {

                revealTiles(y, x);
            }
        }
    }

    public void revealTiles(int y, int x) {
        //if tile blank, clear tile and nearby tiles
        if (table[y][x].endsWith("C")) {
            return;
        }
        if (table[y][x].startsWith("x") || table[y][x].startsWith("0")) {
            table[y][x] = table[y][x] + "C";
            revealNeighbors(x, y - 1);
            revealNeighbors(x + 1, y - 1);
            revealNeighbors(x + 1, y);
            revealNeighbors(x + 1, y + 1);
            revealNeighbors(x, y + 1);
            revealNeighbors(x - 1, y + 1);
            revealNeighbors(x - 1, y);
            revealNeighbors(x - 1, y - 1);
            //if tile is not blank, check for flagging and bombs
            //ignore if flagged or bomb
        } else if (!table[y][x].startsWith("F") || !table[y][x].equals("b")) {
            //check for number tile
            for (int v = 1; v < 8; v++) {
                String vS = Integer.toString(v);
                if (table[y][x].equals(vS)) {
                    table[y][x] = table[y][x] + "C";
                }
            }
        }
    }

    public boolean checkForWin(int y, int x){
        for (int xint = 0; xint < x; xint++) {
            for (int yint = 0; yint < y; yint++) {
                for (int num = 0; num < 8; num++) {
                    String number = Integer.toString(num);
                    if (table[yint][xint].equals(number) || table[yint][xint].equals("x") || table[yint][xint].equals("F" + number) || table[yint][xint].equals("F" + "x")) {
                            return false;

                    }
                }
            }
        }
        return true;
    }


    enum returnedTile {
        KEEP_SAME,
        CLEAR,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT

    }

    public returnedTile checkForClear(int y, int x) {
        if (table[y][x].equals("0C") || table[y][x].equals("xC")) {
            return returnedTile.CLEAR;
        } else if (table[y][x].equals("1C")) {
            return returnedTile.ONE;
        } else if (table[y][x].equals("2C")) {
            return returnedTile.TWO;
        } else if (table[y][x].equals("3C")) {
            return returnedTile.THREE;
        } else if (table[y][x].equals("4C")) {
            return returnedTile.FOUR;
        } else if (table[y][x].equals("5C")) {
            return returnedTile.FIVE;
        } else if (table[y][x].equals("6C")) {
            return returnedTile.SIX;
        } else if (table[y][x].equals("7C")) {
            return returnedTile.SEVEN;
        } else if (table[y][x].equals("8C")) {
            return returnedTile.EIGHT;
        } else {
            return returnedTile.KEEP_SAME;
        }
    }

    public boolean checkBombEnd(int y, int x){
        if (table[y][x].equals("b") || (table[y][x].equals("Fb"))){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkIfFlaggable(int yint, int xint) {
        for (int num = 0; num < 8; num++) {
            String number = Integer.toString(num);
            if (table[yint][xint].equals(number) || table[yint][xint].equals("x") || table[yint][xint].equals("b")){
                return true;

            }
        }
        return false;
    }
    }



