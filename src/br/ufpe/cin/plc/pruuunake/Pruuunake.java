package br.ufpe.cin.plc.pruuunake;

import java.util.Deque;
import java.util.concurrent.locks.Lock;

public class Pruuunake {

	public static final int PORT = 6666;

	public static final int SIZE = 20;

	private static Pruuunake INSTANCE;

	public static Pruuunake getInstance() {
		return INSTANCE;
	}

	private Field field;

	private Snake player1;
	private Snake player2;

	private Reader reader;
	private Writer writer;
	private Pizzeria pizzeria;

	public Pruuunake(String option) {
		INSTANCE = this;

		this.field = new Field();

		setup(option);
	}

	public Field getField() {
		return this.field;
	}

	public Snake getPlayer1() {
		return this.player1;
	}

	public Snake getPlayer2() {
		return this.player2;
	}

	public Reader getReader() {
		return this.reader;
	}

	public Writer getWriter() {
		return this.writer;
	}

	public Pizzeria getPizzeria() {
		return this.pizzeria;
	}

	public boolean isHost() {
		return writer instanceof ServerWriter;
	}

	private void setup(String option) {
		if (option.equals("0")) {
			player1 = new Snake('A', 0, 0, Direction.DOWN);
			player2 = new Snake('B', SIZE - 1, SIZE - 1, Direction.UP);

			Lock lock = field.getLock();

			lock.lock();

			char[][] data = field.getData();

			data[0][0] = 'A';
			data[SIZE - 1][SIZE - 1] = 'B';

			lock.unlock();

			System.out.println("Aguardando conexão...");
			PruuuServer pruuuServer = new PruuuServer();

			writer = new ServerWriter(pruuuServer.getOutputStream());
			reader = new ServerReader(pruuuServer.getInputStream());

			new Thread(new Pizzeria()).start();
		} else {
			PruuuClient pruuuClient = new PruuuClient(option);

			writer = new ClientWriter(pruuuClient.getOutputStream());
			reader = new ClientReader(pruuuClient.getInputStream());
		}

		new Thread(reader).start();
		new Thread(writer).start();
	}

	public void move(Snake player) {
		Direction direction = player.getDirection();
		Deque<Point> tail = player.getTail();

		Point head = direction.process(player.getHead());
		head.ensureBounds();
		tail.addLast(head);

		Point last = tail.removeFirst();

		Lock lock = field.getLock();

		lock.lock();

		char[][] data = field.getData();

		data[last.getX()][last.getY()] = ' ';
		data[head.getX()][head.getY()] = player.getId();

		lock.unlock();
	}

}
