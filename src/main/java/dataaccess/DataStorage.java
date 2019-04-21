package dataaccess;


import java.sql.SQLException;
import java.util.List;
import domain.Turtle;
import service.Racecontender;

public interface DataStorage {
	
	public void registerResults(List <Racecontender> contenders, int raceId ) throws ClassNotFoundException, SQLException;
	
	public List<Turtle> getAllTurtles() throws ClassNotFoundException, SQLException;
	
	public void registerRace () throws ClassNotFoundException, SQLException;
	
	public int getLastestRaceId () throws ClassNotFoundException, SQLException;
	
	public void instantiateTurtles () throws ClassNotFoundException, SQLException;
	
	public List<Turtle> getLastRaceResult () throws ClassNotFoundException, SQLException;
		
		

	


	

}
