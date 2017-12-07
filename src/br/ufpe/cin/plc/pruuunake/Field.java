package br.ufpe.cin.plc.pruuunake;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Field {

	private char[][] data;
	private Lock lock;

	public Field() {
		this.lock = new ReentrantLock();

		initializeField();
	}

	public char[][] getData() {
		return this.data;
	}

	public Lock getLock() {
		return this.lock;
	}

	public void setData(char[][] data) {
		this.data = data;
	}

	private void initializeField() {
		lock.lock();

		int size = Pruuunake.SIZE;

		this.data = new char[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				data[i][j] = ' ';
			}
		}

		lock.unlock();
	}

}
