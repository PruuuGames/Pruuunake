package br.ufpe.cin.plc.pruuunake;

import java.io.DataInputStream;
import java.io.IOException;

public class Reader implements Runnable {

	private Snake player;

	private char[][] field;

	private DataInputStream dis;

	public Reader(Snake player, char[][] field, DataInputStream dis) {
		this.player = player;

		this.field = field;

		this.dis = dis;
	}

	@Override
	public void run() {
		try {
			int move;
			while ((move = dis.readInt()) != -1) {
				if (move == 0) {
					synchronized (field) {
						field[dis.readInt()][dis.readInt()] = 'X';
					}
				} else {
					player.turn(Direction.fromCode(move));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
