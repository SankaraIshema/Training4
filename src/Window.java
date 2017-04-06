import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class Window extends JFrame {

	private Panel pan           = new Panel();
	private JPanel motherPane   = new JPanel();
	private JPanel buttonPane   = new JPanel();
	private JPanel topPane      = new JPanel();
	private JButton button1     = new JButton("Go");
	private JButton button2     = new JButton("Stop");
	
	private Thread threadGo;
	private boolean greenLight = true;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menuMorph  = new JMenu("Morphing");

	private JMenu animation  = new JMenu("Animation");
	private JMenuItem launch = new JMenuItem("Launch");
	private JMenuItem stop   = new JMenuItem("Stop");
	private JMenuItem close  = new JMenuItem("Close");
	
	private JMenu form       = new JMenu("Form");
	private JMenu formType   = new JMenu("Shape");
	private JRadioButtonMenuItem radioButtonCircle   = new JRadioButtonMenuItem("Circle");
	private JRadioButtonMenuItem radioButtonSquare   = new JRadioButtonMenuItem("Square");
	private JRadioButtonMenuItem radioButtonTriangle = new JRadioButtonMenuItem("Triangle");
	private JRadioButtonMenuItem radioButtonStar     = new JRadioButtonMenuItem("Star");
	
	private JCheckBoxMenuItem morphing = new JCheckBoxMenuItem("Morphing");
	
	private JMenu about               = new JMenu("About");
	private JMenuItem aboutAbout      = new JMenuItem("?");
	
	private JPopupMenu rightClickMenu = new JPopupMenu();
	
	private JMenu objectColor     = new JMenu("Color");
	private JMenuItem red   = new JMenuItem("Red");
	private JMenuItem blue  = new JMenuItem("Blue");
	private JMenuItem green = new JMenuItem("Green");
	
	private JMenu backgroundColor = new JMenu("Background Color");
	private JMenuItem backgroundRed   = new JMenuItem("Red");
	private JMenuItem backgroundBlue  = new JMenuItem("Blue");
	private JMenuItem backgroundGreen = new JMenuItem("Green");

	public Window() {
		
		// We set the parameters of our window
		this.setTitle("Interesting window");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					red.addActionListener(new ObjectColorListener());
					blue.addActionListener(new ObjectColorListener());
					green.addActionListener(new ObjectColorListener());
					
					objectColor.add(red);
					objectColor.add(blue);
					objectColor.add(green);
					
					backgroundRed.addActionListener(new BackgroundColorListener());
					backgroundBlue.addActionListener(new BackgroundColorListener());
					backgroundGreen.addActionListener(new BackgroundColorListener());
					
					backgroundColor.add(backgroundRed);
					backgroundColor.add(backgroundBlue);
					backgroundColor.add(backgroundGreen);
					
					rightClickMenu.add(launch);
					rightClickMenu.add(stop);
					rightClickMenu.add(objectColor);
					rightClickMenu.add(backgroundColor);
					
					rightClickMenu.show(pan, e.getX(), e.getY());
				}
			}
		});
		
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
		
		this.initMenuBar();
		this.setVisible(true);
		
		/* And we don't forget to launch the Go() method 
		 * developed just below
		 */
		go();
	}
	
	private void initMenuBar() {
		launch.addActionListener(new ButtonListener1());
		launch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		animation.add(launch);
		
		stop.addActionListener(new ButtonListener2());
		stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK 
																  + KeyEvent.SHIFT_DOWN_MASK));
		animation.add(stop);
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		animation.add(close);
		
		form.add(formType);
		
		ButtonGroup formButtonGroup = new ButtonGroup();
		formButtonGroup.add(radioButtonCircle);
		formButtonGroup.add(radioButtonSquare);
		formButtonGroup.add(radioButtonTriangle);
		formButtonGroup.add(radioButtonStar);
		
		formType.add(radioButtonCircle);
		formType.add(radioButtonSquare);
		formType.add(radioButtonTriangle);
		formType.add(radioButtonStar);
		radioButtonCircle.setSelected(true);
		
		radioButtonCircle.   addActionListener(new FormListener());
		radioButtonSquare.   addActionListener(new FormListener());
		radioButtonTriangle. addActionListener(new FormListener());
		radioButtonStar.     addActionListener(new FormListener());
		
		form.addSeparator();
		morphing.addActionListener(new MorphListener());
		form.add(morphing);
		
		about.add(aboutAbout);
		
		animation.setMnemonic('A');
		menuBar.add(animation);
		
		form.setMnemonic('F');
		menuBar.add(form);
		
		about.setMnemonic('I');
		menuBar.add(about);
		
		setJMenuBar(menuBar);
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
			if(!pan.isMorph()) {
				/* When the ball is going forward backX is true 
				 * When the ball is going backward backX is false */
				if(x < 1) backX = true;
				if(x > pan.getWidth() - 50) backX = false;
				
				//Same for backY
				if(y < 1) backY = true;
				if(y > pan.getHeight() - 50) backY = false;
			}
			else {
				if(x < 1) backX = true;
				if(x > pan.getWidth() - pan.getDrawSize()) backX = false;
				
				if(y < 1) backY = true;
				if(y > pan.getHeight() - pan.getDrawSize()) backY = false;
			}
			
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
				Thread.sleep(5);
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
			int option = JOptionPane.showConfirmDialog(null, "Do you want to relaunch the application?", 
																"Launch the App", JOptionPane.YES_NO_OPTION, 
																					JOptionPane.QUESTION_MESSAGE);
			
			
			/* By clicking our Go button we send the infinite loop
			 * to infinity and beyond. Meaning we start the red ball again.
			 * To do that we have to set greenLight to true.
			 */
			if(option == JOptionPane.OK_OPTION) {
				greenLight = true;
				
				// We disable the Go button and enable the Stop button
				launch.setEnabled(false);
				stop.setEnabled(true);
				
				/* And we kick-start the Go() method again 
				 * by using a new Thread each time we push the Go button.
				 * For this we use a custom made thread that will take upon itself
				 * to launch the go() method. This thread is defined below.
				 */
				threadGo = new Thread(new CustomRunnable());
				threadGo.start();
			}
		}	
	}
	
	// The class that listen to our Stop button (button2) and implements ActionListener
	class ButtonListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "Do you want to stop the application?", "Stop the App", 
																JOptionPane.YES_NO_CANCEL_OPTION, 
																	JOptionPane.QUESTION_MESSAGE);
			
			/* By clicking our Stop button we stop the infinite loop.
			 * We do so by setting greenLight to false.
			 */
			if(option == JOptionPane.YES_OPTION) {
				greenLight = false;
				
				// We enable the Go button and disable the Stop button
				launch.setEnabled(true);
				stop.setEnabled(false);
			}	
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
			pan.setForm(((JRadioButtonMenuItem)arg0.getSource()).getText());
		}
	}	
	
	class MorphListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(morphing.isSelected()) {
				pan.setMorph(true);
			}
			else {
				pan.setMorph(false);
			}
		}	
	}
	
	class ObjectColorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch(((JMenuItem)arg0.getSource()).getText()) {
			case "Red" :
				pan.setObjectColor(Color.RED);
				break;
				
			case "Blue" :
				pan.setObjectColor(Color.BLUE);
				break;
				
			case "Green" :
				pan.setObjectColor(Color.GREEN);
				break;
				
			default :
				pan.setObjectColor(Color.GRAY);
				break;
			}
		}
	}
	
	class BackgroundColorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((JMenuItem)e.getSource()).getText()) {
			case "Red" :
				pan.setBackgroundColor(Color.RED);
				break;
				
			case "Blue" :
				pan.setBackgroundColor(Color.BLUE);
				break;
				
			case "Green" :
				pan.setBackgroundColor(Color.GREEN);
				break;
				
			default :
				pan.setBackgroundColor(Color.GRAY);
				break;
			}
		}
	}
}
