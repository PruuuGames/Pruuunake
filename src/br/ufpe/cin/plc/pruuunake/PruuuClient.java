package br.ufpe.cin.plc.pruuunake;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PruuuClient {

	private Socket socket;

	public PruuuClient(Socket socket) {
		this.socket = socket;
	}

	public DataOutputStream getOutputStream() {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			return dos;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public DataInputStream getInputStream() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			return dis;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
