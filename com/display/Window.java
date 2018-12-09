package com.display;

import com.main.Tick;

import javax.swing.JFrame;

import java.awt.Dimension;

//makes window and container

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 300;
	public static int HEIGHT = 500;
	
	public Window(Tick tick)
	{
		this.setTitle("Ballz");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addKeyListener(tick);
	}
	
}
