package br.ufpe.cin.plc.pruuunake;

import java.io.Serializable;

public class FieldWrapper implements Serializable {

	private static final long serialVersionUID = 5984281116604871035L;

	private char[][] data;

	private int player1Score;
	private int player2Score;

	public FieldWrapper(char[][] original) {
		Pruuunake instance = Pruuunake.getInstance();

		int size = original.length;

		this.data = new char[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				this.data[i][j] = original[i][j];
			}
		}

		this.player1Score = instance.getPlayer1().getScore();
		this.player2Score = instance.getPlayer2().getScore();
	}

	public char[][] getData() {
		return data;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

}
