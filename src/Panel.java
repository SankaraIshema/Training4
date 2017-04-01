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
	private String form = "Circle";
	
	public void paintComponent(Graphics graph) {
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		graph.setColor(Color.RED);
		draw(graph);
	}
	
	public void draw(Graphics graph) {
		switch(form) {
		case "Circle" :
			graph.fillOval(posX, posY, 50, 50);
			break;
			
		case "Square" :
			graph.fillRect(posX, posY, 50, 50);
			break;
			
		case "Triangle" :
			int firstPointX  = posX + 25;
			int firstPointY  = posY;
			
			int secondPointX = posX + 50;
			int secondPointY = posY + 50;
			
			int thirdPointX  = posX;
			int thirdPointY  = posY + 50;
			
			int[] pointsX = {firstPointX, secondPointX, thirdPointX};
			int[] pointsY = {firstPointY, secondPointY, thirdPointY};
			
			graph.fillPolygon(pointsX, pointsY, 3);
			break;
			
		case "Star" :
			  int s1X = posX + 25;
		      int s1Y = posY;
		      
		      int s2X = posX + 50;
		      int s2Y = posY + 50; 
		      
		      graph.drawLine(s1X, s1Y, s2X, s2Y);  
		      
		      int s3X = posX;
		      int s3Y = posY + 17;
		      
		      graph.drawLine(s2X, s2Y, s3X, s3Y); 
		      
		      int s4X = posX + 50;
		      int s4Y = posY + 17;
		      
		      graph.drawLine(s3X, s3Y, s4X, s4Y);      
		      
		      int s5X = posX;
		      int s5Y = posY + 50;
		      
		      graph.drawLine(s4X, s4Y, s5X, s5Y);       
		      graph.drawLine(s5X, s5Y, s1X, s1Y); 
		}
	}
	

	public void setForm(String form) {
		this.form = form;
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
