package com.expenseTracking.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
	
	private static String formPath = "./src/main/resources/form.txt";
//	private static String formPath = new File("json.txt").getAbsolutePath();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void write(Object obj) {
		try {
//			File file = 
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			
			
			FileWriter myWriter = new FileWriter(formPath);
			myWriter.write(json);
		    myWriter.close();
			
			
//			mapper.writeValue(new File(formPath), json);
//			try {
//				FileWriter myWriter = new FileWriter(formPath);
//			      myWriter.write("Files in Java might be tricky, but it is fun enough!");
//			      myWriter.close();
//			      System.out.println("Successfully wrote to the file.");
//			      System.out.println(formPath);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
		     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static <T> T read(Class<T> targetClass) {
		try {
			T parsed = mapper.readValue(new File(formPath), targetClass);
			return parsed;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
