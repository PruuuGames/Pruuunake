package br.ufpe.cin.plc.pruuunake;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pizzeria implements Runnable {

	private static final Random RANDOM = new Random();

	private Field field;

	private Point pizza;

	private Lock lock;

	public Pizzeria() {
		this.field = Pruuunake.getInstance().getField();

		this.pizza = null;

		this.lock = new ReentrantLock();
	}

	public Lock getLock() {
		return this.lock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Lock fieldLock = field.getLock();

				boolean l1 = fieldLock.tryLock();
				boolean l2 = lock.tryLock();

				while (!l1 || !l2) {
					if (l1) {
						fieldLock.unlock();
					}

					if (l2) {
						lock.unlock();
					}

					l1 = fieldLock.tryLock();
					l2 = lock.tryLock();
				}

				char[][] data = field.getData();

				if (pizza == null) {
					int x, y;

					while (true) {
						x = RANDOM.nextInt(Pruuunake.SIZE);
						y = RANDOM.nextInt(Pruuunake.SIZE);

						if (data[x][y] == ' ') {
							break;
						}
					}

					pizza = new Point(x, y);
				}

				data[pizza.getX()][pizza.getY()] = 'X';

				fieldLock.unlock();
				lock.unlock();

				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
