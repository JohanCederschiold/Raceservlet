package dataaccess;

import java.sql.SQLException;
import java.util.List;

import database.DButil;
import domain.Turtle;
import service.Racecontender;

public class DataStorageDBconnect implements DataStorage {

	@Override
	public void registerResults(List<Racecontender> contenders, int raceId)
			throws ClassNotFoundException, SQLException {
		
		DButil db = DButil.getInstance();
		db.registerResults(contenders, raceId);
		
	}

	@Override
	public List<Turtle> getAllTurtles() throws ClassNotFoundException, SQLException {
		
		DButil db = DButil.getInstance();
		return db.getAllTurtles();
	}

	@Override
	public void registerRace() throws ClassNotFoundException, SQLException {
		DButil db = DButil.getInstance();
		db.registerRace();
		
	}

	@Override
	public int getLastestRaceId() throws ClassNotFoundException, SQLException {
		DButil db = DButil.getInstance();
		return db.getLastestRaceId();
	}

	@Override
	public void instantiateTurtles() throws ClassNotFoundException, SQLException {
		DButil db = DButil.getInstance();
		db.instantiateTurtles();
	}

	@Override
	public List<Turtle> getLastRaceResult() throws ClassNotFoundException, SQLException {
//		TODO: add connection do DButil when method is created. 
		
		return null;
	}
	
	
}

