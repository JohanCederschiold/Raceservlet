package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dataaccess.DataStorage;
import dataaccess.DataStorageDBconnect;
import dataaccess.DataStorageFactory;
import domain.Turtle;

public class Race {
	
	private DataStorage db;
//	private Turtle [] contenders;
	private Racecontender [] raceContenders;
	private final int SLOTS = 8;
	private final int distance = 100;
	private int [] finishingPositions = new int [3];
	
//	public Race (Turtle [] contenders) {
//		setContenders(contenders);
//		raceContenders = new Racecontender[SLOTS];
//		
//		for (int i = 0 ; i < SLOTS ; i++ ) {
//			raceContenders[i] = new Racecontender(this.contenders[i]);
//		}
//	}
	
	public Race () {
		
//		Instantiate Databaseobject
		db = DataStorageFactory.getStorage();
		
//		Check if tables is created (and contains turtles). If not create 8 turtles
		try {
			db.instantiateTurtles();
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

//		Set Racecontenders by getting 8 turtles.
		raceContenders = new Racecontender[SLOTS];
		List<Turtle> allTurtles = null;
		try {
			allTurtles = db.getAllTurtles();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0 ; i < SLOTS ; i++ ) {
			raceContenders[i] = new Racecontender(allTurtles.get(i));
		}
		
	}

//	public Turtle[] getContenders() {
//		return contenders;
//	}
	
	public int [] getFinishingPosition() {
		return finishingPositions;
	}

//	public void setContenders(Turtle[] contenders) {
//
//		
//		if (contenders.length == SLOTS) {
//			this.contenders = contenders;
//		} else if (contenders.length > 3) {
//			this.contenders = Arrays.copyOfRange(contenders, 0, 2);
//		} else {
//			this.contenders = Arrays.copyOf(contenders, 3);
//			for (int i = contenders.length ; i < SLOTS ; i++ ) {
//				System.out.println("called");
//				this.contenders[i] = new Turtle("DefaulTurtle" + i, 7, 7, 7);
//			}
//		}
//	}
	
	
	public void startRace () {

//		DataStorageDBconnect db = new DataStorageDBconnect();
				
		int raceNo = 0;
		try {
			db.registerRace();
			raceNo = db.getLastestRaceId();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		boolean raceCompleted = false;
		int lap = 0;
		
		while ( !raceCompleted ) {

			for (int i = 0 ; i < this.raceContenders.length ; i++ ) {
				
				raceContenders[i].goDistance();
				
				if (raceContenders[i].getDistance() >= distance ) {
					raceCompleted = true;
				}

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Lap: " + ++lap);
			List<Racecontender>currentStandings = getCurrentPositions();
			System.out.printf("%s in the lead. %s in second and %s in third\n", 
					currentStandings.get(0).getTurtle().getName(),
					currentStandings.get(1).getTurtle().getName(),
					currentStandings.get(2).getTurtle().getName());
			
	
		}
		
		List<Racecontender> finalStandings = getCurrentPositions();
		for (int i = 0 ; i < SLOTS ; i++ ) {
			System.out.printf("%d: %s with a distance of %d\n", i + 1, 
					finalStandings.get(i).getTurtle().getName(), finalStandings.get(i).getDistance());
		}
		
		
		

		try {
			db.registerResults(setPointsFromRace(getCurrentPositions()), raceNo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		
	}
	
	public List<Racecontender> getCurrentPositions () {
		List<Racecontender> contendersAsList = Arrays.asList(raceContenders);
		return contendersAsList.stream().sorted((a, b) -> b.getDistance() - a.getDistance()).collect(Collectors.toList());
	}
	
	private List<Racecontender> setPointsFromRace (List<Racecontender> contenders) {
		List<Racecontender> contendersWithPoints = contenders;
		int [] points = {12, 10, 8, 5, 4, 3, 2, 1};
		for (int i = 0 ; i < points.length ; i++ ) {
			contendersWithPoints.get(i).setPoints(points[i]);
		}
		return contendersWithPoints;
	}
	
	
	

}

