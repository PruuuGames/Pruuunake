package br.ufpe.cin.plc.pruuunake;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Snake {

	private char id;
	private Direction direction;
	private Deque<Point> tail;
	private Queue<Point> keep;

	private AtomicBoolean pizza;

	private char[][] field;

	public Snake(char id, int x, int y, Direction direction, AtomicBoolean pizza, char[][] field) {
		this.id = id;
		this.direction = direction;
		this.tail = new LinkedList<Point>();
		this.keep = new LinkedList<Point>();

		this.tail.addLast(new Point(x, y));

		this.pizza = pizza;

		this.field = field;
	}

	public char getId() {
		return this.id;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Point getHead() {
		return this.tail.getLast();
	}

	public Queue<Point> getTail() {
		return this.tail;
	}

	public void turn(Direction direction) {
		if (direction != this.direction) {
			this.direction = direction;
		}
	}

	public void move() {
		Point oldHead = getHead();

		Point head = direction.process(oldHead);
		head.ensureBounds();

		tail.addLast(head);

		synchronized (field) {
			if (field[head.getX()][head.getY()] == 'X') {
				keep.add(oldHead);

				pizza.set(false);
			}
		}

		Point last = tail.getFirst();
		if (!last.equals(keep.peek())) {
			tail.removeFirst();
		} else {
			keep.remove();
		}

		synchronized (field) {
			field[last.getX()][last.getY()] = ' ';
			field[head.getX()][head.getY()] = id;
		}
	}

}
