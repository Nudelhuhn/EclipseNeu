package uebung1;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingExample
{
    public static void main()
    {
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        frame.add(panel);

        DrawingPanel dp = new DrawingPanel();
        frame.add(dp, BorderLayout.CENTER);
        JLabel x = new JLabel("x");
        panel.add(x);
        JLabel y = new JLabel("y");
        panel.add(y);
        
        frame.setVisible(true);
    }
}

class DrawingPanel extends JPanel
{

}
