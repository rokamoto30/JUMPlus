package com.expenseTracking.util;

import java.util.HashMap;
import java.util.Map;

public class ColorUtil {
	
	public static final Map<String, String> colorMap = new HashMap<String, String>();
	static {
		colorMap.put("reset", "\u001B[0m");
		colorMap.put("black", "\u001B[30m");
		colorMap.put("red", "\u001B[31m");
		colorMap.put("green", "\u001B[32m");
		colorMap.put("yellow", "\u001B[33m");
		colorMap.put("blue", "\u001B[34m");
		colorMap.put("purple", "\u001B[35m");
		colorMap.put("cyan", "\u001B[36m");
		colorMap.put("white", "\u001B[37m");
		
		colorMap.put("lightBlack", "\033[0;90m");
		colorMap.put("lightRed", "\033[0;91m");
		colorMap.put("lightGreen", "\033[0;92m");
		colorMap.put("lightYellow", "\033[0;93m");
		colorMap.put("lightBlue", "\033[0;94m");
		colorMap.put("lightPurple", "\033[0;95m");
		colorMap.put("lightCyan", "\033[0;96m");
		colorMap.put("lightWhite", "\033[0;97m");
		
		
	}

	    
	
	public static void colorSwitch(String color) {
		if(colorMap.containsKey(color)) {
			System.out.print(colorMap.get(color));
			return;
		}
		System.out.println("Color doesnt exist");
		
	}
	public static void colorPrint(String color, String toPrint) {
		if(colorMap.containsKey(color)) {
			System.out.print(colorMap.get(color));
			System.out.print(toPrint);
			System.out.print(colorMap.get("reset"));
			return;
		}
		System.out.println("Color doesnt exist");
	}
	
	public static void colorPrintln(String color, String toPrint) {
		if(colorMap.containsKey(color)) {
			System.out.print(colorMap.get(color));
			System.out.print(toPrint);
			System.out.println(colorMap.get("reset"));
			return;
		}
		System.out.println("Color doesnt exist");
	}
	
	public static String color(String color, String toPrint) {
		if(colorMap.containsKey(color)) {
			return colorMap.get(color) + toPrint + colorMap.get("reset");
		}
		System.out.println("Color doesnt exist");
		return null;
	}
	
}
