package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.locks.Lock;

public class ClientReader implements Reader {

	private Pruuunake instance;

	private ClientWriter clientWriter;

	private ObjectInputStream ois;

	public ClientReader(InputStream is) {
		this.instance = Pruuunake.getInstance();

		Writer writer = instance.getWriter();
		this.clientWriter = (ClientWriter) writer;

		try {
			this.ois = new ObjectInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			char[][] data;
			while ((data = (char[][]) ois.readObject()) != null) {
				Field field = instance.getField();

				Lock lock = field.getLock();

				lock.lock();

				System.out.println();
				System.out.println();
				for (int i = 0; i < data.length; ++i) {
					for (int j = 0; j < data.length; ++j) {
						System.out.printf("%c%s", data[i][j] == ' ' ? '#' : data[i][j], j < data.length - 1 ? ' ' : '\n');
					}
				}

				field.setData(data);

				lock.unlock();

				clientWriter.clearQueue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
