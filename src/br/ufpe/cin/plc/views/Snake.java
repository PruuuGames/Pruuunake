package br.ufpe.cin.plc.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener {

	private static final int HEIGHT = 800;
	private static final int WIDTH = 800;
	private static final int FIELD_SIZE_X = 20;
	private static final int FIELD_SIZE_Y = 20;

	private char[][] field;

	public char[][] getField() {
		return this.field;
	}

	public void setField(char[][] field) {
		this.field = field;
	}

	public static Snake snake;

	public JFrame mainPanel;

	public Field render;

	public Dimension dimension;

	public Timer timer = new Timer(20, this);

	public Snake() {
		field = new char[FIELD_SIZE_X][FIELD_SIZE_Y];
		for (int x = 0; x < FIELD_SIZE_X; x++) {
			for (int y = 0; y < FIELD_SIZE_X; y++) {
				field[x][y] = ' ';
			}
		}
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		mainPanel = new JFrame("Snake");
		mainPanel.setVisible(true);
		mainPanel.setSize(HEIGHT, WIDTH);
		mainPanel.setResizable(false);
		mainPanel.setLocation(dimension.width / 2 - WIDTH / 2, dimension.height / 2 - HEIGHT / 2);
		mainPanel.add(render = new Field(field));
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainPanel.removeAll();
		mainPanel.add(render = new Field(field));
	}

	public static void main(String[] args) {
		Snake snake = new Snake();
	}

}
