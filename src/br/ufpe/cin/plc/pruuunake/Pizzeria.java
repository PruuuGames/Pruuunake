package br.ufpe.cin.plc.pruuunake;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pizzeria implements Runnable {

	private static final Random RANDOM = new Random();

	private Writer writer;

	private AtomicBoolean pizza;

	private char[][] field;

	public Pizzeria(Writer writer, AtomicBoolean pizza, char[][] field) {
		this.writer = writer;

		this.pizza = pizza;

		this.field = field;
	}

	@Override
	public void run() {
		while (true) {
			if (pizza.get()) {
				continue;
			}

			int size = Pruuunake.SIZE;

			int x = -1;
			int y = -1;

			while (true) {
				x = RANDOM.nextInt(size);
				y = RANDOM.nextInt(size);

				synchronized (field) {
					if (field[y][x] == ' ') {
						break;
					}
				}
			}

			writer.queueMove(0);
			writer.queueMove(y);
			writer.queueMove(x);

			synchronized (field) {
				field[y][x] = 'X';
			}

			pizza.set(true);
		}
	}

}
