package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private JLabel score;
	private JTextField angle;
	private JTextArea challenge;

	public HUD(Game game) {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.game = game;
		scorePanel = scorePanel();
		add(scorePanel);

		challengePanel = challengePanel();
		add(challengePanel);

		anglePanel = anglePanel();
		add(anglePanel);
	}

	public JPanel scorePanel() {
		JPanel panel = new JPanel();
		String pScore = Integer.toString(game.getPlayer().getScore());
		score = new JLabel();
		score.setText("Score: " + pScore);
		panel.add(score);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}

	public JPanel challengePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Challenge Question: "));
		challenge = new JTextArea();
		challenge.setLineWrap(true);
		challenge.setEditable(false);
		challenge.setFont(new Font("SansSerif", Font.BOLD, 20));
		panel.add(challenge);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}

	public JPanel anglePanel() {
		JLabel label = new JLabel("Enter the new Barrel Angle: ");
		JPanel panel = new JPanel();
		angle = new JTextField(10);
		angle.setFont(new Font("SansSerif", Font.BOLD, 12));
		JButton submit = new JButton("Set Barrel Angle");
		// When the submit button is clicked, the barrel should move
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int newAng = Integer.parseInt(angle.getText());
				game.getPlayer().setBarrelAngle(newAng);
			}
		});
		panel.add(label);
		panel.add(angle);
		panel.add(submit);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
}