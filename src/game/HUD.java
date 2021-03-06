package game;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class HUD extends JPanel {
	public final static int WIDTH = 50;
	public final static int HEIGHT = 200;

	private Game game;
	private JPanel challengePanel, anglePanel;
	private JLabel score;
	private JTextField angle;
	private JTextArea challenge; 
	private JTextField timeRemaining; 
	private String timeString = "Time Remaining: "; 
	private int timeLeft = 90; 
	private Timer countDownTimer;

	public HUD(Game game) {
		this.game = game;
		setLayout(new GridLayout(0,1));
		setMaximumSize(new Dimension(25, 500));
		
		score = new JLabel("Score: 0");
		add(score); 
		 
		countDownTimer = new Timer(1000, new TimeListener());  
		timeRemaining = timeRemainingLabel();
		add(timeRemaining);  
		countDownTimer.start();

		challengePanel = challengePanel();
		
		add(challengePanel);

		anglePanel = anglePanel();
		add(anglePanel);  
		
		
		
	}
	
	private JTextField timeRemainingLabel() {
		JTextField time = new JTextField(timeString + timeLeft); 
		time.setEditable(false);
		return time;
	} 
	
	private class TimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {  
			if (timeLeft <= 0) {
				countDownTimer.stop();
				String endMsg = "Game Over! " + "Score: "
						+ game.getPlayer().getScore();
				JOptionPane.showMessageDialog(null, endMsg); 
				System.exit(0); 
			} else {
				timeLeft -= 1;
				timeRemaining.setText(timeString + timeLeft);
			}
		} 
		
	}

	public void updateScore() {
		score.setText("Score: " + game.getPlayer().getScore());
	}

	public JPanel challengePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Challenge Question: "));
		challenge = new JTextArea();
		challenge.setLineWrap(true);
		challenge.setWrapStyleWord(true);
		challenge.setEditable(false);
		challenge.setPreferredSize(new Dimension(145,40));
		challenge.setFont(new Font("SansSerif", Font.BOLD, 12));		
		panel.add(challenge);
		return panel;
	}
	
	public void updateChallenge(String challengePrompt) {
		challenge.setText(challengePrompt);
	}
	
	public JTextField getAnglePanel(){
		angle.requestFocus();
		return angle;
	}

	public JPanel anglePanel() {
		JLabel label = new JLabel("Barrel Angle: ");
		JPanel panel = new JPanel();
		angle = new JTextField();
		angle.setMaximumSize(new Dimension(500,25));
		angle.setFont(new Font("SansSerif", Font.BOLD, 12));
		JButton setAngleButton = new JButton("Set Barrel Angle");
		// When the submit button is clicked, the barrel should move
		setAngleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String input = angle.getText();
				int newAng = 0;
				if(input.length() > 0) {
					newAng = Integer.parseInt(angle.getText());
				//	game.repaint();
				}
				game.getPlayer().setBarrelAngle(newAng);
				getAnglePanel().requestFocus();
			}
		});  
		
		angle.addKeyListener(new KeyListener() {
			// Should we make this KeyListener an actual class implemented by multiple JComponents so that it is more accessible?
			@Override
			public void keyPressed(KeyEvent e) { 
				int key = e.getKeyCode();
				Player p = game.getPlayer();
				if (key == KeyEvent.VK_ENTER) {
					String input = angle.getText();
					int newAng = 0;
					if (input.length() > 0) {
						newAng = Integer.parseInt(angle.getText().trim());
					}
					p.setBarrelAngle(newAng);
				} 
				else if(key == KeyEvent.VK_SPACE) {
					game.playerShoots();
					angle.setText(angle.getText().trim());
				}
				else if(key == KeyEvent.VK_UP) {		// increase barrel angle
					double theta = p.getAngle() + 1;
					p.setBarrelAngle(theta);
					angle.setText(Integer.toString((int)theta));
				}
				else if(key == KeyEvent.VK_DOWN) {		// decrease barrel angle
					double theta = p.getAngle() - 1;
					p.setBarrelAngle(theta);
					angle.setText(Integer.toString((int)theta));
				}
				game.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		}); 
		JButton shoot = new JButton("Shoot!");
		shoot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.playerShoots();
			}
			
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(label);
		panel.add(angle);
		panel.add(setAngleButton);
		panel.add(shoot);
		return panel;
	}
}