import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableScreen extends JFrame {
    JPanel pane;
    JPanel mainPanel;
    JPanel topPanel;
    JLabel bombLabel;
    static JButton[][] displayedTable;
    JButton derol;
    ImageIcon unchartedTile = new ImageIcon("src/Sprites/UnchartedTile.png");
    ImageIcon chartedTile = new ImageIcon("src/Sprites/ChartedTile.png");
    ImageIcon derolTile = new ImageIcon("src/Sprites/Derol.png");
    ImageIcon flagTile = new ImageIcon("src/Sprites/FlaggedTile.png");
    ImageIcon boomTile = new ImageIcon("src/Sprites/BoomTile.png");
    ImageIcon winTile = new ImageIcon("src/Sprites/WinDerolTile.png");
    ImageIcon loseTile = new ImageIcon("src/Sprites/DeadDerolTile.png");
    ImageIcon oneTile = new ImageIcon("src/Sprites/OneTile.png");
    ImageIcon twoTile = new ImageIcon("src/Sprites/TwoTile.png");
    ImageIcon threeTile = new ImageIcon("src/Sprites/ThreeTile.png");
    ImageIcon fourTile = new ImageIcon("src/Sprites/FourTile.png");
    ImageIcon fiveTile = new ImageIcon("src/Sprites/FiveTile.png");
    ImageIcon sixTile = new ImageIcon("src/Sprites/SixTile.png");
    ImageIcon sevenTile = new ImageIcon("src/Sprites/SevenTile.png");
    ImageIcon eightTile = new ImageIcon("src/Sprites/eightTile.png");
    int widthHeight;
    TableFunctions table;
    boolean firstClick;
    boolean alive;
    int bombAmount;

    public TableScreen(int xSize, int ySize, int percent) {
        //declare variables
        firstClick = false;
        alive = true;
        bombLabel = new JLabel("Bombs left:");
        derol = new JButton((resizeIcon(derolTile, 50, 50)));
        //set window
        setTitle("Minesweeper");
        displayedTable = new JButton[xSize][ySize];
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setResizable(false);
        //set panes
        pane.setLayout(new GridLayout(xSize, ySize));
        //set size and layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        topPanel.setPreferredSize(new Dimension(50, 50));
        //size
        setContentPane(mainPanel);
        derol.setSize(new Dimension(40, 40));
        derol.setMargin(new Insets(0, -2, 0, -2));
        //axis
        //add pane
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(bombLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(derol);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        mainPanel.add(topPanel);
        mainPanel.add(pane);
        //create table
        runTable(xSize, ySize);
        sizeBorder(xSize, ySize);


        //inputs
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int finalX = x;
                int finalY = y;


                displayedTable[y][x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //change table based on click
                        if (!firstClick) {
                            table = new TableFunctions(xSize, ySize, percent, finalX, finalY);
                            table.revealTiles(finalY, finalX);
                            table.debug();
                            firstClick = true;
                            bombAmount = table.bombCount;
                            bombLabel.setText("Bombs left: " + bombAmount);
                        } else {
                            table.debug();
                            if (alive) {
                                if (!table.checkForFlag(finalY, finalX)) {
                                    if (table.checkBomb(finalX, finalY) == true) {

                                        System.out.println("Game over!");
                                        for (int y = 0; y < ySize; y++) {
                                            for (int x = 0; x < xSize; x++) {
                                                boolean bomb = table.checkBombEnd(y, x);
                                                if (bomb) {
                                                    displayedTable[y][x].setIcon(resizeIcon(boomTile, widthHeight, widthHeight));
                                                }
                                                derol.setIcon((resizeIcon(loseTile, 100, 100)));
                                                derol.setSize(new Dimension(100, 100));
                                                derol.setMargin(new Insets(0, -2, 0, -2));
                                            }
                                        }
                                        alive = false;
                                    }
                                    table.revealTiles(finalY, finalX);
                                }
                            }
                        }

                        //update tiles when clicked
                        for (int y = 0; y < ySize; y++) {
                            for (int x = 0; x < xSize; x++) {
                                TableFunctions.returnedTile returnStatement = table.checkForClear(y, x);
                                if (returnStatement == TableFunctions.returnedTile.CLEAR) {
                                    displayedTable[y][x].setIcon(resizeIcon(chartedTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.ONE) {
                                    displayedTable[y][x].setIcon(resizeIcon(oneTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.TWO) {
                                    displayedTable[y][x].setIcon(resizeIcon(twoTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.THREE) {
                                    displayedTable[y][x].setIcon(resizeIcon(threeTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.FOUR) {
                                    displayedTable[y][x].setIcon(resizeIcon(fourTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.FIVE) {
                                    displayedTable[y][x].setIcon(resizeIcon(fiveTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.SIX) {
                                    displayedTable[y][x].setIcon(resizeIcon(sixTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.SEVEN) {
                                    displayedTable[y][x].setIcon(resizeIcon(sevenTile, widthHeight, widthHeight));
                                } else if (returnStatement == TableFunctions.returnedTile.EIGHT) {
                                    displayedTable[y][x].setIcon(resizeIcon(eightTile, widthHeight, widthHeight));
                                }
                            }
                        }
                    }
                });

                displayedTable[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (firstClick) {
                                if (table.checkForFlag(finalY, finalX)) {
                                    displayedTable[finalY][finalX].setIcon(resizeIcon(unchartedTile, widthHeight, widthHeight));
                                    table.deflagTile(finalY, finalX);
                                    bombAmount = bombAmount + 1;
                                    bombLabel.setText("Bombs left: " + bombAmount);
                                    table.debug();
                                } else {
                                    displayedTable[finalY][finalX].setIcon(resizeIcon(flagTile, widthHeight, widthHeight));
                                    table.flagTile(finalY, finalX);
                                    table.debug();
                                    bombAmount = bombAmount - 1;
                                    bombLabel.setText("Bombs left: " + bombAmount);


                                }
                            }
                        }
                    }
                });
            }
        }


        pack();

    }

    public void runTable(int x, int y) {
        for (int h = 0; h < y; h++) {
            for (int w = 0; w < x; w++) {
                displayedTable[h][w] = new JButton();

            }
        }
        for (int h = 0; h < y; h++) {
            for (int w = 0; w < x; w++) {

                pane.add(displayedTable[h][w]);

            }
        }
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void sizeBorder(int xSize, int ySize) {
        //set height and width and border
        pack();
        for (int h = 0; h < ySize; h++) {
            for (int w = 0; w < xSize; w++) {
                widthHeight = (int) Math.sqrt(pane.getHeight() * pane.getHeight() / (xSize * ySize));
                int intWH = (int) widthHeight;
                displayedTable[h][w].setMargin(new Insets(0, 0, 0, 0));
                displayedTable[h][w].setBorder(BorderFactory.createEmptyBorder());
                displayedTable[h][w].setFocusPainted(false);
                displayedTable[h][w].setIcon(resizeIcon(unchartedTile, intWH, intWH));
            }
        }
        pane.setBorder(BorderFactory.createEmptyBorder(0, topPanel.getHeight(), 0, topPanel.getHeight()));

    }


}
