package com.expenseTracking.util;

import java.lang.reflect.Field;

import com.expenseTracking.exception.InvalidException;

public class PrintUtil {
	public static StringBuilder tablePrint(Object[] objects) {
		
		return null;
	}
	
	public static void printObjects(Object[] objects) throws InvalidException, IllegalArgumentException, IllegalAccessException {
		if(objects.length < 1) {
			throw new InvalidException("object list is empty");
		}

		
        Field[] fields = objects[0].getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        int[] maxColLen = new int[fields.length];
        for (int i = 0; i < maxColLen.length; i++) {
        	fieldNames[i] = fields[i].getName();
        	maxColLen[i] = fieldNames[i].length();
        }
        		
		for (int i =0; i < objects.length; i++) { // for every object
			Object curObj = objects[i]; // grab the cur object
			for(int j = 0; j < fields.length; j++) { // for every field
				Object value = fields[j].get(curObj); // grab the objects field value
				int valLen = value.toString().length();
				if(valLen > maxColLen[j]) { // if the values length is greater than the current max
					maxColLen[j] = valLen; // update the max
				}
			}
		}
		
		int breakerLen = 1; // add 1 for last |
		for(int len : maxColLen) {
			breakerLen += (len + 3); // add padding plus 3 for | and one surrounding space on each side
		}
		
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			row.append("| " + ColorUtil.colorMap.get("green") + "%-" + maxColLen[i] + "s " + ColorUtil.colorMap.get("reset"));
		}
		row.append("|\n");
		
		String breaker =  "=".repeat(breakerLen) + "\n";
		String header =  String.format(row.toString(), fieldNames);
		
		StringBuilder table = new StringBuilder();
		table.append(breaker);
		table.append(header);
		table.append(breaker);
						
		for(Object curObj : objects) {
			String[] attributes = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				String value = fields[i].get(curObj).toString();
//				if (fields[i].getName().equals("amount")) {
//					total += Double.valueOf(value);
//				}
				attributes[i] = value;
			}
			table.append(String.format(row.toString(), attributes));
		}
		table.append(breaker);
		System.out.println(table);
		
		
//		
//		StringBuilder table = new StringBuilder();
//		
//		table.append
		
		
		
		
	}
	
	public static void commandPrint(String[] commands) {
		int commandMaxLen = 0;
		int maxIndexLen = String.valueOf(commands.length).length();
		for (String command : commands) {
			int CommandLen = command.length();
			if (CommandLen > commandMaxLen) {
				commandMaxLen = CommandLen;
			}
		}
		int breakerLen = (maxIndexLen + 3) + (3 + commandMaxLen) + 1;
		
		String commandInsert =  ColorUtil.color("green", "%-" + commandMaxLen + "s");
		String indexInsert = ColorUtil.color("lightBlue", "%-" + maxIndexLen + "s");
		
		
		StringBuilder commandPrint = new StringBuilder();
		String breaker =  "=".repeat(breakerLen) + "\n";
		
		commandPrint.append(breaker);
		for (int i=0; i<commands.length; i++) {
			String row = String.format("| " + indexInsert + " | " + commandInsert  + " |\n", i+1, commands[i]);
			commandPrint.append( row );
		}
		commandPrint.append(breaker);
		System.out.print(commandPrint);
	}
	
	
	
//	public static StringBuilder tableFormat(String[] commands){
//    	int commandMaxLen = 25;
//    	int breakerLen = 36;
//    	StringBuilder table = new StringBuilder();
//    	String breaker = "=".repeat(breakerLen) + "\n";
//    	table.append(breaker);
//    	for (int i = 0; i < commands.length; i++) {
//    		table.append( String.format("| " + TextColor.blueText() + "%-" + 3 +"s " + TextColor.clearText() + " | " + TextColor.blueText() + "%-" + commandMaxLen +"s " + TextColor.clearText() + "|\n", i+1, commands[i]) );
//    	}
//    	table.append(breaker);
//		return table;
//	}
}
