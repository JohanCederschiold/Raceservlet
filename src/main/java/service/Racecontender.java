package service;

import java.util.Random;

import domain.Turtle;

public class Racecontender {
	
	private Turtle turtle;
	private int distance;
	private boolean brokeDown = false;
	private Random random = new Random();
	private int finishingPosition;
	private int points;
	

	public Racecontender(Turtle turtle) {
		
		this.turtle = turtle;

	}
	
	public void goDistance () {
		int test = distance;
		distance += random.nextInt(turtle.getSpeed());
//		System.out.println(distance - test);
	}
	
	public int getDistance () {
		return distance;
	}
	
	public Turtle getTurtle () {
		return turtle;
	}
	
	public void setFinishingPosition (int position) {
		this.finishingPosition = position;
	}
	
	public int getFinishingPosition () {
		return finishingPosition;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString () {
		if (finishingPosition == 1 ) {
			return String.format("%s fininished 1st", turtle.getName());					
		
		} else if (finishingPosition == 2) {
			return String.format("%s fininished 2nd", turtle.getName());					
				
		} else if (finishingPosition == 3) {
			return String.format("%s fininished 3rd", turtle.getName());						
		} else {
			return String.format("%s fininished %dth", turtle.getName(), finishingPosition);					
		}
	}
	
	
	

}

