package uebung1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InneresFenster extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        int x = getWidth() / 2 - 5; // x-Koordinate des grünen Punkts
        int y = getHeight() / 2 - 5; // y-Koordinate des grünen Punkts
        g.fillOval(x, y, 10, 10); // zeichnet einen grünen Punkt in der Mitte
    }

    public static void main(String[] args) {
    	int frameWidth = 1100;
    	int frameHeight = 700;
    	
        JFrame frame = new JFrame("Inneres Fenster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(); // Haupt-JPanel
        outerPanel.setPreferredSize(new Dimension(frameWidth, frameHeight)); // Größe des Hauptfensters
        frame.getContentPane().add(outerPanel); // fügt das Haupt-JPanel zum JFrame hinzu

        JPanel innerPanel1 = new InneresFenster(); // JPanel für das innere Fenster
        innerPanel1.setBackground(Color.WHITE);
        innerPanel1.setPreferredSize(new Dimension(frameWidth-320, frameHeight-10));
        outerPanel.add(innerPanel1, BorderLayout.WEST); // fügt das innere JPanel zum äußeren JPanel hinzu
        
        JPanel rightSpace = new JPanel();
        rightSpace.setPreferredSize(new Dimension(300,0));
        outerPanel.add(rightSpace, BorderLayout.EAST);

        frame.pack();
        frame.setLocationRelativeTo(null); // platziert das Fenster in der Mitte des Bildschirms
        frame.setVisible(true);
    }
}


