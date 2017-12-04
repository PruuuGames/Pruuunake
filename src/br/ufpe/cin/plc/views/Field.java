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
		this.rows = 20;
		this.columns = 20;

		this.field = new JButton[rows][columns];

		setBackground(new Color(44, 62, 80));
		initializeField();
		buildField(field);
	}

	public void initializeField() {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				field[x][y] = new JButton();
				// field[x][y].setBounds(x * BUTTON_WIDTH, y * BUTTON_HEIGHT, 100, 100);
				field[x][y].setEnabled(false);
				add(field[x][y]);
				field[x][y].setRequestFocusEnabled(false);

			}
		}
	}

	public void buildField(char[][] field) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (field[row][column] == ' ') {
					this.field[row][column].setBackground(Color.WHITE);

				} else if (field[row][column] == 'A') {
					this.field[row][column].setBackground(Color.PINK);

				} else if (field[row][column] == 'B') {
					this.field[row][column].setBackground(Color.BLUE);

				} else if (field[row][column] == 'a') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pruuA.jpg"));

				} else if (field[row][column] == 'b') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pruuB.jpg"));

				} else if (field[row][column] == 'X') {
					this.field[row][column].setIcon(new ImageIcon("src/br/ufpe/cin/pizza.png"));
				}
			}
		}
	}

}
