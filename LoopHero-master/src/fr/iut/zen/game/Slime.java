package fr.iut.zen.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Slime implements Monster {
	private int X;
	private int Y;
	private double life;
	private double attack;
	
	public Slime(int X, int Y, double life, double attack) {
		this.X = X;
		this.Y = Y;
		this.life = life;
		this.attack = attack;
	}
	
	public void draw(Graphics2D graphics, int column, int line) {
		graphics.setColor(Color.GREEN);
		graphics.fill(new Rectangle2D.Float(column, line, 20, 20));

	}
}
