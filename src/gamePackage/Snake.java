package gamePackage;

public class Snake {

	private int playerNumber;
	private boolean alive;
	private char head;
	private char[] body;
	private int bodySize;

	public Snake(char head, int maxLenght, int player) {
		this.playerNumber = player;
		this.alive = true;
		this.head = head;
		this.body = new char[maxLenght];
		this.bodySize = 0;
	}

	public void snakeMovement(char next) {
		if (next != 'X') {
			char aux = head;
			char previous = ' ';
			head = next;
			if (playerNumber == 1) {
				head = 'A';
			} else {
				head = 'B';
			}
			for (int i = 0; i < bodySize; i++) {
				if (playerNumber == 1) {
					aux = 'A';
				} else {
					aux = 'B';
				}
				previous = body[i];
				body[i] = aux;
				if (playerNumber == 1) {
					body[i] = 'A';
				} else {
					body[i] = 'B';
				}
				aux = previous;
			}
			if (playerNumber == 1) {
				aux = 'A';
			} else {
				aux = 'B';
			}
		} else {
			setAlive(false);
		}
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public char getHead() {
		return this.head;
	}

	public void setHead(char head) {
		this.head = head;
	}

	public char[] getBody() {
		return this.body;
	}

	public void setBody(char[] body) {
		this.body = body;
	}

	public int getBodySize() {
		return this.bodySize;
	}

	public void setBodySize(int bodySize) {
		this.bodySize = bodySize;
	}

}
