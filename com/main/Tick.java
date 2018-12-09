package com.main;

import com.display.Render;
import com.objects.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;


//holds all logic for each tick
public class Tick extends TimerTask implements KeyListener {
	
	private Render render;
	private boolean main_menu;
	private boolean game_play;
	private boolean end_screen;
	private boolean running;
	
	private boolean wait;
	private boolean startTurn;
	
	private Player player;
	private BoardPiece [][] board;
	private ArrayList<Ball> balls;
	private int newBalls;
	public static final int BOARD_WIDTH = 8;
	public static final int BOARD_HEIGHT = 12;
	
	private double xVector;
	private double yVector;
	
	private int counter;
	private int timer;
	private boolean endTurn;
	
	private boolean enter;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean f;
	
	public Tick(Render render)
	{
		this.render = render;
		this.main_menu = true;
		this.game_play = false;
		this.end_screen = false;
		this.running = false;
		
		player = new Player();
		board = new BoardPiece[BOARD_WIDTH][BOARD_HEIGHT];
		balls = new ArrayList<Ball>(1);
		balls.add(new Ball());
		newBalls = 0;
		
		xVector = 0;
		yVector = 0;
		
		counter = 0;
		timer = 0;
		endTurn = false;
		
		this.enter = false;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
		this.f = false;
		
		this.wait = false;
		this.startTurn = true;
	}

	public boolean isMain_menu() {
		return main_menu;
	}

	public boolean isGame_play() {
		return game_play;
	}

	public boolean isEnd_screen() {
		return end_screen;
	}

	public boolean isRunning() {
		return running;
	}

