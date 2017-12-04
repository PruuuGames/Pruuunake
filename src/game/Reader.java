package game;

import java.io.DataInputStream;
import java.io.IOException;

public class Reader implements Runnable {

	private int bx;
	private int by;
	private char[][] field;

	private DataInputStream dis;

	public Reader(int bx, int by, char[][] field, DataInputStream dis) {
		this.bx = bx;
		this.by = by;
		this.field = field;

		this.field[bx][by] = 'B';

		this.dis = dis;
	}

	@Override
	public void run() {
		try {
			int move;
			while ((move = dis.readInt()) != -1) {
				synchronized (field) {
					if (move == 1) {
						bx++;
					} else if (move == 2) {
						bx--;
					} else if (move == 3) {
						by++;
					} else if (move == 4) {
						by--;
					}

					field[bx][by] = 'B';
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
