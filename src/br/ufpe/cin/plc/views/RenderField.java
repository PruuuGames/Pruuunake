package br.ufpe.cin.plc.views;

import java.awt.Color;
import java.util.concurrent.locks.Lock;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufpe.cin.plc.pruuunake.Field;
import br.ufpe.cin.plc.pruuunake.Pruuunake;

@SuppressWarnings("serial")
public class RenderField extends JPanel {

	private static final int BUTTON_WIDTH = 40;
	private static final int BUTTON_HEIGHT = 40;

	private int rows;
	private int columns;

	private JButton[][] field;
	
	

	public RenderField() {
		super();

		this.rows = 20;
		this.columns = 20;

		this.field = new JButton[rows][columns];
		
		add( new JLabel("What is your name"));
		setBounds(0, 0, 800, 800);
		setBackground(new Color(44, 62, 80));
		setLayout(null);

		initializeField();
		
		
		
		buildField();
	}

	public void initializeField() {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				field[x][y] = new JButton();
				field[x][y].setBounds(x * BUTTON_WIDTH, y * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
				field[x][y].setEnabled(false);
				field[x][y].setRequestFocusEnabled(false);

				add(field[x][y]);
			}
		}
		
		JLabel label = new JLabel("TESTE");
		label.setBounds(900, 300, 100, 100);
		label.setBackground(Color.WHITE);
		add(label);
	}

	public void buildField() {
		Pruuunake pruuunake = Pruuunake.getInstance();
		Field field = pruuunake.getField();

		Lock lock = field.getLock();

		lock.lock();

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

}
