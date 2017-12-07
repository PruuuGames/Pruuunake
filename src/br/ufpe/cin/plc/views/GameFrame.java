package br.ufpe.cin.plc.views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import br.ufpe.cin.plc.pruuunake.ClientWriter;
import br.ufpe.cin.plc.pruuunake.Pruuunake;
import br.ufpe.cin.plc.pruuunake.Snake;

public class GameFrame implements ActionListener, KeyListener {

	private static final int HEIGHT = 829;
	private static final int WIDTH = 805;

	private static GameFrame INSTANCE;

	public static GameFrame getInstance() {
		return INSTANCE;
	}

	public static void main(String[] args) {
		System.out.println("Digite 0 para ser o servidor ou o ip do servidor:");

		Scanner scanner = new Scanner(System.in);

		String option = scanner.next();

		scanner.close();

		INSTANCE = new GameFrame(option);
	}

	public static GameFrame snake;

	public Pruuunake pruuunake;

	public JFrame frame;

	public RenderField render;

	public Timer timer = new Timer(20, this);

	public GameFrame(String option) {
		pruuunake = new Pruuunake(option);

		frame = new JFrame("GameFrame " + pruuunake.isHost());
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.setIconImage(new ImageIcon("src/br/ufpe/cin/plc/assets/pruuA.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		frame.setAlwaysOnTop(true);

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setContentPane(render = new RenderField());
		frame.repaint();
		frame.revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (pruuunake.isHost()) {
			Snake player = pruuunake.getPlayer1();

			player.turn(e.getKeyCode());
		} else {
			ClientWriter writer = (ClientWriter) pruuunake.getWriter();

			writer.queueTurn(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
