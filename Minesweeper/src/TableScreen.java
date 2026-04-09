import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableScreen extends JFrame {
    JPanel pane;
    JPanel mainPanel;
    JPanel topPanel;
    static JButton[][] displayedTable;
    JButton derol;
    ImageIcon unchartedTile = new ImageIcon("src/Sprites/UnchartedTile.png");
    ImageIcon chartedTile = new ImageIcon("src/Sprites/ChartedTile.png");
    ImageIcon derolTile = new ImageIcon("src/Sprites/Derol.png");
    double widthHeight;
    TableFunctions table;
    boolean firstClick;
    boolean alive;

    public TableScreen(int xSize, int ySize, int percent) {
        //declare variables
        firstClick = false;
        alive = true;
        derol = new JButton();
        //set window
        setTitle("Minesweeper");
        displayedTable = new JButton[xSize][ySize];
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setResizable(false);
        //set panes
        pane.setLayout(new GridLayout(xSize, ySize+1));
        mainPanel = new JPanel();
        topPanel = new JPanel();
        topPanel.add(derol);
        mainPanel.add(topPanel);
        mainPanel.add(pane);
        setContentPane(mainPanel);
        //set size and layout
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(50,50));
        //size
        derol.setSize(new Dimension(40,40));
        derol.setIcon(resizeIcon(derolTile, 40, 40));
        //create table
        runTable(xSize, ySize);
        sizeBorder(xSize,ySize);


        //inputs
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int finalX = x;
                int finalY = y;
                displayedTable[y][x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!firstClick) {
                            table = new TableFunctions(xSize, ySize, percent, finalX, finalY);
                            table.debug();
                            firstClick = true;
                        } else {
                            if (alive) {
                                if (table.checkBomb(finalX, finalY) == true) {
                                    System.out.println("Game over!");
                                    alive = false;
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

    public void sizeBorder(int xSize, int ySize){
        //set height and width and border
        pack();
        for (int h = 0; h < ySize; h++) {
            for (int w = 0; w < xSize; w++) {
                widthHeight = Math.sqrt(pane.getHeight()*pane.getHeight() / (xSize * ySize));
                int intWH = (int) widthHeight;
                displayedTable[h][w].setMargin(new Insets(0,0,0,0));
                displayedTable[h][w].setBorder(BorderFactory.createEmptyBorder());
                displayedTable[h][w].setFocusPainted(false);
                displayedTable[h][w].setIcon(resizeIcon(unchartedTile, intWH, intWH));
            }
        }
        pane.setBorder(BorderFactory.createEmptyBorder(0, topPanel.getHeight(), 0, topPanel.getHeight()));

    }
}
