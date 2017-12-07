package br.ufpe.cin.plc.pruuunake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {

	private char id;
	private Direction direction;
	private Deque<Point> body;

	public Snake(char id, int x, int y, Direction direction) {
		this.id = id;
		this.direction = direction;
		this.body = new LinkedList<Point>();

		this.body.addLast(new Point(x, y));
	}

	public char getId() {
		return this.id;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Point getHead() {
		return this.body.getLast();
	}

	public Deque<Point> getBody() {
		return this.body;
	}

	public void turn(int keyCode) {
		Direction newDirection = Direction.fromCode(keyCode);

		if (newDirection == null) {
			return;
		}

		if (newDirection != this.direction) {
			this.direction = newDirection;
		}
	}

}
