import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private int posX = -50;
	private int posY = -50;
	
	public void paintComponent(Graphics graph) {
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		graph.setColor(Color.RED);
		graph.fillOval(posX, posY, 50, 50);	 
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
