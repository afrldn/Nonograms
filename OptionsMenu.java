
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class OptionsMenu extends JMenuBar
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem newItem;
	private JMenuItem restartItem;			//
	private JMenuItem exitItem;
	private JCheckBoxMenuItem checkItem;	//
	private JMenuItem showAnsItem;			//
	private JMenuItem helpItem;

	private int lvl;

// Make NonoGUI get the JMenuBar

	public OptionsMenu(int level)
	{
		lvl = level;

		menuBar = new JMenuBar();

		buildFileMenu();
		menuBar.add(fileMenu);

		buildHelpMenu();
		menuBar.add(helpMenu);
	}

	public void buildFileMenu()
	{
		fileMenu = new JMenu("File", true);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		newItem = new JMenuItem("New Game", KeyEvent.VK_N);
		//newItem.setMnemonic(KeyEvent.VK_N);
		newItem.addActionListener(new NewListener());

		restartItem = new JMenuItem("Restart", KeyEvent.VK_R);
		//restartItem.setMnemonic(KeyEvent.VK_R);

		exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		//exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ExitListener());

		fileMenu.add(newItem);
		fileMenu.add(restartItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
	}

	public void buildHelpMenu()
	{
		helpMenu = new JMenu("Help", true);
		helpMenu.setMnemonic(KeyEvent.VK_H);

		checkItem = new JCheckBoxMenuItem("Check for Mistakes", false);
		checkItem.setMnemonic(KeyEvent.VK_C);

		showAnsItem = new JMenuItem("Show Answer", KeyEvent.VK_S);
		//showAnsItem.setMnemonic(KeyEvent.VK_S);

		helpItem = new JMenuItem("Instructions", KeyEvent.VK_I);
		//helpItem.setMnemonic(KeyEvent.VK_I);
		helpItem.addActionListener(new HelpListener());

		helpMenu.add(checkItem);
		helpMenu.add(showAnsItem);
		helpMenu.add(helpItem);
	}

	public JMenuBar getOptionsMenuBar()
	{
		return menuBar;
	}

	public JMenuItem getRestartItem()
	{
		return restartItem;
	}

	public JCheckBoxMenuItem getCheckItem()
	{
		return checkItem;
	}

	public JMenuItem getShowAnsItem()
	{
		return showAnsItem;
	}

	private class HelpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new Help();
		}
	}

	private class NewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			new LvlGUI();
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



