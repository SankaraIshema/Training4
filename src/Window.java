import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {

	private Panel pan = new Panel();
	private JPanel motherPane = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JButton button1 = new JButton("Go");
	private JButton button2 = new JButton("Stop");
	private JLabel label = new JLabel("Nice Label");
	private boolean greenLight = true;
	
	public Window() {
		this.setTitle("Interesting window");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		motherPane.setBackground(Color.WHITE);
		motherPane.setLayout(new BorderLayout());
		motherPane.add(pan, BorderLayout.CENTER);

		buttonPane.add(button1);
		buttonPane.add(button2);
		motherPane.add(buttonPane, BorderLayout.SOUTH);
		
		Font policy = new Font("Tahoma", Font.BOLD, 16);
		label.setFont(policy);
		label.setForeground(Color.BLUE);
		label.setHorizontalAlignment(JLabel.CENTER);
		motherPane.add(label, BorderLayout.NORTH);
		
		button1.addActionListener(new ButtonListener1());
		button1.setEnabled(false);
		button2.addActionListener(new ButtonListener2());
		
		this.setContentPane(motherPane);
		this.setVisible(true);
		Go();
	}
	
	private void Go() {
		//Starting position of our red ball
		int x = pan.getPosX();
		int y = pan.getPosY();		
		
		//Initialise the booleans
		Boolean backX = true;
		Boolean backY = true;
		
		while(this.greenLight) {
			
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
			if(backX) {
				pan.setPosX(++x);
			}
			else {
				pan.setPosX(--x);
			}
			
			//Same for backY
			if(backY) {
				pan.setPosY(++y);
			}
			else {
				pan.setPosY(--y);
			}
			
			//We re-draw our panel
			pan.repaint();
			
			//And we don't forget to slow the process down
			try {
				Thread.sleep(3);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	class ButtonListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			greenLight = true;
			button1.setEnabled(false);
			button2.setEnabled(true);
			Go();
		}	
	}
	
	class ButtonListener2 implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			greenLight = false;
			button1.setEnabled(true);
			button2.setEnabled(false);
				
		}
	}
}
