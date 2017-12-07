package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PruuuClient {

	private Socket socket;

	public PruuuClient(String ip) {
		try {
			this.socket = new Socket(ip, Pruuunake.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OutputStream getOutputStream() {
		try {
			OutputStream dos = socket.getOutputStream();

			return dos;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public InputStream getInputStream() {
		try {
			InputStream dis = socket.getInputStream();

			return dis;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
