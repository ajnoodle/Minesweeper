import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JPanel pane;
    private JTextField xSizeField;
    private JTextField ySizeField;
    private JTextField percentSizeField;
    private JButton generateButton;
    private JLabel errorLabel;
    int xValue;
    int yValue;
    int percent;


    public HomeScreen() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 200));
        setResizable(false);
        setContentPane(pane);
        pack();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        xValue = Integer.parseInt(xSizeField.getText());
                        yValue = Integer.parseInt(xSizeField.getText());
                        percent = Integer.parseInt(percentSizeField.getText());

                        if (xValue >= 5 && xValue <= 50 && yValue >= 5 && yValue <= 50) {
                            if (percent >= 5 && percent <= 70) {

                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        TableScreen TableScreen = new TableScreen(xValue, yValue, percent);
                                        TableScreen.setVisible(true);
                                        setVisible(false);
                                    }
                                });
                            } else {
                                errorLabel.setText("Percent must be between 5 and 70");
                            }
                        } else {
                            errorLabel.setText("First values must be between 5 and 50");
                        }
                    } catch (NumberFormatException exception) {
                        errorLabel.setText("Please enter a number in all lines");
                    }

                }
        });
    }
}

