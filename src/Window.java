import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{

	private Panel pan = new Panel();
	
	public Window() {
		this.setTitle("Interesting window");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(pan);
		this.setVisible(true);
		Go();
	}
	
	private void Go() {
		while(true) {
			int x = pan.getPosX();
			int y = pan.getPosY();		
			
			//Initialise the booleans
			Boolean backX = true;
			Boolean backY = false;
			
			/* When the ball is going forward backX is true 
			 * When the ball is going backward backX is false */
			if(x < 1) backX = true;
			if(x > pan.getWidth() - 50) backX = false;
			
			//Same for backY
			if(y < 1) backY = true;
			if(y > pan.getHeight() - 50) backY = false;
			
			/* When backX is true the ball is going forward
			 * When backX is false the ball is going backward 
			 * Which in both case mean incrementing or decrementing the position*/
			if(backX == true) {
				pan.setPosX(++x);
			}
			else {
				pan.setPosX(--x);
			}
			
			//Same for backY
			if(backY == true) {
				pan.setPosY(++y);
			}
			else {
				pan.setPosY(--y);
			}
			
			//We re-draw our panel
			pan.repaint();
			
			//And we don't forget to slow the process downS
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
