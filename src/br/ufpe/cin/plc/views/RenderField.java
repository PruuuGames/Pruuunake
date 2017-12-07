package br.ufpe.cin.plc.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.concurrent.locks.Lock;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufpe.cin.plc.pruuunake.Field;
import br.ufpe.cin.plc.pruuunake.Pruuunake;
import br.ufpe.cin.plc.pruuunake.Snake;

@SuppressWarnings("serial")
public class RenderField extends JPanel {

	private static final int BUTTON_WIDTH = 800 / Pruuunake.SIZE;
	private static final int BUTTON_HEIGHT = 800 / Pruuunake.SIZE;

	private static final String SCORE_RED = "RED current score is: ";
	private static final String SCORE_BLUE = "BLUE current score is: ";

	private int size;

	private JLabel[][] field;
	
	private Pruuunake instance;

	private JLabel title;
	private JLabel scoreA;
	private JLabel scoreB;
	private JLabel pruuu;

	public RenderField() {
		super();

		this.size = Pruuunake.SIZE;

		this.field = new JLabel[size][size];
		
		this.instance = Pruuunake.getInstance();
		
		this.title = new JLabel("PRUUUNAKE");
		this.scoreA = new JLabel(SCORE_RED + 0);
		this.scoreB = new JLabel(SCORE_BLUE + 0);
		this.pruuu = new JLabel(new ImageIcon("src/br/ufpe/cin/plc/assets/pruuA.png"));

		
		setBounds(0, 0, 800, 800);		
		
		setBackground(new Color(44, 62, 80));
		
		setLayout(null);

		initializeField();

		buildField();
	}

	public void initializeField() {
		title.setBounds(size * BUTTON_WIDTH + 95, BUTTON_HEIGHT, 500, 100);
		if(instance.isHost()) {
			title.setForeground(Color.RED);
		} else {
			title.setForeground(Color.BLUE);
		}
		title.setFont(new Font("Verdana", Font.BOLD, 30));

		scoreA.setBounds(size * BUTTON_WIDTH + 65, BUTTON_HEIGHT + 100, 500, 100);
		scoreA.setForeground(Color.WHITE);
		scoreA.setFont(new Font("Verdana", Font.BOLD, 20));

		scoreB.setBounds(size * BUTTON_WIDTH + 65, BUTTON_HEIGHT + 200, 500, 100);
		scoreB.setForeground(Color.WHITE);
		scoreB.setFont(new Font("Verdana", Font.BOLD, 20));

		pruuu.setBounds(size * BUTTON_WIDTH - 42, BUTTON_HEIGHT + 400, 500, 500);

		add(title);
		add(scoreA);
		add(scoreB);
		add(pruuu);

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				field[x][y] = new JLabel("");
				field[x][y].setBounds(x * BUTTON_WIDTH, y * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
				field[x][y].setBackground(Color.WHITE);
				field[x][y].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
				field[x][y].setEnabled(true);
				field[x][y].setOpaque(true);

				add(field[x][y]);
			}
		}
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

		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				if (data[row][column] == ' ') {
					this.field[row][column].setBackground(Color.WHITE);

				} else if (data[row][column] == 'A') {
					this.field[row][column].setBackground(Color.RED);

				} else if (data[row][column] == 'B') {
					this.field[row][column].setBackground(Color.BLUE);

				} else if (data[row][column] == 'X') {
					this.field[row][column].setIcon(new ImageIcon(new ImageIcon("src/br/ufpe/cin/plc/assets/pizza.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT)));
				}
			}
		}

		lock.unlock();
	}
}
