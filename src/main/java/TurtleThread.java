import Domain.Turtle;
import service.Race;

public class TurtleThread extends Thread {
	
	public void run() {
		
		while (true) {
			
//			Turtle t1 = new Turtle("Razor Rob",8, 8, 5);
//			Turtle t2 = new Turtle("Butchering Betty",7, 9, 5);
//			Turtle t3 = new Turtle("Dronald Drumpf",6, 10, 5);
//			Turtle t4 = new Turtle("Glutenous Greg",7, 10, 5);
//			Turtle t5 = new Turtle("Lucifer Larry",8, 10, 5);
//			Turtle t6 = new Turtle("Currylike Ceasar",9, 10, 5);
//			Turtle t7 = new Turtle("Chaotic Carl",8, 10, 5);
//			Turtle t8 = new Turtle("Elmond Smusk",7, 10, 5);
//			
//			Turtle [] myGuys = new Turtle [8];
//
//			myGuys[0] = t1;
//			myGuys[1] = t2;
//			myGuys[2] = t3;
//			myGuys[3] = t4;	
//			myGuys[4] = t5;	
//			myGuys[5] = t6;	
//			myGuys[6] = t7;
//			myGuys[7] = t8;

			Race race = new Race ();
			
			race.startRace();
			
			
			
			try {
				Thread.sleep(1000 * 5 * 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

