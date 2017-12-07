package br.ufpe.cin.plc.pruuunake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {

	private char id;
	private int score;

	private Direction direction;
	private Deque<Point> body;

	public Snake(char id, int x, int y, Direction direction) {
		this.id = id;
		this.score = 1;

		this.direction = direction;
		this.body = new LinkedList<Point>();

		this.body.addLast(new Point(x, y));
	}

	public char getId() {
		return this.id;
	}

	public int getScore() {
		return this.score;
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
	
	public void setScore(int score) {
		this.score = score;
	}

	public void incrementScore() {
		score++;
	}

	public void decrementScore() {
		score--;
	}

	public void turn(int keyCode) {
		Direction newDirection = Direction.fromCode(keyCode);

		if (newDirection == null) {
			return;
		}

		if (newDirection != direction && newDirection != direction.getOppositeDirection()) {
			direction = newDirection;
		}
	}

}
