package com.main;

import com.display.Window;
import com.display.Render;

import java.util.Timer;

//holds main function and compiles components of game

public class Game {
	
	public Game()
	{
		Render render = new Render();
		Timer timer = new Timer();
		Tick gameTick = new Tick(render);
		Window frame = new Window(gameTick);
		frame.add(render);
		timer.scheduleAtFixedRate(gameTick, 10, 10);
	}
	
	public static void main(String args[])
	{
		new Game();
	}
}
