package br.ufpe.cin.plc.pruuunake;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Writer implements Runnable {

	private DataOutputStream dos;

	private BlockingQueue<Integer> queue;

	public Writer(DataOutputStream dos) {
		this.dos = dos;

		this.queue = new LinkedBlockingQueue<Integer>();
	}

	@Override
	public void run() {
		try {
			int move;
			while ((move = queue.take()) != -1) {
				dos.writeInt(move);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queueTurn(Direction direction) {
		try {
			queue.put(direction.getCode());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void queuePizza(int y, int x) {
		try {
			queue.put(0);
			queue.put(y);
			queue.put(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
