package com.patterns.visitor;

public class Dot implements Shape {
	private int id;
	private int x;
	private int y;
	
	public Dot() {
	}
	
	public Dot(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String accept(Visitor visitor) {
		return visitor.visitDot(this);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
