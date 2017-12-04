package br.ufpe.cin.plc.pruuunake;

public class Point extends Pair<Integer, Integer> {

	public Point(Integer first, Integer second) {
		super(first, second);
	}

	public int getX() {
		return getFirst();
	}

	public int getY() {
		return getSecond();
	}

	public void ensureBounds() {
		int size = Pruuunake.SIZE;

		if (getX() == -1) {
			setFirst(size - 1);
		} else if (getX() == size) {
			setFirst(0);
		} else if (getY() == -1) {
			setSecond(size - 1);
		} else if (getY() == size) {
			setSecond(0);
		}
	}

}
