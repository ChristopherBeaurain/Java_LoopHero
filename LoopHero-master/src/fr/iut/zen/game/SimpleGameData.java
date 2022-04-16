package fr.iut.zen.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

public class SimpleGameData {
	private final Cell[][] matrix;
	private GridPosition selected;
	private GridPosition bob = new GridPosition(6, 3);

	public SimpleGameData(int nbLines, int nbColumns) {
		if (nbLines < 1 || nbColumns < 1) {
			throw new IllegalArgumentException("at least one line and column");
		}
		matrix = new Cell[nbLines][nbColumns];

	}

	/**
	 * The number of lines in the matrix contained in this GameData.
	 * 
	 * @return the number of lines in the matrix.
	 */
	public int nbLines() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix contained in this GameData.
	 * 
	 * @return the number of columns in the matrix.
	 */
	public int nbColumns() {
		return matrix[0].length;
	}

	private void checkBoundsOrThrow(int line, int column) {
		Objects.checkIndex(line, matrix.length);
		Objects.checkIndex(column, matrix[0].length);
	}

	/**
	 * The coordinates of the cell selected, if a cell is selected.
	 * 
	 * @return the coordinates of the selected cell; null otherwise.
	 */
	public GridPosition getSelected() {
		return selected;
	}

	public GridPosition bob() {
		return bob;
	}

	public Cell[][] getMatrix() {
		return matrix;
	}

	/**
	 * Tests if a cell is selected.
	 * 
	 * @return true if and only if at least one cell is selected; false otherwise.
	 */
	public boolean hasASelectedCell() {
		return selected != null;
	}

	/**
	 * Selects the cell identified by the specified coordinates.
	 * 
	 * @param line   the first coordinate of the cell.
	 * @param column the second coordinate of the cell.
	 * @throws IndexOutOfBoundsException
	 * @throws IllegalStateException     if a first cell is already selected.
	 */
	public void selectCell(int line, int column) {
		checkBoundsOrThrow(line, column);
		if (selected != null) {
			throw new IllegalStateException("First cell already selected");
		}
		selected = new GridPosition(line, column);
	}

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void selectNextCellInLine() {
		if (selected == null) { // no cell is selected
			selectCell(0, 0);
			return;
		}
		int column = selected.column() + 1;
		if (column >= matrix[0].length) {
			selected = null;
		} else {
			selected = new GridPosition(selected.line(), column);
		}
	}

	/**
	 * Selects the next cell in the same line, if it exists. If no cell is selected,
	 * start with the first cell.
	 */
	public void moveBob(int position) {
		List<Integer> listOfX = List.of(3, 3, 3, 3, 3, 4, 5, 5, 5, 5, 5, 6, 7, 7, 8, 8, 8, 8, 8, 8, 7, 6, 6, 5, 4, 3, 3,
				3, 3, 3);
		List<Integer> listOfY = List.of(7, 8, 9, 10, 11, 11, 11, 10, 9, 8, 7, 7, 7, 6, 6, 5, 4, 3, 2, 1, 1, 1, 2, 2, 2,
				2, 3, 4, 5, 6);
		bob = new GridPosition(listOfY.get(position), listOfX.get(position));
	}

	public void fillMatrix() {
		for (int x = 0; x < 21; x++) {
			for (int y = 0; y < 12; y++) {
				matrix[y][x] = new Background(x, y);
			}
		}
		matrix[6][3] = new Camp(3, 6);
		List<Integer> listOfX = List.of(3, 3, 3, 3, 3, 4, 5, 5, 5, 5, 5, 6, 7, 7, 8, 8, 8, 8, 8, 8, 7, 6, 6, 5, 4, 3, 3,
				3, 3);
		List<Integer> listOfY = List.of(7, 8, 9, 10, 11, 11, 11, 10, 9, 8, 7, 7, 7, 6, 6, 5, 4, 3, 2, 1, 1, 1, 2, 2, 2,
				2, 3, 4, 5);
		for (int i = 0; i <listOfX.size(); i++) {
			matrix[listOfY.get(i)][listOfX.get(i)] = new Path(listOfX.get(i), listOfY.get(i));
		}
		List<Integer> listOfX2 = List.of(2, 2, 2, 2, 2 , 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5,5, 6, 6,
				6, 6, 6, 6, 6, 7, 7, 7, 7, 7,7, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9,9);

		List<Integer> listOfY2 = List.of(1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 1, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 3, 6, 0,
				3, 6, 8, 9, 10, 11, 0, 2, 3, 4, 5,8, 8, 0, 7, 0, 1, 2, 3, 4, 5, 6,7);
		for (int i = 0; i <listOfX2.size()-1; i++) {
			matrix[listOfY2.get(i)][listOfX2.get(i)] = new Field(listOfX2.get(i), listOfY2.get(i));
		}
		//matrix[7][3].addMonster(new Slime(3,7,13.0,2.0));
		
		
	}

	/**
	 * Unselects the cell (whether they is a selected cell or not).
	 */
	public void unselect() {
		selected = null;
	}

	/**
	 * Updates the data contained in the GameData.
	 */
	public void updateData() {
		// update (attention traitement different si des cases sont
		// selectionnées ou non...)
	}
}
