package com.patterns.visitor;

public interface Visitor {
	String visitDot(Dot dot);
	String visitCircle(Circle circle);
	String visitRectangle(Rectangle rectangle);
	String visitCompoundGraphic(CompoundShape compoundShape);
}
