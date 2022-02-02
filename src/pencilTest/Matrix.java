package pencilTest;

/**
 * <h1>Matrix</h1>
 * <br>A class defining a Matrix object, specifically a 2D array of integers.
 * <br>This is represented in Java as an array of arrays.
 * @author malai40
 */
public class Matrix {
	int[][] matrix;
	private int rows;
	private int cols;
	
	/**
	 * Instantiates an empty matrix object of given dimensions.
	 * @param numRows The number of rows that the matrix should have.
	 * @param numCols The number of columns that the matrix should have.
	 * @return Nothing.
	 */
	public Matrix(int numRows, int numCols) {
		matrix = new int[numRows][numCols];
		rows = numRows;
		cols = numCols;
	}
	
	/**
	 * Get the value at the specified location.
	 * @param row The row number to pull from.
	 * @param col The column number to pull from.
	 * @return The value at the location [row, col].
	 */
	public int getValueAt(int row, int col) {
		return matrix[row][col];
	}
	
	/**
	 * Set the value at the specified location.
	 * @param row The row number to set in.
	 * @param col The column number to set in.
	 * @param value The integer to insert in [row, col].
	 * @return Nothing.
	 */
	public void setValueAt(int row, int col, int value) {
		matrix[row][col] = value;
	}

	/**
	 * Get the number of rows in the matrix.
	 * @return The number of rows in the matrix.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Get the number of columns in the matrix.
	 * @return The number of columns in the matrix.
	 */
	public int getCols() {
		return cols;
	}
	
	/**
	 * Get the dimensions of the matrix.
	 * @return Nothing.
	 */
	public void getSize() {
		System.out.println(Integer.toString(rows) + "x" + Integer.toString(cols));
	}
	
	/**
	 * Print a 1D array to the console.
	 * @param arr The array to print.
	 * @return Nothing.
	 */
	private void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print("\t");
		}
	}
	
	/**
	 * Print a row in the matrix to the console.
	 * @param numberOfRow The number of the row to print.
	 * @return Nothing.
	 */
	public void printRow(int numberOfRow) {
		// print the entire row
		printArr(matrix[numberOfRow]);
	}
	
	/**
	 * Print a column in the matrix to the console.
	 * @param numberOfCol The number of the column to print.
	 * @return Nothing.
	 */
	public void printCol(int numberOfCol) {
		// print the entire col
		for (int i = 0; i < rows; i++) {
			System.out.println(matrix[i][numberOfCol]);
		}
	}
	
	/**
	 * Print the entire matrix to the console.
	 * @return Nothing.
	 */
	public void print() {
		for (int i = 0; i < rows; i++) {
			printRow(i);
			System.out.print("\n");
		}
	}
	
	
}
