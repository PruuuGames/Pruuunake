package br.ufpe.cin.plc.pruuunake;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class ServerReader implements Reader {

	private Snake player2;

	private DataInputStream dis;

	public ServerReader(InputStream is) {
		this.player2 = Pruuunake.getInstance().getPlayer2();

		this.dis = new DataInputStream(is);
	}

	@Override
	public void run() {
		try {
			int move;
			while ((move = dis.readInt()) != -1) {
				player2.turn(move);
			}
		} catch (SocketException e) {
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
