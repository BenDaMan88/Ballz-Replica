package com.objects;

public class Ball {
	
	private double xPosition;
	private double yPosition;
	private double xSpeed;
	private double ySpeed;
	
	private boolean stopped;
	
	public Ball()
	{
		xPosition = 130;
		yPosition = 430;
		xSpeed = 0;
		ySpeed = 0;
		
		stopped = false;
	}

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	

}
