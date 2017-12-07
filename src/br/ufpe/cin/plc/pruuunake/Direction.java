package br.ufpe.cin.plc.pruuunake;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public enum Direction {

	UP(0, -1, KeyEvent.VK_W, KeyEvent.VK_UP),
	RIGHT(1, 0, KeyEvent.VK_D, KeyEvent.VK_RIGHT),
	DOWN(0, 1, KeyEvent.VK_S, KeyEvent.VK_DOWN),
	LEFT(-1, 0, KeyEvent.VK_A, KeyEvent.VK_LEFT);

	private static Map<Integer, Direction> byCode;

	static {
		byCode = new HashMap<Integer, Direction>();

		for (Direction direction : values()) {
			for (int code : direction.getCodes()) {
				byCode.put(code, direction);
			}
		}
	}

	private int x;
	private int y;
	
	private Collection<Integer> codes;

	Direction(int x, int y, Integer... codes) {
		this.x = x;
		this.y = y;

		this.codes = Arrays.asList(codes);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public Direction getOppositeDirection() {
		switch(this) {
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case RIGHT:
			return LEFT;
		case LEFT:
			return RIGHT;
		}
		
		return null;
	}

	public Collection<Integer> getCodes() {
		return this.codes;
	}

	public Point process(Point point) {
		return new Point(point.getX() + this.x, point.getY() + this.y);
	}

	public static Direction fromCode(int move) {
		return byCode.get(move);
	}

}
