package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Pruuunake {

	public static final int PORT = 6666;

	private char[][] field;

	private int ax;
	private int ay;

	public static void main(String[] args) {
		new Pruuunake();
	}

	public Pruuunake() {
		this.field = new char[10][10];

		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				field[i][j] = ' ';
			}
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite 0 para ser o servidor ou digite o ip do outro jogador:");
		String ip = scanner.next();

		Reader reader = null;
		Writer writer = null;

		if (!ip.equals("0")) {
			try {
				this.ax = 9;
				this.ay = 9;

				this.field[ay][ax] = 'A';

				System.out.println("Tentando se concetar ao outro jogador...");
				Socket socket = new Socket(ip, PORT);
				System.out.println("Conexão estabelecida");
				PruuuClient pruuuClient = new PruuuClient(socket);

				reader = new Reader(0, 0, field, pruuuClient.getInputStream());
				writer = new Writer(pruuuClient.getOutputStream());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.ax = 0;
			this.ay = 0;

			this.field[ay][ax] = 'A';

			System.out.println("Aguardando conexão...");
			PruuuServer pruuuServer = new PruuuServer();

			reader = new Reader(9, 9, field, pruuuServer.getInputStream());
			writer = new Writer(pruuuServer.getOutputStream());
		}

		new Thread(reader).start();
		new Thread(writer).start();
		new Thread(new Printer(field)).start();

		int move;
		while ((move = scanner.nextInt()) != -1) {
			writer.queueMove(move);

			synchronized (field) {
				if (move == 1) {
					ax++;
				} else if (move == 2) {
					ax--;
				} else if (move == 3) {
					ay++;
				} else if (move == 4) {
					ay--;
				}

				field[ax][ay] = 'A';
			}
		}

		scanner.close();
	}

}
