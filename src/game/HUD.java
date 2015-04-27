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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HUD extends JPanel {
	public final static int WIDTH = 50;
	public final static int HEIGHT = 200;

	private Game game;
	private JPanel challengePanel, anglePanel;
	private JLabel score;
	private JTextField angle;
	private JTextArea challenge;

	public HUD(Game game) {
		this.game = game;
		setLayout(new GridLayout(0,1));
		setMaximumSize(new Dimension(25, 500));
		
		score = new JLabel("Score: 0");
		add(score);

		challengePanel = challengePanel();
		
		add(challengePanel);

		anglePanel = anglePanel();
		add(anglePanel); 
		
	}
	
	public void updateScore() {
		score.setText("Score: " + game.getPlayer().getScore());
	}

	public JPanel challengePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Challenge Question: "));
		challenge = new JTextArea();
		challenge.setLineWrap(true);
		challenge.setEditable(false);
		challenge.setPreferredSize(new Dimension(145,40));
		challenge.setFont(new Font("SansSerif", Font.BOLD, 12));		
		panel.add(challenge);
		return panel;
	}
	
	public void updateChallenge(String challengePrompt) {
		challenge.setText(challengePrompt);
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
					game.repaintGame();
				}
				game.getPlayer().setBarrelAngle(newAng);
			} 
			
		});  
		
		angle.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) { 
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					String input = angle.getText();
					int newAng = 0;
					if (input.length() > 0) {
						newAng = Integer.parseInt(angle.getText().trim());
						game.repaintGame();
					}
					game.getPlayer().setBarrelAngle(newAng);
				} 
				else if(key == KeyEvent.VK_SPACE) {
					game.playerShoots();
				}
				else if(key == KeyEvent.VK_UP) {		// increase barrel angle
					Player p = game.getPlayer();
					double theta = p.getAngle();
					p.setBarrelAngle(theta + 1);
					angle.setText(Integer.toString((int)theta));
				}
				else if(key == KeyEvent.VK_DOWN) {		// decrease barrel angle
					Player p = game.getPlayer();
					double theta = p.getAngle();
					p.setBarrelAngle(theta - 1);
					angle.setText(Integer.toString((int)theta));
				}
				game.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}  
			
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