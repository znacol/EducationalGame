package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class HUD extends JPanel {
	public final static int WIDTH = 50;
	public final static int HEIGHT = 200;

	private Game game;
	private JPanel scorePanel, challengePanel, anglePanel;
	private JTextField angle;
	private JTextArea display;



	public HUD(Game game) {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//setPreferredSize(new Dimension())

		this.game = game;
		JPanel scorePanel = scorePanel();
		add(scorePanel);

		JPanel challengePanel = challengePanel();
		add(challengePanel);

		JPanel anglePanel = anglePanel();
		add(anglePanel);
	}

	public JPanel scorePanel() {
		//setPreferredSize(new Dimension(100, 300));
		JPanel panel = new JPanel();
		display = new JTextArea();
		display.setLineWrap(true);
		display.setEditable(false);
		display.setFont(new Font("SansSerif", Font.BOLD, 20));
		display.setText("Score: " + "\n" + Integer.toString(game.getPlayer().getScore()));
		panel.add(display);
		return panel;

	}

	public JPanel challengePanel() {
		//setPreferredSize(new Dimension(100, 400));
		JPanel panel = new JPanel();
		display = new JTextArea();
		display.setLineWrap(true);
		display.setEditable(false);
		display.setFont(new Font("SansSerif", Font.BOLD, 20));
		display.setText("Challenge Question!");
		panel.add(display);
		return panel;
	}

	public JPanel anglePanel() {
		JTextArea enterAngle = new JTextArea(50,300);
		JPanel panel = new JPanel();

	//	enterAngle = new JTextArea();
	//	enterAngle.setLineWrap(true);
	//	enterAngle.setEditable(false);
	//	display.setWrapStyleWord(true);
	//	enterAngle.setFont(new Font("SansSerif", Font.BOLD, 20));
	//	enterAngle.setText("Enter angle: ");
	//	panel.add(enterAngle);
		
		angle = new JTextField();
		angle.setPreferredSize(new Dimension(100, 100));
		angle.setFont(new Font("SansSerif", Font.BOLD, 12));
		angle.addFocusListener(new NameListener());
		panel.add(angle);
		return panel;
	}
	
	private class NameListener implements FocusListener {
		public void focusGained(FocusEvent e){
			angle.setText(angle.getText());
		}
		public void focusLost(FocusEvent e) {
			angle.setText(angle.getText());
		}
	}
	



}
