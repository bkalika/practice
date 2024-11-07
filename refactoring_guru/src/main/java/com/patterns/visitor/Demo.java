package com.patterns.visitor;

public class Demo {
	public static void main(String[] args) {
		Dot dot = new Dot(1, 10, 55);
		Circle circle = new Circle(2, 23, 15, 10);
		Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

		CompoundShape cs = new CompoundShape(4);

		cs.add(dot);
		cs.add(circle);
		cs.add(rectangle);

		CompoundShape cs2 = new CompoundShape(5);
		cs2.add(dot);
		cs.add(cs2);

		export(circle, cs);

	}

	private static void export(Shape... shapes) {
		XMLExportVisitor exportVisitor = new XMLExportVisitor();
		System.out.println(exportVisitor.export(shapes));
	}
}
