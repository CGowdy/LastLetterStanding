package com.meowmeowchicken.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.meowmeowchicken.framework.util.InputHandler;
import com.meowmeowchicken.game.state.MainState;
import com.meowmeowchicken.game.state.State;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable{
	private int gameWidth;
	private int gameHeight;

	private Image gameImage;
	
	private volatile boolean running = false;
	private Thread gameThread;
	private volatile State currentState;
	private InputHandler inputHandler;
	
	public Game(int gameWidth, int gameHeight){
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void run() {
		long updateDurationMillis = 0;
		long sleepDurationMillis = 0;
		while(running){
			long beforeupdateRender = System.nanoTime();
			long deltaMillis = updateDurationMillis + sleepDurationMillis;
			
			updateAndRender(deltaMillis);
			
			updateDurationMillis = (System.nanoTime() - beforeupdateRender) / 100000L;
			sleepDurationMillis = Math.max(2, 17-updateDurationMillis);
			
			try{
				Thread.sleep(sleepDurationMillis);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
		System.exit(0);
		
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		initInput();
		setCurrentState(new MainState());
		initGame();
	}
	
	private void initInput() {
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);
		
	}

	public void setCurrentState(State newState){
		System.gc();
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(newState);
	}
	
	private void initGame(){
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}
	
	private void updateAndRender(long deltaMillis){
		currentState.update(deltaMillis / 1000f);
		prepareGameImage();
		currentState.render(gameImage.getGraphics());
		renderGameImage(getGraphics());
		
	}
	private void prepareGameImage(){
		if(gameImage == null){
			gameImage = createImage(gameWidth, gameHeight);
		}
		
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	
	private void renderGameImage(Graphics graphics) {
		if (gameImage != null){
			graphics.drawImage(gameImage, 0, 0, null);
		}
		graphics.dispose();
		
	}

	
}
