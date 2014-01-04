
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class LvlGUI extends JFrame
{
	private JLabel pick;
	private JPanel buttonP;
	private JButton[] grid;
	private JButton exit;

	private ImageIcon[] pics;

	private final int WINDOW_WIDTH = 200;
	private final int WINDOW_HEIGHT = 170;

	public LvlGUI()
	{
		setLookAndFeel();

		setTitle("Choose Your Difficulty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new BorderLayout());

		buildFrame();

		setVisible(true);
	}

	public void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: Setting look and feel");
			System.exit(0);
		}
	}

	public void buildFrame()
	{
		pick = new JLabel("Welcome to Nkem's Nonograms Game!!    Please choose your level of difficulty: ");
		add(pick, BorderLayout.NORTH);

		buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(3, 1));

	//	pics = new ImageIcon[7];
		grid = new JButton[3];

		for (int i = 0; i < 3; i++)
		{
			grid[i] = new JButton((5 * (i + 1)) + " by " + (5 * (i + 1)) + " grid");
			grid[i].addActionListener(new ButtonListener());
/*			Failed coloring attempt
			pics[i] = new ImageIcon((i + 1) * 5 + "by" + (i + 1) * 5 + ".png");
			grid[i] = new JButton(pics[i]);
			grid[i].setBackground(Color.WHITE);
			*/
			buttonP.add(grid[i]);
		}

		add(buttonP, BorderLayout.CENTER);

		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		add(exit, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);

			for (int i = 0; i < 3; i++)
				if (e.getSource() == grid[i])
					new NonoGUI((i + 1) * 5);
		}
	}

	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, "Thanks for playing! Come again! ^_^");
			System.exit(0);
		}
	}
}

