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
	private static final int FIELD_SIZE_X = 20;
	private static final int FIELD_SIZE_Y = 20;

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

	public JFrame mainPanel;

	public RenderField render;

	public Timer timer = new Timer(20, this);

	public GameFrame(String option) {
		pruuunake = new Pruuunake(option);

		mainPanel = new JFrame("GameFrame " + pruuunake.isHost());
		mainPanel.setVisible(true);
		mainPanel.setSize(WIDTH, HEIGHT);
		mainPanel.setResizable(false);
		mainPanel.setLocationRelativeTo(null);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.addKeyListener(this);
		mainPanel.setIconImage(new ImageIcon("src/br/ufpe/cin/plc/assets/pruuA.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainPanel.setContentPane(render = new RenderField());
		mainPanel.repaint();
		mainPanel.revalidate();
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
