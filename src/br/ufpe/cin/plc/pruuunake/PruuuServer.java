package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PruuuServer {

	private ServerSocket serverSocket;

	private InputStream is;
	private OutputStream os;

	public PruuuServer() {
		try {
			this.serverSocket = new ServerSocket(Pruuunake.PORT);

			Socket socket = serverSocket.accept();

			this.os = socket.getOutputStream();
			this.is = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OutputStream getOutputStream() {
		return os;
	}

	public InputStream getInputStream() {
		return is;
	}

}
