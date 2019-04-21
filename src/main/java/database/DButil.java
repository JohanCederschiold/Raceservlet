package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import domain.Turtle;
import service.Racecontender;

public class DButil {
	
	private static DButil instance;
	private Connection connection;
	private String connectionUrl = "jdbc:postgresql://postgres/";
	private String driver = "org.postgresql.Driver";
	
	
//	Private constructor(for singleton)
	private DButil () {
		
	}
	
//	Public getInstance (for singleton)
	public static DButil getInstance () {
		if (instance == null) {
			instance = new DButil();
		}
		return instance;
	}
	
	
	private void setUpConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		Properties properties = new Properties();
		properties.setProperty("user", "postgres");
		connection = DriverManager.getConnection(connectionUrl, properties);
		
	}
	
//	Metod som registrerar raceresults
	public void registerResults(List <Racecontender> contenders, int raceId ) throws ClassNotFoundException, SQLException  {
		
		setUpConnection();
		
//		Skapa en tabell om ingen redan existerar
		Statement statement = connection.createStatement();
		statement.execute("create table if not exists raceresults (RaceId integer, TurtleId integer, Position integer)");
			
		for (int i = 0 ; i < contenders.size() ; i++ ) {
//		Lgg till poster
			PreparedStatement prepStatement = connection.prepareStatement("insert into raceresults values(?, ? , ?)");
			prepStatement.setInt(1, raceId);
			prepStatement.setInt(2, contenders.get(i).getTurtle().getId());
			prepStatement.setInt(3, contenders.get(i).getPoints());
			prepStatement.execute();
		}
			
		connection.close();
		System.out.println(connection.isClosed());

	}
	
//	Get all registered turtles. 
	public List<Turtle> getAllTurtles() throws ClassNotFoundException, SQLException {
		
		List<Turtle> allTurtles = new ArrayList<>();
		
		setUpConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from turtle");
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
			int id = results.getInt(1);
			String name = results.getString(2);
			int speed = results.getInt(3);
			int stamina = results.getInt(4);
			int luck = results.getInt(5); 
			allTurtles.add(new Turtle(id, name, speed, stamina, luck));
		}
		
		connection.close();
		
		return allTurtles;
		
	}
	
//	Resgisters a race with date and id.
	public void registerRace () throws ClassNotFoundException, SQLException {
		
		setUpConnection();
		
		Statement statement = connection.createStatement();
		statement.execute("create table if not exists Race(Race_id integer generated by default as identity,"
				+ "Type text,"
				+ "Datum date default current_date , "
				+ "Tid time(0) default current_time)");
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Race (Type) values (?)");
		preparedStatement.setString(1, "Race");
		preparedStatement.execute();
		
		connection.close();
		System.out.println(connection.isClosed());

	}
	
	
//	Gets the id of the latest registered race. 
	public int getLastestRaceId () throws ClassNotFoundException, SQLException {
		
		setUpConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select max(Race_id) from Race");
		ResultSet results = preparedStatement.executeQuery();
		
		int maxId = 0;
		
		while (results.next()) {
			maxId = results.getInt(1);
		}
		
		connection.close();
		return maxId;
		
	}
	
//	Kontrollerar om Turtles finns registrerade sedan innan. Om inte skapas 8 turtles.
	public void instantiateTurtles () throws ClassNotFoundException, SQLException {
		
		setUpConnection();
		
		Statement statement = connection.createStatement();
		statement.execute("create table if not exists Turtle(Turtle_id integer generated by default as identity,"
				+ "Name text,"
				+ "Speed integer, "
				+ "Stamina integer, "
				+ "Luck integer)");
		
		connection.close();
		
//		letThereBeTurtles();
		
		if (turtlesEmpty()) {
			letThereBeTurtles();
		} else {
			System.out.println("No need to create turtles");
		}
		
	}
	
	private boolean turtlesEmpty () throws ClassNotFoundException, SQLException {
		
		setUpConnection();
		
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery("select count(*) from Turtle");
		
		int noPosts = 0;
		
		while ( results.next() ) {
			noPosts = results.getInt(1);
		}
		
		return noPosts < 1;
		
	}
	
	
	private void letThereBeTurtles () throws ClassNotFoundException, SQLException {
		
		
		List<Turtle> theFirstTurtles = new ArrayList<>();
		
		theFirstTurtles.add(new Turtle("Razor Rob",8, 8, 5));
		theFirstTurtles.add(new Turtle("Butchering Betty",7, 9, 5));
		theFirstTurtles.add(new Turtle("Dronald Drumpf",6, 10, 5));
		theFirstTurtles.add(new Turtle("Glutenous Greg",7, 10, 5));
		theFirstTurtles.add(new Turtle("Lucifer Larry",8, 10, 5));
		theFirstTurtles.add(new Turtle("Currylike Ceasar",9, 10, 5));
		theFirstTurtles.add(new Turtle("Chaotic Carl",8, 10, 5));
		theFirstTurtles.add(new Turtle("Elmond Smusk",7, 10, 5));
		
		setUpConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into Turtle (Name, Speed, Stamina, Luck) values (?, ?, ?, ?)");
		
		for (Turtle turtle : theFirstTurtles) {
			preparedStatement.setString(1, turtle.getName());
			preparedStatement.setInt(2, turtle.getSpeed());
			preparedStatement.setInt(3, turtle.getStamina());
			preparedStatement.setInt(4, turtle.getLuck());
			preparedStatement.execute();
		}
		
		System.out.println("Turtles created!!!");
		
		connection.close();
		
	}
	
	
//	public Map<Integer,String> getLatestRaceResults () throws ClassNotFoundException, SQLException {
//		
//		setUpConnection();
//		Map<Integer, String> raceResults = new TreeMap<>();
//		
//		PreparedStatement statement = connection.prepareStatement("select * "
//				+ "from raceresults where raceid = ?");
//		statement.setInt(1, getLastestRaceId());
//		ResultSet result = statement.executeQuery();
//	
//		int counter = 0;
//		
//		while (result.next()) {
//			raceResults.put(++counter, arg1)
//			
//			
//		}
//		
//		
//		return null;
//		
//	}
	
	
	  
}
