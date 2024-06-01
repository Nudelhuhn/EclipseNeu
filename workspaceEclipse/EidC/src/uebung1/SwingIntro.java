package uebung1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

public class SwingIntro
{
	public static void main(String[] args)
	{
		// Create a JFrame, aka a window that will house all of our ui components
		// Give it a title and set its default size
		JFrame frame = new JFrame("Hallo Swing");
		frame.setSize(400, 400);
		// Set the default close operation, so that the whole application exits,
		//  when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the default location the window will show up on the screen
		frame.setLocation(100, 100);
		// Make the window non-resizable by the user
		frame.setResizable(false);
		// And set its layout manager to a box layout
		// The Box layout takes the Container it should layout and the axis
		//  in which the components should be aligned
		// In this case components are aligned vertically
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		// Create a new label and add it to the frame
		JLabel label = new JLabel("Hallo ECG");
		frame.add(label);

		// Create a new button, which will change its text when clicked
		JButton button = new JButton("Click Me");

		// We need to add at least one action listener to the button to react to clicks
		// We can either add an action listener in form of a lambda expression
		button.addActionListener(e ->
		{
			System.out.println(
					String.format("Button with Text %s pressed %d, %f", ((JButton) e.getSource()).getText(), 10, 0.5F));
		});

		// Or by constructing an object of a class that implements the ActionListener interface
		button.addActionListener(new ButtonActionHandler1(frame));
		frame.add(button);

		// Another useful ui-element is the slider
		// This constructor accepts three values (min, max and default value)
		JSlider slider = new JSlider(0, 100, 10);
		// We can register a change listener on the slider (given here in form of a lambda expression)
		//  that gets called whenever the slider's value changes
		slider.addChangeListener(e ->
		{
			int curr_value = ((JSlider) e.getSource()).getValue();
			System.out.println(String.format("Slider value is %d", curr_value));
		});
		frame.add(slider);

		// A menu bar is a top aligned menu container, which should only be places i
		//  a JFrame
		// It will not be layouted like the rest of the elements
		JMenuBar menu_bar = new JMenuBar();
		frame.setJMenuBar(menu_bar);

		// Create the first context menu and add it to the menu bar
		JMenu menu = new JMenu("Menu 1");
		menu_bar.add(menu);

		// Add an item to the menu and set its action listener
		JMenuItem menu_item = new JMenuItem("Menu Item");
		menu_item.addActionListener(e -> System.out.println("Menu Item clicked!"));
		menu.add(menu_item);

		// Create the second context menu and add it to the menu bar
		JMenu menu2 = new JMenu("Menu 2");
		menu_bar.add(menu2);

		// Create a checkbox menu item, register an action listener for it
		//  and add it to the second menu
		// It should be noted here, that there exists another JCheckbox ui element
		//  that can be used outside of JMenu's (i.e. as part of a JPanel or JFrame,
		//  similiar to the JLabel, JButton, etc.)
		JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem("Enable me");
		checkbox.addActionListener(e ->
		{
			boolean is_selected = ((JCheckBoxMenuItem) e.getSource()).isSelected();
			System.out.println(String.format("Checkbox is %b", is_selected));
		});
		menu2.add(checkbox);

		// A call to frame.pack() results in the frame computing how much size it actually needs to "neatly"
		//  show all the ui components and resizes itself to this size
		frame.pack();
		// Show our now configured frame
		// It is important that this gets called after all the elements have been added
		//  to the frame, since it's not guaranteed that adding components to the frame
		//  after it has already been set as visible execute a frame redraw
		frame.setVisible(true);
	}
}

// A small class that is used to implement the action listener interface
class ButtonActionHandler1 implements ActionListener
{
	// Frame references the frame the event source is contained in
	private JFrame frame_;
	// The counter keeps track of how many times the action listener was called
	private int counter_ = 0;

	public ButtonActionHandler1(JFrame frame)
	{
		frame_ = frame;
	}

	// This method gets called every time the action listener should do something,
	//  after action event was triggered (i.e. a button this listener was registered on was clicked)
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Grab the button the event was triggered by
		// This should only be done, if we are absolutely sure, that the event source will always
		//  be of type JButton, otherwise a ClassCastException runtime error will be thrown
		JButton button = (JButton) e.getSource();
		// Increment the counter, because the action listener is currently being triggered
		counter_++;
		// Set the button text to a string that tells us how many times the button was clicked
		button.setText(String.format("Button has been clicked %d times", counter_));
		// Repack the frame, so that all the ui elements are fully visible
		frame_.pack();
	}
}