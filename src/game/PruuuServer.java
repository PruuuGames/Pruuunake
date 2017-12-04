package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PruuuServer {

	private ServerSocket serverSocket;

	private DataInputStream dis;
	private DataOutputStream dos;

	public PruuuServer() {
		try {
			this.serverSocket = new ServerSocket(Pruuunake.PORT);

			Socket socket = serverSocket.accept();

			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataOutputStream getOutputStream() {
		return dos;
	}

	public DataInputStream getInputStream() {
		return dis;
	}

}
