package com.display;

import javax.swing.JPanel;

import com.main.Tick;
import com.objects.Ball;
import com.objects.BoardPiece;
import com.objects.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


//renders all of the game
public class Render extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	private boolean main_menu;
	private boolean game_play;
	private boolean end_screen;
	private boolean running;
	
	private Player player;
	private BoardPiece [][] board;
	private ArrayList<Ball> balls;
	
	private int counter;
	
	public boolean isMain_menu() {
		return main_menu;
	}

	public void setMain_menu(boolean main_menu) {
		this.main_menu = main_menu;
	}

	public boolean isGame_play() {
		return game_play;
	}

	public void setGame_play(boolean game_play) {
		this.game_play = game_play;
	}

	public boolean isEnd_screen() {
		return end_screen;
	}

	public void setEnd_screen(boolean end_screen) {
		this.end_screen = end_screen;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BoardPiece[][] getBoard() {
		return board;
	}

	public void setBoard(BoardPiece[][] board) {
		this.board = board;
	}

	public ArrayList<Ball> getBalls() {
		return balls;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		if(main_menu)
		{
			render_main_menu(g);
		}
		else if(game_play)
		{
			render_game_play(g);
		}
		else
		{
			render_end_screen(g);
		}
	}
	
	public void render_main_menu(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.setFont(new Font("Bold", Font.BOLD, 70));
		g.drawString("BALLZ", 30, 100);
		g.setFont(new Font("Bold", Font.BOLD, 25));
		g.drawString("Press enter to play", 30, 200);
		g.setFont(new Font("Bold", Font.BOLD, 20));
		g.drawString("Use Arrow Keys to", 30, 275);
		g.drawString("move cursor", 30, 300);
		g.drawString("When ready to fire hit ", 30, 330);
		g.drawString("'f' to launch balls", 30, 355);
		g.setColor(Color.BLACK);
	}
	
	public void render_game_play(Graphics g)
	{
		if(running)
		{
			render_game_running(g);
		}
		else
		{
			render_game_input(g);
		}
	}
	
	public void render_game_running(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.drawLine(5, 427, 290, 427);
		
		if(balls.size() > counter)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Bold", Font.BOLD, 15));
			g.drawString("x" + (balls.size() - counter), 155, 445);
		}
		
		for(int i = 0; i < balls.size(); i++)
		{
			g.setColor(Color.GREEN);
			g.fillOval((int)balls.get(i).getxPosition(), (int)balls.get(i).getyPosition(), 20, 20);
			g.setColor(Color.WHITE);
		}
		
		for(int i = 0; i < Tick.BOARD_WIDTH; i++)
		{
			for(int j = 0; j < Tick.BOARD_HEIGHT; j++)
			{
				if(board[i][j] != null)
				{
					if(board[i][j].getIsSquare())
					{
						g.setColor(Color.CYAN);
						g.fillRect(10 + i * 35, 45 + j * 35, 30, 30);
						g.setColor(Color.BLACK);
						g.setFont(new Font("Bold", Font.BOLD, 20));
						g.drawString(Integer.toString(board[i][j].getNum()), 15 + i * 35, 68 + j * 35);
					}
					else
					{
						g.setColor(Color.WHITE);
						g.fillOval(17 + i * 35, 52 + j * 35, 15, 15);
						g.setColor(Color.BLACK);
					}
				}
			}
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Bold", Font.BOLD, 20));
		g.drawString(Integer.toString(player.getTurn()), 140, 30);
	}
	
	public void render_game_input(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Bold", Font.BOLD, 20));
		g.drawString(Integer.toString(player.getTurn()), 140, 30);
		
		g.setColor(Color.GRAY);
		g.drawLine(5, 427, 290, 427);
		
		for(int i = 0; i < Tick.BOARD_WIDTH; i++)
		{
			for(int j = 0; j < Tick.BOARD_HEIGHT; j++)
			{
				if(board[i][j] != null)
				{
					if(board[i][j].getIsSquare())
					{
						g.setColor(Color.CYAN);
						g.fillRect(10 + i * 35, 45 + j * 35, 30, 30);
						g.setColor(Color.BLACK);
						g.setFont(new Font("Bold", Font.BOLD, 20));
						g.drawString(Integer.toString(board[i][j].getNum()), 15 + i * 35, 68 + j * 35);
					}
					else
					{
						g.setColor(Color.WHITE);
						g.fillOval(17 + i * 35, 52 + j * 35, 15, 15);
						g.setColor(Color.BLACK);
					}
				}
			}
		}
		
		g.setColor(Color.GREEN);
		g.fillOval(player.getxPosition(), player.getyPosition(), 20, 20);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Bold", Font.BOLD, 15));
		g.drawString("x" + balls.size(), 155, 445);
		
		if(player.isShowCursor())
		{
			g.setColor(Color.GRAY);
			g.drawLine(player.getxPosition() + 10, player.getyPosition() + 10, player.getCursorX(), player.getCursorY());
			g.setColor(Color.BLACK);
		}
	}
	
	public void render_end_screen(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.setFont(new Font("Bold", Font.BOLD, 40));
		g.drawString("GAME OVER", 30, 100);
		g.setFont(new Font("Bold", Font.BOLD, 20));
		g.drawString("Press enter to go to menu", 20, 200);
		g.setColor(Color.BLACK);
	}
	
}
