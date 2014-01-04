
import java.util.Random;

/***************************************************************************************************************
	v. 1.0 | Friday, April 27, 2012 5:15 PM

	DONE... For now >:3

****************************************************************************************************************/


public class Setup
{
	private String[] rows, cols;		// Holds the numbers in each rows and columns, format ex/ "2 1"
	private int[][] lens;				// Holds the simple value for each box in the 5 * 5 grid - 0 = blank, 1 = fill in.
	private int total;					// Holds the total number of filled in boxes.

	private int lvl;

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The Setup constructor initializes some variables. It was easier to put them here.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public Setup(int level)
	{
		lvl = level;
		rows = new String[lvl];
		cols = new String[lvl];
		lens = new int[lvl][lvl];
		total = 0;
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The setInfo method computes and organizes the game solution grid.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public void setInfo()
	{
		int totRow, totCol;							// Helps add up the numbers and make the displayed format.

		Random ranNum = new Random();				// Creates an object of the Random class.


		// Assigns a 1 (fill in) or 0 (cross out/blank) for each square in the 5 * 5 grid.
		for (int i = 0; i < lvl; i++)
			for (int j = 0; j < lvl; j++)
				lens[i][j] = ranNum.nextInt(2);

		for (int i = 0; i < lvl; i++)
		{
			// Resets the values for the next row/column.
			rows[i] = "";
			cols[i] = "";

			totRow = 0;
			totCol = 0;

			// If the lens value is 1 (fill in), add 1 to that row/col.
			// If the lens value is 0 (cross out/blank) and there a value to be added to the row/col, add the value to the row/col.
			for (int j = 0; j < lvl; j++)
			{
				if (lens[i][j] == 1)
				{
					totRow += 1;
					total += 1;							// Adds up total filled in squares.
				}
				if ((lens[i][j] == 0) && totRow != 0)
				{
					rows[i] += totRow + " ";
					totRow = 0;
				}

				if (lens[j][i] == 1)
					totCol += 1;
				if ((lens[j][i] == 0) && totCol != 0)
				{
					cols[i] += totCol + " ";
					totCol = 0;
				}
			}
			// This is for the last row/col place is 1. The above for loop will not catch the value.
			// Also, if the entire row/col is 0, the 2nd if statement will display a "0".
			if (totRow != 0)
				rows[i] += totRow;
			if (rows[i].equals(""))
				rows[i] = "0";

			if (totCol != 0)
				cols[i] += totCol;
			if (cols[i].equals(""))
				cols[i] = "0";

			rows[i].trim();
			cols[i].trim();
		}
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The getRow method returns the values to be displayed in the West Panel of NonoGUI.
	//	@return The String array of the values in each row.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public String[] getRow()
	{
		return rows;
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The getCol method returns the values to be displayed in the North Panel of NonoGUI.
	//	@return The String array of the values in each column.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public String[] getCol()
	{
		return cols;
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The getTotal method returns the total number of lens values that equal 1.
	//	@return The total number of filled squares.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public int getTotal()
	{
		return total;
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The getLengths method returns the lens 2D array.
	//	@return The 5 * 5 array of 0 or 1 for each square in the grid.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public int[][] getLengths()
	{
		return lens;
	}

	/* ---------------------------------------------------------------------------------------------------------------------
	//
	//	The test method. For testing.
	//
	// --------------------------------------------------------------------------------------------------------------------- */

	public void test()
	{
		for (int i = 0; i < lvl; i++)
		{
			for (int j = 0; j < lvl; j++)
				System.out.print(lens[i][j] + "  ");
			System.out.println("");
		}

		System.out.println("\nROWS START\n");
		for (int i = 0; i < lvl; i++)
			System.out.print("\n" + rows[i]);

		System.out.println("\nCOLUMNS START\n");

		for (int i = 0; i < lvl; i++)
			System.out.print("\n" + cols[i]);

		System.out.println("\n TOTAL: " + total);
	}
}