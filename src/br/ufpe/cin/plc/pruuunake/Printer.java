package br.ufpe.cin.plc.pruuunake;

public class Printer implements Runnable {

	private char[][] field;

	public Printer(char[][] field) {
		this.field = field;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (field) {
				for (int i = 0; i < field.length; ++i) {
					for (int j = 0; j < field[i].length; ++j) {
						System.out.printf("%c%c", field[i][j], j < field[i].length - 1 ? ' ' : '\n');
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
