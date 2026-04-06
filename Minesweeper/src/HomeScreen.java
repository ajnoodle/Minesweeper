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

    public HomeScreen(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 200));
        setResizable(false);
        setContentPane(pane);
        pack();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

