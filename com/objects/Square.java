package com.objects;

public class Square extends BoardPiece{
	
	private int num;
	
	public Square(int num)
	{
		this.num = num;
		isSquare = true;
	}
	
	public int get_num()
	{
		return num;
	}
	
	public void set_num(int num)
	{
		this.num = num;
	}

	@Override
	public boolean getIsSquare() {
		return isSquare;
	}

	@Override
	public int getNum() {
		return num;
	}

	@Override
	public void setNum(int num) {
		this.num = num;
	}
	
}
