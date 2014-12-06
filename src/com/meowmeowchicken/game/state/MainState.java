package com.meowmeowchicken.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.meowmeowchicken.game.main.GameMain;
import com.meowmeowchicken.game.model.Letter;

public class MainState extends State {

	private Letter player;
	private ArrayList<Letter> letters;
	private ArrayList<Integer> keysPressed;

	@Override
	public void init() {
		System.out.println("Main State Entered");
		player = new Letter(50, 50, 13, 14, "Z", 30, 10, false);
		letters = new ArrayList<Letter>();
		keysPressed = new ArrayList<Integer>();
		letters.add(new Letter(100, 100, 13, 14, "A", new Font("Arial",
				Font.PLAIN, 18)));
		letters.add(new Letter(150, 100, 13, 14, "a", new Font("Arial",
				Font.PLAIN, 18)));

	}

	@Override
	public void update(float delta) {
		if (player.isAlive()) {
			player.update(delta);
		}
		updateLetters(delta);

	}

	private void updateLetters(float delta) {
		for (Letter l : letters) {
			l.update(delta, player);
			// TODO: Check for collisions
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		renderPlayer(g);
		renderLetters(g);

	}

	private void renderPlayer(Graphics g) {
		// Draw hitbox
		g.setColor(new Color(.5f, .5f, .5f, .2f));
		g.fillRect((int) player.getX(),
				(int) player.getY() - player.getHeight(), player.getWidth(),
				player.getHeight());
		// Draw Player
		g.setFont(player.getFont());
		g.setColor(Color.BLACK);
		g.drawString(player.getString(), (int) player.getX(),
				(int) player.getY());
	}

	private void renderLetters(Graphics g) {
		// Draw letters
		for (Letter l : letters) {
			// Draw hitbox
			g.setColor(new Color(.5f, .5f, .5f, .2f));
			g.fillRect((int) l.getX(), (int) l.getY() - l.getHeight(),
					l.getWidth(), l.getHeight());
			// Draw character
			g.setFont(l.getFont());
			g.setColor(Color.RED);
			g.drawString(l.getString(), (int) l.getX(), (int) l.getY());
		}
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if (!keysPressed.contains(e.getKeyCode()))
			keysPressed.add(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			player.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			player.punch();
			break;
		}

	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		keysPressed.remove(keysPressed.indexOf(e.getKeyCode()));

		if (!(keysPressed.contains(KeyEvent.VK_LEFT) || keysPressed
				.contains(KeyEvent.VK_RIGHT))) {
			player.stopHorizontal();
		} 
		if (!(keysPressed.contains(KeyEvent.VK_UP) || keysPressed
				.contains(KeyEvent.VK_DOWN))) {
			player.stopVertical();
		}
	}
}
