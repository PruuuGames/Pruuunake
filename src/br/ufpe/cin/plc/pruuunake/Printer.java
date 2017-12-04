package br.ufpe.cin.plc.pruuunake;

public class Printer implements Runnable {

	private char[][] field;

	private Snake[] players;

	public Printer(char[][] field, Snake... players) {
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

			System.out.println();
			for (int i = 0; i < size + 2; ++i) {
				for (int j = 0; j < size + 2; ++j) {
					if ((i == 0 || i == size + 1) && j < size + 1) {
						if (j < size - 1) {
							System.out.print("##");
						}
					} else if (j == 0) {
						System.out.print("#");
					} else if (j == size + 1) {
						System.out.println("#");
					} else {
						System.out.printf("%c%s", temp[i - 1][j - 1], j < size - 2 ? " " : "");
					}
				}
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
