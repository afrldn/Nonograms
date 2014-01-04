
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

/***************************************************************************************************************
	v. 3.1

	UPDATE:
	 1. YAY! IT WORKS!!!!!!:D!!:D!!!!!!!!!
	 2. Better pictures.
	 3. YAY-er!!! No bugs that I can find!
	 4. Exit button
	 5. Check for mistakes button
	 6. Restart button
	 7. New puzzle Button
	 8. Give up/show answer button
	 9. Better Font.
	 10. The alignment of the columns over the boxes.
	 11. Made available to many sizes
	 12. Changed look and feel
	 13. 10 * 10, 15 * 15, 20 * 20 grids, etc.
	 	- a pop-up asking for what size you want
	 14. Delete grids past 15 * 15
	 15. Fixed spacing of display numbers for bigger grids
	 16. Add alt + click option to X box
	 17. Add lines between 5 * 5 grids in bigger grids


	TO ADD:

	~~~~~~~~ SPIFFY UP ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	 3. Fix formating for remaining grids (display numbers and boxes). - Change to GridBag Layout

	 4. Add color changer to the menu option

	 5. Add shape changer? for X's, fill ins, and empty boxes?
	 	5a. Move all pictures to a separate folder.
	 	5b. Themes?

	 6. Add to menu: choose size of boxes?
	 	- small, large? How? With Graphics or Paint?

	 7. Timer in its own method/class.

	 8. Clean up and comments.

	 9. Save all in jic.txt


	 ~~~~~~~!MAJOR CHANGE!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


	 10. Welcome Frame
	 	- Welcome label
	 	- Log In Panel
	 		~ If user logs in correctly go to #11
	 		* Username Label and TF
	 		* PW Label and TF
	 		* Remember Me Checkbox (~PW hint in JOptionPane)
	 		* Forgot PW button
	 	- New User? Panel
	 		* New User? label
	 		* Create Account button
	 			~ If Create Account chosen go to #14
	 		* Sign in as Guest Button
	 			~ If Guest chosen, go to #13

	 11. User Welcome Frame
	 	- Welcome ___! label
	 	- Scores/History Panel
	 	- Choose Game Panel
	 		* New Game Button
	 			~ If New Game is chosen got to #12
	 		* Continue Game Button

	 12. Chose Game Frame
	 	- Level Panel
	 		* Easy Button
	 		* Normal Button
	 		* Hard Button
	 	- Timing Panel
	 		* Timed button
	 		* Zen Mode button

	 13. Guest Frame
	 	~ Mention that guests can't save scores or play timed version
	 	- Welcome Guest### label
	 	- Level Panel
	 		* Easy Button
	 		* Normal Button
	 		* Hard Button
	 		* Separator
	 		* Instructions Button

	 14. New User Frame
	 	~ Offer tutorial after account is made
	 	- Username label and TF
	 	- Check username button
	 	- PW label and TF
	 	- Redo PW label and TF
	 	- Hint Question label and drop down
	 	- Hint label and TF
	 	- Create Account button
	 	- Cancel button

	 ~~~~~ PART OF #10-14 ~~~~~~~~~~~~~~~~~~~~~~~~

	 15. Save Scores:
	 	- wins/loses #
	 	- streaks
	 	- high score (maybe separate)
	 	- fastest time
	 	- avg time?

	 16. High Score List

	 17. Create Scoring Rules Frame
	 	 - Add to Menu

	 18. Timer


	 ~~~~~~ OTHER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	 19. Audio. At least winning sound.

	 20. Help/Instructions frame
			- Awesome Tutorial

	 21. Splash screen!?

	 22. Clean up and Comments.


	PROBLEMS:
	 - Get splash screen to work :(
	 - Change to MouseListener?


****************************************************************************************************************/


public class NonoGUI extends JFrame
{
	private Setup set;												// Holds an object of the Setup class

	private OptionsMenu optionsMenu;								// Holds an object for the OptionsMenu class
	private JMenuBar optMenuBar;									// Holds the menu bar for the options menu
	private JMenuItem restartItem;									// Holds restart button in the file menu
	private JCheckBoxMenuItem checkItem;							// Holds the check for mistakes button on the help menu
	private JMenuItem showAnsItem;									// Holds the show answers button in the help menu

	private JPanel westP;											// Holds a left panel for the display numbers
	private JLabel[] westL;											// Holds the array of number labels that will be displayed

	private JPanel northP;											// Holds a top panel for the display numbers
	private JLabel[][] northL;										// Holds the array of number labels that will be displayed

	private JPanel gridP;											// Holds the center panel for the grid
	private JButton[][] gridB;										// Holds an 2D array of each box in the grid
	private ImageIcon empty;										// Holds an icon of an empty box

	private JScrollBar scroll;

