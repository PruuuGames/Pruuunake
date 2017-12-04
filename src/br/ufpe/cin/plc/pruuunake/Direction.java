package br.ufpe.cin.plc.pruuunake;

import java.util.HashMap;
import java.util.Map;

public enum Direction {

	UP(8, -1, 0),
	RIGHT(6, 0, 1),
	DOWN(2, 1, 0),
	LEFT(4, 0, -1);

	private static Map<Integer, Direction> byCode;

	static {
		byCode = new HashMap<Integer, Direction>();

		for (Direction direction : values()) {
			byCode.put(direction.code, direction);
		}
	}

	private int code;

	private int x;
	private int y;

	Direction(int code, int x, int y) {
		this.code = code;

		this.x = x;
		this.y = y;
	}

	public int getCode() {
		return code;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Point process(Point point) {
		return new Point(point.getX() + this.x, point.getY() + this.y);
	}

	public static Direction fromCode(int move) {
		return byCode.get(move);
	}

}
