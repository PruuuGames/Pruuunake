package br.ufpe.cin.plc.views;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Field extends JPanel {

	private static final int BUTTON_WIDTH = 40;
	private static final int BUTTON_HEIGHT = 40;

	private int rows;
	private int columns;

	private JButton[][] field;

	public Field(char[][] field) {
		this.rows = field.length;
		this.columns = field[0].length;

		this.field = new JButton[rows][columns];

		initializeField(field);
		buildField(field);
	}

	public void initializeField(char[][] field) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				this.field[row][column] = new JButton();
				this.field[row][column].setBounds(row * BUTTON_WIDTH, column * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
				add(this.field[row][column]);
			}
		}
	}

	public void buildField(char[][] field) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (field[row][column] == ' ') {
					this.field[row][column].setBackground(Color.WHITE);

				} else if (field[row][column] == 'A') {
					this.field[row][column].setBackground(Color.BLUE);

				} else if (field[row][column] == 'B') {
					this.field[row][column].setBackground(Color.RED);

				} else if (field[row][column] == 'a') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pombo.jpg"));

				} else if (field[row][column] == 'b') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pombo.jpg"));

				} else if (field[row][column] == 'X') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pizza.png"));
				}
			}
		}
	}

}