	private ImageIcon filled;										// Holds an icon of a filled in box
	private ImageIcon X;											// Holds an icon of an Xed out box
	private int[][] gridClicks;										// Holds an 2D array of each box's clicked status
	private int total;												// Holds the total number of boxes the user filled in

	private ImageIcon win;											// Holds the winning gif

	private JPanel optionsP;										// Holds the bottom options panel
	private JButton exit, check, restart, new_game, show, help;		// Holds the buttons for the options panel
	private ImageIcon red_X, red_filled;							// Holds icons of red filled in and Xed out boxes for mistake checker

	private Font font;												// Holds the font for the display numbers

	private int WINDOW_WIDTH;			;							// Holds the width of the frame
	private int WINDOW_HEIGHT;										// Holds the height of the frame

	private int lvl;												// Holds 5, 10, etc. the chosen level grid size



	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The NonoGUI constructor creates the frame (and states its detail) for the GUI.
	//	@param level The size of the grid.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public NonoGUI(int level)
	{
		// Passes the size of the grid to the Backwork method.
		Backwork(level);

		// Sets the details of the frame.
		setTitle("Nonograms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new BorderLayout());

		// Creates and places all of the panels in the frame
		WestPanel();
		add(westP, BorderLayout.WEST);

		NorthPanel();
		add(northP, BorderLayout.NORTH);

		OptionsMenu();

		GridPanel();
		add(gridP, BorderLayout.CENTER);

		// Sets the frame to visible.
		setVisible(true);
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The Backwork method sets frame's height and width and the preliminary data for the some of the variables.
	//	@param level The size of the grid.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public void Backwork(int level)
	{
		setLookAndFeel();

		lvl = level;

		if (lvl == 5)
		{
			WINDOW_WIDTH = 280;
			WINDOW_HEIGHT = 335;
		}
		else if (lvl == 10)
		{
			WINDOW_WIDTH = 553;
			WINDOW_HEIGHT = 618;
		}
		else if (lvl == 15)
			WINDOW_WIDTH = 835;

		else if (lvl == 20)
			WINDOW_WIDTH = 1100;
		else
			WINDOW_WIDTH = 1369;

		if (lvl > 10)
			WINDOW_HEIGHT = 730;

		set = new Setup(lvl);							// Creates an object of the Setup class.
		set.setInfo();									// Computes and organizes the game solution grid
		total = 0;
		gridClicks = new int[lvl][lvl];
		font = new Font("Courier New", Font.BOLD,  15);	// The font for the display numbers.
		set.test();										// Prints the solution to the game.

		//scroll = new JScrollBar(this.JFrame, Adjustable.VERTICAL);
		//(this.JFrame, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setWheelScrollingEnabled(true);
		//this.add(scroll);
	}

	public void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: Setting look and feel");
			System.exit(0);
		}
	}


	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The WestPanel method displays the numbers for the rows in the game.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public void WestPanel()
	{
		westP = new JPanel();
		westP.setLayout(new GridLayout(lvl, 1));

		String[] showRow = set.getRow();
		westL = new JLabel[lvl];

		for (int i = 0; i < lvl; i++)
		{
			westL[i] = new JLabel(showRow[i], JLabel.RIGHT);
			westL[i].setFont(font);
			westP.add(westL[i]);
		}
	}


	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The NorthPanel method displays the numbers for the columns in the game. CURRENTLY fixed :D
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public void NorthPanel()
	{
		String spacing = "";

		northP = new JPanel();
		northP.setLayout(new GridLayout((lvl/2) + 1, lvl + 2));

		String[][] temp = new String[lvl][];				// A temporary ragged array of each column's values. Holds the

		northL = new JLabel[(lvl/2) + 1][lvl];

		int rmd = 0;

		for (int i = 0; i < lvl; i++)
		{
			temp[i] = set.getCol()[i].split(" ");	// ragged array of the 5 cols

			rmd = ((lvl/2) + 1) - temp[i].length;

			for (int j = 0; j < rmd; j++)
				northL[j][i] = new JLabel("");

			for (int j = 0; j < temp[i].length; j++)
			{
				northL[rmd + j][i] = new JLabel(temp[i][j]);
				northL[rmd + j][i].setFont(font);
				northL[rmd + j][i].setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		for (int i = 0; i < ((lvl/2) + 1); i++)
		{
			//if (lvl == 5)
			//	spacing = "  ";
			if (lvl == 10)
				spacing = "    ";
			else if (lvl == 15)
				spacing = "     ";

			northP.add(new JLabel(spacing));
			northP.add(new JLabel(spacing));

			for (int j = 0; j < lvl; j++)
				northP.add(northL[i][j]);
		}
	}

	public void OptionsMenu()
	{
		optionsMenu = new OptionsMenu(lvl);
		optMenuBar = optionsMenu.getOptionsMenuBar();

		restartItem = optionsMenu.getRestartItem();
		restartItem.addMouseListener(new ButtonListener());

		checkItem = optionsMenu.getCheckItem();
		checkItem.addMouseListener(new ButtonListener());

		showAnsItem = optionsMenu.getShowAnsItem();
		showAnsItem.addMouseListener(new ButtonListener());

		setJMenuBar(optMenuBar);
	}



	public void GridPanel()
	{
		gridP = new JPanel();
		gridP.setLayout(new GridLayout(lvl, lvl));

		gridB = new JButton[lvl][lvl];

		empty = new ImageIcon("checkbox_empty.jpg");

		red_filled = new ImageIcon("red_filled.png");
		red_X = new ImageIcon("red_X.png");

		for (int i = 0; i < lvl; i++)
		{
			for (int j = 0; j < lvl; j++)
			{
				gridB[i][j] = new JButton(empty);
				gridB[i][j].setBackground(Color.WHITE);
				gridB[i][j].addMouseListener(new ButtonListener());


				if (((i + 1) % 5 == 0) && ((j + 1) % 5 == 0) && ((i + 1) != lvl) && ((j + 1) != lvl))
					gridB[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.DARK_GRAY));
				else if ((i + 1) % 5 == 0 && ((i + 1) != lvl))
					gridB[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.DARK_GRAY));

				else if ((j + 1) % 5 == 0 && ((j + 1) != lvl))
					gridB[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.DARK_GRAY));
				else
					gridB[i][j].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

				gridP.add(gridB[i][j]);
			}
		}
	}

	public void WonMessage()
	{
		Random ranNum = new Random();
		int num = ranNum.nextInt(5);
		win = new ImageIcon("win" + (num + 1) + ".gif");
		JOptionPane.showMessageDialog(null, null, "CONGRATULATIONS!!!", 2, win);
		System.exit(0);
	}

	private class ButtonListener implements MouseListener
	{
		public void mousePressed(MouseEvent e)
		{
			boolean correct = false;
			boolean mistake = false;
			int score = 0;
			filled = new ImageIcon("fill1.png");
			X = new ImageIcon("X1.jpg");

			for (int i = 0; i < lvl; i++)
			{
				for (int j = 0; j < lvl; j++)
				{
					if ((e.getComponent() == gridB[i][j]) && (e.isShiftDown()))
					{
						gridClicks[i][j] = 2;
						gridB[i][j].setIcon(X);
					}
					else if ((e.getComponent() == gridB[i][j]) && ((gridClicks[i][j] % 3) == 0))
					{
						total += 1;
						gridClicks[i][j] += 1;
						gridB[i][j].setIcon(filled);
					}
					else if((e.getComponent() == gridB[i][j]) && ((gridClicks[i][j] % 3) == 1))
					{
						total -= 1;
						gridClicks[i][j] += 1;
						gridB[i][j].setIcon(X);
					}
					else if((e.getComponent() == gridB[i][j]) && ((gridClicks[i][j] % 3) == 2))
					{
						gridClicks[i][j] += 1;
						gridB[i][j].setIcon(empty);
					}

					if (e.getComponent() == restartItem)
					{
						total = 0;
						gridClicks[i][j] = 0;
						gridB[i][j].setIcon(empty);
						score = 0;
					}
					if (checkItem.isSelected())
					{
						if ((set.getLengths()[i][j] == 1) && (((gridClicks[i][j] % 3) == 2)))
						{
							gridB[i][j].setIcon(red_X);
							mistake = true;
						}
						if ((set.getLengths()[i][j] == 0) && (((gridClicks[i][j] % 3) == 1)))
						{
							gridB[i][j].setIcon(red_filled);
							mistake = true;
						}
					}

					if (e.getComponent() == showAnsItem)
					{
						if(set.getLengths()[i][j] == 1)
							gridB[i][j].setIcon(filled);
						if(set.getLengths()[i][j] == 0)
							gridB[i][j].setIcon(X);
					}
				}
			}
			if (!mistake && (e.getComponent() == check))
				JOptionPane.showMessageDialog(null, "No mistakes.");


			if (total >= set.getTotal())
			{
				for (int i = 0; i < lvl; i++)
					for (int j = 0; j < lvl; j++)
						if  (((gridClicks[i][j] % 3) == 1) && (set.getLengths()[i][j] == 1))
							score += 1;
				if (score == set.getTotal())
					WonMessage();
				else
					System.out.print("WRONG  ");
			}
		}
		public void mouseExited(MouseEvent me){}
		public void mouseClicked(MouseEvent me){}
		public void mouseReleased(MouseEvent me){}
		public void mouseEntered(MouseEvent me){}
	}
}