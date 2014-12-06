package com.meowmeowchicken.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.meowmeowchicken.game.main.GameMain;

public abstract class State {

	public abstract void init();
	
	public abstract void update(float delta);
	
	public abstract void render(Graphics g);
	
	public abstract void onKeyPress(KeyEvent e);
	
	public abstract void onKeyRelease(KeyEvent e);
	
	public void setCurrentState(State newState){
		GameMain.game.setCurrentState(newState);
	}
}
