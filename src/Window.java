import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {

	private Panel pan           = new Panel();
	private JPanel motherPane   = new JPanel();
	private JPanel buttonPane   = new JPanel();
	private JPanel topPane      = new JPanel();
	private JButton button1     = new JButton("Go");
	private JButton button2     = new JButton("Stop");
	private JLabel label        = new JLabel("Form Options");
	
	private boolean greenLight = true;
	private Thread threadGo;
	private String[] optionsTab = {"Circle", "Square", "Triangle", "Star", };
	private JComboBox<String> combo = new JComboBox<>(optionsTab);
	
	public Window() {
		
		
		// We set the parameters of our window
		this.setTitle("Interesting window");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* We give backgroundColor, layout and the panel containing our ball
		 * to our mother panel
		 */
		motherPane.setBackground(Color.WHITE);
		motherPane.setLayout(new BorderLayout());
		motherPane.add(pan, BorderLayout.CENTER);
		
		// We add the our two buttons to our button panel
		buttonPane.add(button1);
		buttonPane.add(button2);
		motherPane.add(buttonPane, BorderLayout.SOUTH);
		
		// We set up the parameters of our JLabel object and we add it to the top panel
		Font policy = new Font("Tahoma", Font.BOLD, 16);
		label.setFont(policy);
		label.setForeground(Color.BLUE);
		topPane.add(label);
		
		/* We set up our combo-box, add an ActionListner, add it to the top-pane
		 * and and the whole shabbang to the mother-pane.
		 */
		combo.setForeground(Color.BLUE);
		combo.setPreferredSize(new Dimension(100, 20));
		combo.addActionListener(new FormListener());
		topPane.add(combo);
		
		motherPane.add(topPane, BorderLayout.NORTH);
		
		
		
		/* We add ActionListener to our buttons.
		 * Each button has a class designed for handling the specific action
		 * that will start when the user clicks it.
		 * Button1 is spied on by the class ButtonListener1
		 * Button2 is spied on by the class ButtonListener2
		 */
		button1.addActionListener(new ButtonListener1());
		button2.addActionListener(new ButtonListener2());
		
		/* Since the red ball will launch itself automatically at instantiation
		 * of a Window object, we want the Go button to be disabled
		 * while the button Stop is.
		 */
		button1.setEnabled(false);
		
		// We ask our window to make our mother pane the main panel
		this.setContentPane(motherPane);
		
		/* And because we are selfless Humanitarians Priests of Perfection
		 * praying for the Illumination of our fellow humans,
		 * we shall unveil the ultimate personification of Beauty that we created.
		 * Selflessly.
		 */
		this.setVisible(true);
		
		/* And we don't forget to launch the Go() method 
		 * developed just below
		 */
		go();
	}
	
		private void go() {
		//Starting position of our red ball
		int x = pan.getPosX();
		int y = pan.getPosY();		
		
		//Initialise the booleans
		Boolean backX = true;
		Boolean backY = true;
		
		// As long as greenLight is true, the infinite loop will never stop
		while(greenLight) {
			
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
	
	// The class that listen to our Go button (button1) and implements ActionListener
	class ButtonListener1 implements ActionListener {
		
		// Method of ActionListener that we have to override
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/* By clicking our Go button we send the infinite loop
			 * to infinity and beyond. Meaning we start the red ball again.
			 * To do that we have to set greenLight to true.
			 */
			greenLight = true;
			
			// We disable the Go button and enable the Stop button
			button1.setEnabled(false);
			button2.setEnabled(true);
			
			/* And we kick-start the Go() method again 
			 * by using a new Thread each time we push the Go button.
			 * For this we use a custom made thread that will take upon itself
			 * to launch the go() method. This thread is defined below.
			 */
			threadGo = new Thread(new CustomRunnable());
			threadGo.start();
		}	
	}
	
	// The class that listen to our Stop button (button2) and implements ActionListener
	class ButtonListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/* By clicking our Stop button we stop the infinite loop.
			 * We do so by setting greenLight to false.
			 */
			greenLight = false;
			
			// We enable the Go button and disable the Stop button
			button1.setEnabled(true);
			button2.setEnabled(false);
				
		}
	}
	
	// Our custom made Thread class that implements Runnable.
	class CustomRunnable implements Runnable {
		
		/* By implementing Runnable we have to override it's method run().
		 * An opportunity we will take to launch the go() method.
		 */
		@Override
		public void run() {
			go();	
		}
	}
	
	// Our Custom made ActionListener
	class FormListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			pan.setForm(combo.getSelectedItem().toString());
		}
	}	
}
