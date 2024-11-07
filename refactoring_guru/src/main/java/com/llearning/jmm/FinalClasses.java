package com.llearning.jmm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinalClasses {
	private final int x;
	private final String y;
	private final List<Integer> list;
	
	public FinalClasses(int x, String y, List<Integer> list) {
		super();
		this.x = x;
		this.y = y;
//		this.list = List.of(list.toArray(new Integer[0]));
		this.list = List.copyOf(list);
//		List<Integer> temp = new ArrayList<>();
//		Collections.copy(list, temp);
//		this.list = Collections.unmodifiableList(list);
	}
	
    @Override
	public String toString() {
		return "FinalClasses [x=" + x + ", y=" + y + ", list=" + list + "]";
	}

	public static void main(String[] args) {
    	List<Integer> list = new ArrayList<>();
    	list.add(1);
    	list.add(2);
   
		FinalClasses finalClasses = new FinalClasses(5, "any", list);
		list.add(3);
		list.add(3);
		System.out.println(finalClasses.toString());
		
		String name1 = "one";
		String name2 = new String("one");
		System.out.println(name1 == name2);
	}

}
