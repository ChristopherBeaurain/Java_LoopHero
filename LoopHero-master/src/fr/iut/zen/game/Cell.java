package fr.iut.zen.game;

import java.awt.Graphics2D;
public interface Cell {
	boolean isPath();
	boolean isField();
	boolean isBackground();
	boolean isCamp();
	void draw(Graphics2D graphics, int column, int line, int squareSize);
	
}
