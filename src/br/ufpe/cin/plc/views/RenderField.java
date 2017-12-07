package br.ufpe.cin.plc.views;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.locks.Lock;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import br.ufpe.cin.plc.pruuunake.Field;
import br.ufpe.cin.plc.pruuunake.Pruuunake;
import br.ufpe.cin.plc.pruuunake.Snake;

@SuppressWarnings("serial")
public class RenderField extends JPanel {

	private static final int BUTTON_WIDTH = 40;
	private static final int BUTTON_HEIGHT = 40;

	private static final String SCORE_RED = "RED current score is: ";
	private static final String SCORE_BLUE = "BLUE current score is: ";

	private int rows;
	private int columns;

	private JButton[][] field;

	private JLabel title;
	private JLabel scoreA;
	private JLabel scoreB;
	private JLabel pruuu;

	public RenderField() {
		super();

		this.rows = 20;
		this.columns = 20;

		this.field = new JButton[rows][columns];

		this.title = new JLabel("PRUUUNAKE");
		this.scoreA = new JLabel(SCORE_RED + 0);
		this.scoreB = new JLabel(SCORE_BLUE + 0);
		this.pruuu = new JLabel(new ImageIcon("src/br/ufpe/cin/plc/assets/pruuA.jpg"));

		add(new JLabel("What is your name"));
		setBounds(0, 0, 800, 800);
		setBackground(new Color(44, 62, 80));
		setLayout(null);

		initializeField();

		buildField();
	}

	public void initializeField() {
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				field[x][y] = new JButton();
				field[x][y].setBounds(x * BUTTON_WIDTH, y * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
				field[x][y].setEnabled(false);
				field[x][y].setRequestFocusEnabled(false);
				field[x][y].setBorder(emptyBorder);

				add(field[x][y]);
			}
		}

		title.setBounds(rows * BUTTON_WIDTH + 95, BUTTON_HEIGHT, 500, 100);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Verdana", Font.BOLD, 30));

		scoreA.setBounds(rows * BUTTON_WIDTH + 65, BUTTON_HEIGHT + 100, 500, 100);
		scoreA.setForeground(Color.WHITE);
		scoreA.setFont(new Font("Verdana", Font.BOLD, 20));

		scoreB.setBounds(rows * BUTTON_WIDTH + 65, BUTTON_HEIGHT + 200, 500, 100);
		scoreB.setForeground(Color.WHITE);
		scoreB.setFont(new Font("Verdana", Font.BOLD, 20));

		pruuu.setBounds(rows * BUTTON_WIDTH - 50, BUTTON_HEIGHT + 400, 500, 500);

		add(title);
		add(scoreA);
		add(scoreB);
		add(pruuu);
	}

	public void buildField() {
		Pruuunake pruuunake = Pruuunake.getInstance();
		Field field = pruuunake.getField();

		Lock lock = field.getLock();

		lock.lock();

		Snake player1 = pruuunake.getPlayer1();
		Snake player2 = pruuunake.getPlayer2();
		
		scoreA.setText(SCORE_RED + player1.getScore());
		scoreB.setText(SCORE_BLUE + player2.getScore());

		char[][] data = field.getData();

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (data[row][column] == ' ') {
					this.field[row][column].setBackground(Color.WHITE);

				} else if (data[row][column] == 'A') {
					this.field[row][column].setBackground(Color.RED);

				} else if (data[row][column] == 'B') {
					this.field[row][column].setBackground(Color.BLUE);

				} else if (data[row][column] == 'X') {
					this.field[row][column].setBackground(Color.GREEN);
				}
			}
		}

		lock.unlock();
	}

	public void updateScoreA(int score) {
		this.scoreA.setText("RED current score is: " + score);
	}

	public void updateScoreB(int score) {
		this.scoreA.setText("BLUE current score is: " + score);
	}
}
