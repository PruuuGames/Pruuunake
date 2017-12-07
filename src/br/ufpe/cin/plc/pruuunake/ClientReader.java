package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.SocketException;
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
			FieldWrapper wrapper;
			while ((wrapper = (FieldWrapper) ois.readObject()) != null) {
				Field field = instance.getField();

				Lock lock = field.getLock();

				lock.lock();

				field.setData(wrapper.getData());

				Snake player1 = instance.getPlayer1();
				Snake player2 = instance.getPlayer2();

				player1.setScore(wrapper.getPlayer1Score());
				player2.setScore(wrapper.getPlayer2Score());

				lock.unlock();

				clientWriter.clearQueue();
			}
		} catch (SocketException e) {
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
