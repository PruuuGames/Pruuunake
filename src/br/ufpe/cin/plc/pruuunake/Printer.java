package br.ufpe.cin.plc.pruuunake;

import br.ufpe.cin.plc.views.GameFrame;

public class Printer implements Runnable {

	private GameFrame gameFrame;

	private char[][] field;

	private Snake[] players;

	public Printer(GameFrame gameFrame, char[][] field, Snake... players) {
		this.gameFrame = gameFrame;

		this.field = field;

		this.players = players;
	}

	@Override
	public void run() {
		while (true) {
			for (Snake player : players) {
				player.move();
			}

			int size = Pruuunake.SIZE;

			char[][] temp = new char[size][size];

			synchronized (field) {
				for (int i = 0; i < size; ++i) {
					for (int j = 0; j < size; ++j) {
						temp[i][j] = field[i][j];
					}
				}
			}

			gameFrame.setField(temp);

			System.out.println(System.currentTimeMillis() - Pruuunake.time);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
