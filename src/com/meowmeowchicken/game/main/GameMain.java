package com.meowmeowchicken.game.main;

import javax.swing.JFrame;
public class GameMain {
		public static final String GAME_TITLE = "Last Letter Standing";
		public static final int GAME_WIDTH = 800;
		public static final int GAME_HEIGHT = 450;
		public static Game game;
		
	public static void main(String[] args) {
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		game = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
	}

}
