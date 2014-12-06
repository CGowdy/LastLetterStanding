package com.meowmeowchicken.game.model;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

public class Letter {
	private float x, y;
	private int width, height, velX, velY;
	private String letter;
	private Rectangle rect;
	private int hp;
	private int movementSpeed;
	private Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
	private boolean isBadGuy = true;

	private boolean isAlive;
	private boolean isPunching;

	public Letter(float x, float y, String letter, FontMetrics metric) {
		this.x = x;
		this.y = y;
		this.letter = letter;
		this.width = metric.stringWidth(letter);
		this.hp = 10;
		this.movementSpeed = 3;
	}
	
	public Letter(float x, float y, int width, int height, String letter) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.letter = letter;
		this.hp = 10;

		isAlive = true;
		isPunching = false;
	}
	
	public Letter(float x, float y, int width, int height, String letter,
			Font font) {
		this(x, y, width, height, letter);
		this.font = font;
	}



	public Letter(float x, float y, int width, int height, String letter, int hp) {
		this(x, y, width, height, letter);
		this.hp = hp;
		this.movementSpeed = 3;
	}

	public Letter(float x, float y, int width, int height, String letter,
			int hp, int speed) {
		this(x, y, width, height, letter, hp);
		this.movementSpeed = speed;
	}

	public Letter(float x, float y, int width, int height, String letter,
			int hp, int speed, Font font) {
		this(x, y, width, height, letter, hp, speed);
		this.font = font;
	}

	public Letter(int x, int y, int width, int height, String letter, int hp, int speed,
			boolean baddie) {
		this(x, y, width, height, letter, hp, speed);
		this.isBadGuy = baddie;
	}
	
	public void update(float delta){
		this.x += velX * delta;
		this.y += velY * delta;
	}

	public void update(float delta, Letter l) {
		float deltaX = this.x - l.getX();
		float deltaY = this.y - l.getY();

		if (deltaX > 0) {
			moveLeft();

		} else if (deltaX < 0) {
			moveRight();
		}

		if (deltaY > 0) {
			moveUp();
		} else {
			if (deltaY < 0) {
				moveDown();
			}
		}
		this.x += velX * delta;
		this.y += velY * delta;
	}

	public void punch() {
		// TODO: code later
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public Font getFont() {
		return font;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getRect() {
		return rect;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isPunching() {
		return isPunching;
	}

	public String getString() {
		return letter;
	}

	public void moveUp() {
		this.velY = -movementSpeed;

	}

	public void moveDown() {
		this.velY = movementSpeed;
	}

	public void moveLeft() {
		this.velX = -movementSpeed;
	}

	public void moveRight() {
		this.velX = movementSpeed;
	}
	
	public void stop(){
		velY = velX = 0;
	}

}
