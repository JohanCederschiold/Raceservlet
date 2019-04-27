package dataaccess;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	public Map<Integer, String> getLastRaceResult() throws ClassNotFoundException, SQLException {
		DButil db = DButil.getInstance();
		return db.getLatestRaceResults();
	}

	@Override
	public Map<String, Integer> getLeaderboard() throws ClassNotFoundException, SQLException {
		DButil db = DButil.getInstance();
		return db.getLeaderboard();
	}
	
	
}

