package br.ufpe.cin.plc.pruuunake;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import br.ufpe.cin.plc.views.GameFrame;

public class Pruuunake {

	public static final int PORT = 6666;

	public static final int SIZE = 20;

	private char[][] field;

	private AtomicBoolean pizza;

	private Snake player;

	private Writer writer;

	public Pruuunake(GameFrame gameFrame) {
		this.field = new char[SIZE][SIZE];

		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				field[i][j] = ' ';
			}
		}

		this.pizza = new AtomicBoolean(false);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite 0 para ser o servidor ou digite o ip do outro jogador:");
		String ip = scanner.next();

		scanner.close();

		Reader reader = null;
		writer = null;

		player = null;
		Snake other = null;

		if (!ip.equals("0")) {
			try {
				player = new Snake('A', SIZE - 1, SIZE - 1, Direction.UP, pizza, field);
				other = new Snake('B', 0, 0, Direction.DOWN, pizza, field);

				System.out.println("Tentando se concetar ao outro jogador...");
				Socket socket = new Socket(ip, PORT);
				System.out.println("Conexão estabelecida");
				PruuuClient pruuuClient = new PruuuClient(socket);

				reader = new Reader(other, field, pruuuClient.getInputStream());
				writer = new Writer(pruuuClient.getOutputStream());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			player = new Snake('A', 0, 0, Direction.DOWN, pizza, field);
			other = new Snake('B', SIZE - 1, SIZE - 1, Direction.UP, pizza, field);

			System.out.println("Aguardando conexão...");
			PruuuServer pruuuServer = new PruuuServer();

			reader = new Reader(other, field, pruuuServer.getInputStream());
			writer = new Writer(pruuuServer.getOutputStream());
			new Thread(new Pizzeria(writer, pizza, field)).start();
		}

		new Thread(reader).start();
		new Thread(writer).start();
		new Thread(new Printer(gameFrame, field, player, other)).start();
	}

	public Direction getPlayerDirection() {
		return player.getDirection();
	}

	public void turn(Direction direction) {
		writer.queueTurn(direction);

		player.turn(direction);
	}

}
