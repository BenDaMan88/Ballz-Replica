package com.objects;

public class Player {
	
	private int balls;
	private int turn;
	private int xPosition;
	private int yPosition;
	private int cursorX;
	private int cursorY;
	
	private boolean showCursor;
	
	public Player()
	{
		balls = 1;
		turn = 1;
		xPosition = 130;
		yPosition = 430;
		cursorX = 140;
		cursorY = 440;
		showCursor = false;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getCursorX() {
		return cursorX;
	}

	public void setCursorX(int cursorX) {
		this.cursorX = cursorX;
	}

	public int getCursorY() {
		return cursorY;
	}

	public void setCursorY(int cursorY) {
		this.cursorY = cursorY;
	}

	public boolean isShowCursor() {
		return showCursor;
	}

	public void setShowCursor(boolean showCursor) {
		this.showCursor = showCursor;
	}
	
	
}
