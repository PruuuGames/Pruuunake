package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Lock;

public class ServerWriter implements Writer {

	private Pruuunake instance;

	private Field field;

	private int ticks;

	private ObjectOutputStream oos;

	public ServerWriter(OutputStream os) {
		this.instance = Pruuunake.getInstance();

		this.field = instance.getField();

		this.ticks = 0;

		try {
			this.oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Lock lock = field.getLock();

				if (ticks++ % 4 == 0) {
					Snake player1 = instance.getPlayer1();
					Snake player2 = instance.getPlayer2();

					instance.move(player1);
					instance.move(player2);
				}

				lock.lock();

				char[][] data = field.getData();

				int size = data.length;
				char[][] temp = new char[size][size];

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						temp[i][j] = data[i][j];
					}
				}

				oos.writeObject(temp);

				lock.unlock();

				Thread.sleep(10);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
