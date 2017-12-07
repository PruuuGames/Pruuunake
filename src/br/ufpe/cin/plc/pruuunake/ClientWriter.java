package br.ufpe.cin.plc.pruuunake;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientWriter implements Writer {

	private DataOutputStream dos;

	private BlockingQueue<Integer> queue;

	public ClientWriter(OutputStream os) {
		this.dos = new DataOutputStream(os);

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

	public void queueTurn(int keyCode) {
		try {
			queue.put(keyCode);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearQueue() {
		queue.clear();
	}

}
