package br.ufpe.cin.plc.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import br.ufpe.cin.plc.pruuunake.Direction;
import br.ufpe.cin.plc.pruuunake.Pruuunake;

public class GameFrame implements ActionListener, KeyListener {

	private static final int HEIGHT = 829;
	private static final int WIDTH = 805;
	private static final int FIELD_SIZE_X = 20;
	private static final int FIELD_SIZE_Y = 20;

	private static GameFrame INSTANCE;

	public static GameFrame getInstance() {
		return INSTANCE;
	}

	public static void main(String[] args) {
		INSTANCE = new GameFrame();
	}

	private char[][] field;

	public char[][] getField() {
		return this.field;
	}

	public void setField(char[][] field) {
		this.field = field;
	}

	public static GameFrame snake;

	public Pruuunake pruuunake;

	public JFrame mainPanel;

	public Field render;

	public Timer timer = new Timer(20, this);

	public GameFrame() {
		pruuunake = new Pruuunake(this);

		field = new char[FIELD_SIZE_X][FIELD_SIZE_Y];
		for (int x = 0; x < FIELD_SIZE_X; x++) {
			for (int y = 0; y < FIELD_SIZE_X; y++) {
				field[x][y] = ' ';
			}
		}
		mainPanel = new JFrame("GameFrame");
		mainPanel.setVisible(true);
		mainPanel.setSize(WIDTH, HEIGHT);
		mainPanel.setResizable(false);
		mainPanel.setLocationRelativeTo(null);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.addKeyListener(this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainPanel.setContentPane(render = new Field(field));
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		Direction direction = pruuunake.getPlayerDirection();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != Direction.DOWN) {
			pruuunake.turn(Direction.UP);
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != Direction.UP) {
			pruuunake.turn(Direction.DOWN);
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != Direction.RIGHT) {
			pruuunake.turn(Direction.LEFT);
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != Direction.LEFT) {
			pruuunake.turn(Direction.RIGHT);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
