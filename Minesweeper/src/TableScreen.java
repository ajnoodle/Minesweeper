import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableScreen extends JFrame {
    JPanel pane;
    GridBagConstraints gbc = new GridBagConstraints();
    static JButton[][] displayedTable;
    ImageIcon unchartedTile = new ImageIcon("src/Sprites/UnchartedTile.png");
    ImageIcon chartedTile = new ImageIcon("src/Sprites/ChartedTile.png");
    double widthHeight;
    TableFunctions table;
    boolean firstClick;

    public TableScreen(int xSize, int ySize, int percent) {
        firstClick = false;
        setTitle("Minesweeper");
        displayedTable = new JButton[xSize][ySize];
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setResizable(false);
        setContentPane(pane);
        pane.setLayout(new GridLayout(xSize, ySize));
        runTable(xSize, ySize);


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
                            System.out.println(firstClick);
                        }else{

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
                gbc.gridx = w;
                gbc.gridy = h;
                //divide area by x and y then square it to find area of the buttons
                widthHeight = Math.sqrt(640000 / (x * y));
                int intWH = (int) widthHeight;
                pane.add(displayedTable[h][w]);
                displayedTable[h][w].setIcon(resizeIcon(unchartedTile, intWH, intWH));
            }
        }
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