	public void main_menu()
	{
		if(wait)
		{
			try {
			Thread.sleep(200);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
			wait = false;
		}
		if(enter && main_menu)
		{
			
			main_menu = false;
			game_play = true;
			startTurn = true;
		}
			
	}

	public void game_tick()
	{
		if(running)
		{
			game_logic_running();
		}
		else
		{
			if(startTurn)
			{
				startTurn();
				startTurn = !startTurn;
			}
			else
			{
				game_logic_input();
			}
		}
	}
	
	public void end_screen()
	{
		if(enter && end_screen)
		{
			end_screen = false;
			main_menu = true;
			wait = true;
			for(int i = 0; i < BOARD_WIDTH; i++)
			{
				for(int j = 0; j < BOARD_HEIGHT; j++)
				{
					board[i][j] = null;
				}
			}
			balls = new ArrayList<Ball>(1);
			balls.add(new Ball());
			player = new Player();
		}
	}
	
	public void set_render()
	{
		render.setRunning(running);
		render.setGame_play(game_play);
		render.setMain_menu(main_menu);
		render.setEnd_screen(end_screen);
		render.setPlayer(player);
		render.setBoard(board);
		render.setBalls(balls);
		render.setCounter(counter);
	}
	
	@Override
	public void run() {
		if(main_menu)
			main_menu();
		if(game_play)
			game_tick();
		if(end_screen)
			end_screen();
		set_render();
		
		render.repaint();
	}
	
	public void game_logic_running()
	{
		if(counter < balls.size())
		{
			for(int i = 0; i < counter + 1; i++)
			{
				balls.get(i).setxPosition(balls.get(i).getxPosition() + balls.get(i).getxSpeed());
				balls.get(i).setyPosition(balls.get(i).getyPosition() + balls.get(i).getySpeed());
			}
			//border collision detection
			for(int i = 0; i < counter + 1; i++)
			{
				if(balls.get(i).getxPosition() > 273 || balls.get(i).getxPosition() <= 0)
				{
					balls.get(i).setxSpeed(balls.get(i).getxSpeed() * -1);
					balls.get(i).setySpeed(balls.get(i).getySpeed() + .05);
					if(balls.get(i).getxPosition() <= 0)
						balls.get(i).setxPosition(1);
					else
						balls.get(i).setxPosition(273);
				}
				if(balls.get(i).getyPosition() < 0)
				{
					balls.get(i).setySpeed(balls.get(i).getySpeed() * -1 - .1);
				}
			}
			for(int k = 0; k < counter + 1; k++)
			{
				for(int i = 0; i < BOARD_WIDTH; i++)
				{
					for(int j = 0; j < BOARD_HEIGHT; j++)
					{
						if(board[i][j] != null && !board[i][j].getIsSquare())
						{
							double x = 17 + i * 35 + 15 / 2;
							double y = 52 + j * 35 + 15 / 2;
							if(Math.sqrt(Math.pow(x - balls.get(k).getxPosition() - 10, 2) + Math.pow(y - balls.get(k).getyPosition() - 10, 2)) <= 17.5)
							{
								board[i][j] = null;
								newBalls++;
							}
							
						}
						if(board[i][j] != null && board[i][j].getIsSquare())
						{
							//top and bottom side collision detection
							if(balls.get(k).getxPosition() >= 10 + i * 35 && balls.get(k).getxPosition() <= 20 + i * 35 && 
							  ((balls.get(k).getyPosition() >= 25 + j * 35 && balls.get(k).getyPosition() <= 45 + j * 35) || 
							   (balls.get(k).getyPosition() >= 55 + j * 35 && balls.get(k).getyPosition() <= 75 + j * 35)))
							{
								board[i][j].setNum(board[i][j].getNum() - 1);
								balls.get(k).setySpeed(balls.get(k).getySpeed() * -1);
								if(balls.get(k).getyPosition() >= 25 + j * 35 && balls.get(k).getyPosition() <= 45 + j * 35)
									balls.get(k).setyPosition(24 + j * 35);
								else
									balls.get(k).setyPosition(76 + j * 35);
							}
							//side collision detection
							else if(balls.get(k).getyPosition() >= 45 + j * 35 && balls.get(k).getyPosition() <= 55 + j * 35 && 
									  ((balls.get(k).getxPosition() >= -10 + i * 35 && balls.get(k).getxPosition() <= 10 + i * 35) || 
									   (balls.get(k).getxPosition() >= 20 + i * 35 && balls.get(k).getxPosition() <= 40 + i * 35)))
							{
								board[i][j].setNum(board[i][j].getNum() - 1);
								balls.get(k).setxSpeed(balls.get(k).getxSpeed() * -1);
								if(balls.get(k).getxPosition() >= -10 + i * 35 && balls.get(k).getxPosition() <= 10 + i * 35)
									balls.get(k).setxPosition(-11 + i * 35);
								else
									balls.get(k).setxPosition(41 + i * 35);
							}
							else
							{
								
							}
							//do collision detection
							//if intersect, redirect course, subtract one from square 
							
							//if square is at zero, remove square
							if(board[i][j].getNum() == 0)
							{
								board[i][j] = null;
							}
						}
					}
				}
			}
			if(timer == 0)
			{
				counter++;
				timer = 10;
			}
			timer--;
		}
		else
		{
			for(int i = 0; i < balls.size(); i++)
			{
				balls.get(i).setxPosition(balls.get(i).getxPosition() + balls.get(i).getxSpeed());
				balls.get(i).setyPosition(balls.get(i).getyPosition() + balls.get(i).getySpeed());
			}
			//border collision detection
			for(int i = 0; i < balls.size(); i++)
			{
				if(balls.get(i).getxPosition() >= 273 || balls.get(i).getxPosition() <= 0)
				{
					balls.get(i).setxSpeed(balls.get(i).getxSpeed() * -1);
					balls.get(i).setySpeed(balls.get(i).getySpeed() + .05);
					if(balls.get(i).getxPosition() <= 0)
						balls.get(i).setxPosition(1);
					else
						balls.get(i).setxPosition(273);
				}
				if(balls.get(i).getyPosition() < 0)
				{
					balls.get(i).setySpeed(balls.get(i).getySpeed() * -1 + .05);
				}
			}
		}
		for(int i = 0; i < balls.size(); i++)
		{
			if(balls.get(i).getyPosition() > 500 - 50)
			{
				balls.get(i).setxPosition(130);
				balls.get(i).setyPosition(430);
				balls.get(i).setxSpeed(0);
				balls.get(i).setySpeed(0);
				balls.get(i).setStopped(true);
			}
		}
		for(int k = 0; k < balls.size(); k++)
		{
			for(int i = 0; i < BOARD_WIDTH; i++)
			{
				for(int j = 0; j < BOARD_HEIGHT; j++)
				{
					if(board[i][j] != null && !board[i][j].getIsSquare())
					{
						double x = 17 + i * 35 + 15 / 2;
						double y = 52 + j * 35 + 15 / 2;
						if(Math.sqrt(Math.pow(x - balls.get(k).getxPosition() - 10, 2) + Math.pow(y - balls.get(k).getyPosition() - 10, 2)) <= 17.5)
						{
							board[i][j] = null;
							newBalls++;
						}
					}
					if(board[i][j] != null && board[i][j].getIsSquare())
					{
						//top and bottom side collision detection
						if(balls.get(k).getxPosition() >= 10 + i * 35 - 9 && balls.get(k).getxPosition() <= 20 + i * 35 + 9 && 
						  ((balls.get(k).getyPosition() >= 25 + j * 35 && balls.get(k).getyPosition() <= 45 + j * 35) || 
						   (balls.get(k).getyPosition() >= 55 + j * 35 && balls.get(k).getyPosition() <= 75 + j * 35)))
						{
							board[i][j].setNum(board[i][j].getNum() - 1);
							balls.get(k).setySpeed(balls.get(k).getySpeed() * -1);
							if(balls.get(k).getyPosition() >= 25 + j * 35 && balls.get(k).getyPosition() <= 45 + j * 35)
								balls.get(k).setyPosition(24 + j * 35);
							else
								balls.get(k).setyPosition(76 + j * 35);
						}
						//side collision detection
						else if(balls.get(k).getyPosition() >= 45 + j * 35 - 9 && balls.get(k).getyPosition() <= 55 + j * 35 + 9 && 
								  ((balls.get(k).getxPosition() >= -10 + i * 35 && balls.get(k).getxPosition() <= 10 + i * 35) || 
								   (balls.get(k).getxPosition() >= 20 + i * 35 && balls.get(k).getxPosition() <= 40 + i * 35)))
						{
							board[i][j].setNum(board[i][j].getNum() - 1);
							balls.get(k).setxSpeed(balls.get(k).getxSpeed() * -1);
							if(balls.get(k).getxPosition() >= -10 + i * 35 && balls.get(k).getxPosition() <= 10 + i * 35)
								balls.get(k).setxPosition(-11 + i * 35);
							else
								balls.get(k).setxPosition(41 + i * 35);
						}
						//upper left corner collision
						else if(Math.sqrt(Math.pow(balls.get(k).getxPosition() + 10 - (10 + i * 35),2) + Math.pow(balls.get(k).getyPosition() + 10 - (45 + j * 35), 2)) <= 10)
						{
							double nx = balls.get(k).getxPosition() - (40 + i * 35);
							double ny = balls.get(k).getyPosition() - (75 + j * 35);
							double px = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * nx;
							double py = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * ny;
							double rx = balls.get(k).getxSpeed() - 2 * px;
							double ry = balls.get(k).getySpeed() - 2 * py;
							balls.get(k).setxSpeed(rx);
							balls.get(k).setySpeed(ry);
							board[i][j].setNum(board[i][j].getNum() - 1);
						}
						//lower left corner
						else if(Math.sqrt(Math.pow(balls.get(k).getxPosition() + 10 - (10 + i * 35),2) + Math.pow(balls.get(k).getyPosition() + 10 - (75 + j * 35), 2)) <= 10)
						{
							double nx = balls.get(k).getxPosition() - (40 + i * 35);
							double ny = balls.get(k).getyPosition() - (75 + j * 35);
							double px = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * nx;
							double py = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * ny;
							double rx = balls.get(k).getxSpeed() - 2 * px;
							double ry = balls.get(k).getySpeed() - 2 * py;
							balls.get(k).setxSpeed(rx);
							balls.get(k).setySpeed(ry);
							board[i][j].setNum(board[i][j].getNum() - 1);
						}
						//upper right corner
						else if(Math.sqrt(Math.pow(balls.get(k).getxPosition() + 10 - (40 + i * 35),2) + Math.pow(balls.get(k).getyPosition() + 10 - (45 + j * 35), 2)) <= 10)
						{
							double nx = balls.get(k).getxPosition() - (40 + i * 35);
							double ny = balls.get(k).getyPosition() - (75 + j * 35);
							double px = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * nx;
							double py = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * ny;
							double rx = balls.get(k).getxSpeed() - 2 * px;
							double ry = balls.get(k).getySpeed() - 2 * py;
							balls.get(k).setxSpeed(rx);
							balls.get(k).setySpeed(ry);
							board[i][j].setNum(board[i][j].getNum() - 1);
						}
						//lower right corner
						else if(Math.sqrt(Math.pow(balls.get(k).getxPosition() + 10 - (40 + i * 35),2) + Math.pow(balls.get(k).getyPosition() + 10 - (75 + j * 35), 2)) <= 5)
						{
							double nx = balls.get(k).getxPosition() - (40 + i * 35);
							double ny = balls.get(k).getyPosition() - (75 + j * 35);
							double px = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * nx;
							double py = (balls.get(k).getxSpeed() * nx + balls.get(k).getySpeed() * ny) / (nx * nx + ny * ny) * ny;
							double rx = balls.get(k).getxSpeed() - 2 * px;
							double ry = balls.get(k).getySpeed() - 2 * py;
							balls.get(k).setxSpeed(rx);
							balls.get(k).setySpeed(ry);
							board[i][j].setNum(board[i][j].getNum() - 1);
						}
						else
						{
							continue;
						}
						//do collision detection
						//if intersect, redirect course, subtract one from square 
						
						//if square is at zero, remove square
						if(board[i][j].getNum() == 0)
						{
							board[i][j] = null;
						}
					}
				}
			}
		}
		endTurn = true;
		for(int i = 0; i < balls.size(); i++)
		{
			if(!balls.get(i).isStopped())
				endTurn = false;
		}
		if(endTurn)
		{
			running = false;
			startTurn = true;
		}
	}
	
	public void startTurn()
	{
		for(int i = 0; i < newBalls; i++)
		{
			balls.add(new Ball());
		}
		newBalls = 0;
		player.setTurn(player.getTurn() + 1);
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			for(int j = BOARD_HEIGHT - 2; j >= 0; j--)
			{
				board[i][j+1] = board[i][j];
			}
		}
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			board[i][0] = null;
		}
		Random r = new Random();
		int ballSpot = r.nextInt(BOARD_WIDTH);
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			if(r.nextInt(2) == 0 && i != ballSpot)
			{
				board[i][0] = new Square(player.getTurn());
			}
		}
		board[ballSpot][0] = new BoardBall();
		
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			if(board[i][BOARD_HEIGHT - 1] != null && board[i][BOARD_HEIGHT - 1].getIsSquare())
			{
				end_screen = true;
				game_play = false;
			}
			if(board[i][BOARD_HEIGHT - 1] != null && !board[i][BOARD_HEIGHT - 1].getIsSquare())
			{
				board[i][BOARD_HEIGHT - 1] = null;
			}
		}
		player.setCursorX(140);
		player.setCursorY(440);
		/*balls.add(new Ball());
		balls.get(balls.size() - 1).setxPosition(30);
		balls.get(balls.size() -1 ).setyPosition(0);
		balls.add(new Ball());
		balls.get(balls.size() - 1).setxPosition(10);
		balls.get(balls.size() -1 ).setyPosition(20);*/
	}
	
	public void game_logic_input()
	{
		if(up)
			player.setCursorY(player.getCursorY() - 1);
		if(down)
			player.setCursorY(player.getCursorY() + 1);
		if(left)
			player.setCursorX(player.getCursorX() - 1);
		if(right)
			player.setCursorX(player.getCursorX() + 1);
		if(player.getCursorX() == player.getxPosition() + 10 && player.getCursorY() == player.getyPosition() + 10)
			player.setShowCursor(false);
		if(player.getCursorX() != player.getxPosition() + 10 || player.getCursorY() != player.getyPosition() + 10)
			player.setShowCursor(true);
		if(f && (player.getCursorX() != 140 || player.getCursorY() != 440))
		{
			xVector = 4 * (player.getCursorX() - player.getxPosition() - 10) / (Math.sqrt(Math.pow(player.getCursorX() - player.getxPosition() - 10, 2) + 
																				 	 Math.pow(player.getCursorY() - player.getyPosition() - 10, 2)));
			yVector = 4 * (player.getCursorY() - player.getyPosition() - 10) / (Math.sqrt(Math.pow(player.getCursorX() - player.getxPosition() - 10, 2) + 
					 													   			 Math.pow(player.getCursorY() - player.getyPosition() - 10, 2)));
			for(int i = 0; i < balls.size(); i++)
			{
				balls.get(i).setxSpeed(xVector);
				balls.get(i).setySpeed(yVector);
				balls.get(i).setStopped(false);
			}
			counter = 0;
			timer = 10;
			running = true;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER){
			enter = true;
		}
		if(key.getKeyCode() == KeyEvent.VK_UP){
			up = true;
		}
		if(key.getKeyCode() == KeyEvent.VK_DOWN){
			down = true;
		}
		if(key.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}
		if(key.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(key.getKeyCode() == KeyEvent.VK_F){
			f = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER){
			enter = false;		
		}
		if(key.getKeyCode() == KeyEvent.VK_UP){
			up = false;
		}
		if(key.getKeyCode() == KeyEvent.VK_DOWN){
			down = false;
		}
		if(key.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		}
		if(key.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(key.getKeyCode() == KeyEvent.VK_F){
			f = false;
		}
	}


	@Override
	public void keyTyped(KeyEvent key) {
		
	}


}
