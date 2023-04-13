package com.cognixia;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import com.cognixia.model.control.ControLoop;

@SpringBootApplication
//public class MovieRatingAppApplication implements CommandLineRunner {
public class MovieRatingAppApplication {
//
//	@Autowired
//	private ControLoop cl;

	public static void main(String[] args) {
		
		SpringApplication.run(MovieRatingAppApplication.class, args);
//		System.out.println(cl);
//		cl.controLoop();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		boolean running = true;
//		while(running) {
//			System.out.print(cl.getCurUser() + "> ");
//			String command = cl.getSc().nextLine(); 
//			switch(command.toLowerCase()) {
//			case "login":
//				cl.login();
//				break;
//			}
//		}
//		System.out.println("THE END");
//		
//	}

}
