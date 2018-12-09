package com.objects;

public class BoardBall extends BoardPiece{
	
	public BoardBall()
	{
		isSquare = false;
	}

	@Override
	public boolean getIsSquare() {
		return isSquare;
	}

	@Override
	public int getNum() {
		return 1;
	}

	@Override
	public void setNum(int num) {
		return;
	}

}
