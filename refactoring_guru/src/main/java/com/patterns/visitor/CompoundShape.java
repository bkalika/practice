package com.patterns.visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {
	public int id;
	public List<Shape> children = new ArrayList<>();

	public CompoundShape(int id) {
		this.id = id;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public String accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visitCompoundGraphic(this);
	}

	public int getId() {
		return id;
	}

	public void add(Shape shape) {
		children.add(shape);
	}
}
