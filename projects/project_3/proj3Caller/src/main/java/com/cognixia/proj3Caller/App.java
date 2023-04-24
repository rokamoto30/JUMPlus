package com.cognixia.proj3Caller;

import java.util.Scanner;

import com.cognixia.proj3Caller.model.Assignment;
import com.cognixia.proj3Caller.model.Course;
import com.cognixia.proj3Caller.network.ApiException;
import com.cognixia.proj3Caller.service.StudentService;
import com.cognixia.proj3Caller.service.TeacherService;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Scanner sc;
	private static String user;
	private static String role;
	
    public static void main( String[] args ) throws Exception
    {
    	production();
    }
    public static void production() {
    	sc = new Scanner(System.in);
    	while(true) {
	    	System.out.println("Enter role");
	    	String newRole = sc.nextLine();
	    	if (newRole.toLowerCase().equals("teacher")) {
	    		role = "t";
	    		break;
	    	} else if (newRole.toLowerCase().equals("student")) {
	    		role = "s";
	    		break;
	    	}
	    	System.out.println("role must be 'teacher' or 'student'");
    	}
    	switch(role) {
    	case "t":
    		teacher();
    	case "s":
    		student();
    	}
    }
    public static void student() {
    	boolean notLoggedIn = true;
    	
    	while(notLoggedIn) {
	    	System.out.println("Enter username:");
	    	String username = sc.nextLine();
	    	System.out.println("Enter password:");
	    	String password = sc.nextLine();
	    	try {
		    	user = StudentService.create(username, password, "first", "last").getUsername();
		    	notLoggedIn = false;
	    	} catch (ApiException e) {
	    		try {
					user = StudentService.login(username, password).getUsername();
					notLoggedIn = false;
				} catch (Exception e1) {
					System.out.println(e.getMessage());
				}
	    	} catch (Exception e) {
	    		System.out.println(e.getMessage());
	    	}
    	}
    	
    	boolean running = true;
		while (running) {
			System.out.print(user + "> ");
			String command = sc.nextLine();
			switch(command.toLowerCase()) {
				case "help":
					System.out.println("assignments, courses");
					break;
				case "assignments":
					System.out.println("Enter course:");
					try {
						StudentService.getAssignments(user, sc.nextLine());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
			    	break;
				case "courses":
					try {
						StudentService.getCourses(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Please type a valid input/command. For help, type help");
					break;
			}
		}
    }
    
    public static void teacher() {
    	boolean notLoggedIn = true;
    	
    	while(notLoggedIn) {
	    	System.out.println("Enter username:");
	    	String username = sc.nextLine();
	    	System.out.println("Enter password:");
	    	String password = sc.nextLine();
	    	try {
	    		System.out.println(username);
	    		System.out.println(password);
		    	user = TeacherService.create(username, password, "first", "last").getUsername();
		    	notLoggedIn = false;
	    	} catch (ApiException e) {
	    		try {
					user = TeacherService.login(username, password).getUsername();
					notLoggedIn = false;
				} catch (Exception e1) {
					System.out.println(e.getMessage());
				}
	    	} catch (Exception e) {
	    		System.out.println(e.getMessage());
	    	}
    	}
    	
    
    	boolean running = true;
		while (running) {
			System.out.print(user + "> ");
			String command = sc.nextLine();
			switch(command.toLowerCase()) {
				case "help":
					System.out.println("students, courses, create course, create assignment, remove student");
					break;
				case "students":
					System.out.println("Enter course:");
					try {
						TeacherService.getStudents(user, sc.nextLine());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
			    	break;
				case "courses":
					try {
						TeacherService.getCourses(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "create course":
					System.out.println("Enter course:");
					try {
						TeacherService.createCourse(user, sc.nextLine());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "create assignment":
					System.out.println("Enter student name");
					String studentName = sc.nextLine();
					System.out.println("Enter course name");
					String course = sc.nextLine();
					System.out.println("Enter grade");
					Double grade = Double.parseDouble(sc.nextLine());
					System.out.println("Enter weight");
					Double weight = Double.parseDouble(sc.nextLine());
					
					try {
						TeacherService.createAssignment(user, studentName, course, grade, weight);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "remove student":
					System.out.println("Enter student name");
					studentName = sc.nextLine();
					System.out.println("Enter course name");
					String courseName = sc.nextLine();
					try {
						TeacherService.removeStudent(studentName, courseName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				
				default:
					System.out.println("Please type a valid input/command. For help, type help");
					break;
			}
		}
    }
    
    public static void tester() {
    	try {
            System.out.println(StudentService.login("student1", "password").getUsername());
            StudentService.getCourses("student1");
    		//System.out.println(StudentService.create("newUser1", "password", "first", "last"));
    		StudentService.getAssignments("student1", "math");
    		
    		System.out.println(TeacherService.login("ryan", "password").getUsername());
//    		System.out.println(TeacherService.create("newTeacher1", "password", "first", "last"));
//    		TeacherService.createCourse("ryan", "cs");
//    		TeacherService.createAssignment("ryan", "student2", "math", 80.0, .25);
//    		TeacherService.createAssignment("ryan", "student3", "math", 40.0, .25);
    		TeacherService.getCourses("ryan");
    		TeacherService.getStudents("ryan", "math");
    		TeacherService.removeStudent("student3", "cs");    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}
