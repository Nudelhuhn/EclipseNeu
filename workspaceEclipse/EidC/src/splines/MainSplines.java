package splines;

import java.awt.BorderLayout;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainSplines extends JFrame
{
	private static boolean checkedHermiteNat = false;
	private static boolean checkedHermiteGes = false;
	private static boolean checkedHermiteEingeEnden = false;
	private static boolean checkedHermiteParaEnden = false;
	private static boolean checkedBezier = false;

	public static boolean getHermiteNat()
	{
		return checkedHermiteNat;
	}

	public static boolean getHermiteGes()
	{
		return checkedHermiteGes;
	}

	public static boolean getHermiteEingeEnden()
	{
		return checkedHermiteEingeEnden;
	}

	public static boolean getHermiteParaEnden()
	{
		return checkedHermiteParaEnden;
	}
	
	public static boolean getBezier()
	{
		return checkedBezier;
	}
	
	public MainSplines()
	{
		setTitle("Splines und so");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(900, 100);
		setSize(900, 900);

		Zeichenflaeche zf = new Zeichenflaeche();
		add(zf, BorderLayout.CENTER);

		// Erstellen des Menüs
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Kurvenoptionen");
		menuBar.add(menu);

		// Checkbox um HermiteNatürlich zu aktivieren/deaktivieren
		JCheckBoxMenuItem item_hermiteNat = new JCheckBoxMenuItem("Hermite Natürlich");
		item_hermiteNat.addActionListener(e -> {

			if (item_hermiteNat.isSelected())
			{
				checkedHermiteNat = true;
			} else
			{
				checkedHermiteNat = false;
			}
			;
		});
		menu.add(item_hermiteNat);

		// Checkbox um HermiteGeschlossen zu aktivieren/deaktivieren
		JCheckBoxMenuItem item_hermiteGes = new JCheckBoxMenuItem("Hermite Geschlossen");
		item_hermiteGes.addActionListener(e -> {

			if (item_hermiteGes.isSelected())
			{
				checkedHermiteGes = true;
			} else
			{
				checkedHermiteGes = false;
			}
			;
		});
		menu.add(item_hermiteGes);

		// Checkbox um HermiteEngespannteEnden zu aktivieren/deaktivieren
		JCheckBoxMenuItem item_hermiteEingeEnden = new JCheckBoxMenuItem("Hermite eingespannte Enden");
		item_hermiteEingeEnden.addActionListener(e -> {

			if (item_hermiteEingeEnden.isSelected())
			{
				checkedHermiteEingeEnden = true;
			} else
			{
				checkedHermiteEingeEnden = false;
			}
			;
		});
		menu.add(item_hermiteEingeEnden);

		// Checkbox um HermiteParabolischeEnden zu aktivieren/deaktivieren
		JCheckBoxMenuItem item_hermiteParaEnden = new JCheckBoxMenuItem("Hermite parabolische Enden");
		item_hermiteParaEnden.addActionListener(e -> {

			if (item_hermiteParaEnden.isSelected())
			{
				checkedHermiteParaEnden = true;
			} else
			{
				checkedHermiteParaEnden = false;
			}
			;
		});
		menu.add(item_hermiteParaEnden);
		
		// Checkbox um Bezier zu aktivieren/deaktivieren
				JCheckBoxMenuItem item_bezier = new JCheckBoxMenuItem("Bezier");
				item_bezier.addActionListener(e -> {

					if (item_bezier.isSelected())
					{
						checkedBezier = true;
					} else
					{
						checkedBezier = false;
					}
					;
				});
				menu.add(item_bezier);

		setVisible(true);
	}

	public static void main(String[] args)
	{
		new MainSplines();
	}
}
