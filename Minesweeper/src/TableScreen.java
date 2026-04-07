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
    int width;
    public TableScreen(int xSize, int ySize, int percent) {
        setTitle("Minesweeper");
        displayedTable = new JButton[xSize][ySize];
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        setResizable(false);
        setContentPane(pane);
        pane.setLayout(new GridLayout(xSize,ySize));

        runTable(xSize, ySize);

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);
        pack();
    }

    public void runTable(int x, int y) {
        for (int h = 0; h < y; h++) {
            for (int w = 0; w < x; w++) {
                displayedTable[h][w] = new JButton();
                int width = displayedTable[h][w].getWidth();
                int height = displayedTable[h][w].getHeight();
                displayedTable[h][w].setIcon(resizeIcon(unchartedTile, width, height));
            }
        }

        for (int h = 0; h < y; h++) {
            for (int w = 0; w < x; w++) {
                gbc.gridx = w;
                gbc.gridy = h+1;
                pane.add(displayedTable[h][w]);

            }
        }
    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
