import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dataaccess.DataStorage;
import dataaccess.DataStorageFactory;
import domain.Turtle;

@WebServlet("/race")
public class ServletMain extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TurtleThread turtleThread;
	private DataStorage ds;
	
	@Override
	public void init() {
		
		ds = DataStorageFactory.getStorage();
		turtleThread = new TurtleThread();
		turtleThread.start();

	}
	
	public void destroy() {
		
		turtleThread.interrupt();
	}
	
	
	
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080/*");
	    response.setHeader("Access-Control-Allow-Methods", "GET");
		
		String userRequest = request.getParameter("get");
		
		if (userRequest == null  ) {
			response.setContentType("text/plain");
			response.getWriter().println("Here Be Turtles...(check log)");
		} else if (userRequest.equals("turtles")) {
			response.setContentType("text/json");
			response.getWriter().println(getAllTurtlesJson());						
		} else if (userRequest.equals("latest")) {
			response.setContentType("application/json");
			response.getWriter().println(getLatestRaceResults());
		} else if (userRequest.equals("leaders")) {
			response.setContentType("application/json");
			response.getWriter().println(getLeaderboard());
		}else {
			response.getWriter().println(userRequest);
		}
		
		
	}
	
	
	
	public JSONArray getAllTurtlesJson () {
		
		
		JSONArray jsonarray = new JSONArray();
		
		try {
			for (Turtle turtle : ds.getAllTurtles()) {
				JSONObject jo = new JSONObject();
				jo.put("name", turtle.getName());
				jo.put("speed", turtle.getSpeed());
				jo.put("id", turtle.getId());
				jsonarray.put(jo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jsonarray;
	}
	
	public JSONArray getLatestRaceResults () {
		
		JSONArray jsonArray = new JSONArray();
		
		try {
			Map<Integer, String> raceResults = ds.getLastRaceResult();
			
			for (Map.Entry<Integer, String> entry : raceResults.entrySet()) {
				JSONObject jo = new JSONObject();
				jo.put("Points", entry.getKey());
				jo.put("Turtle", entry.getValue());
				jsonArray.put(jo);			
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		
		
		return jsonArray;
	}
	
	
	public JSONArray getLeaderboard () {
		
		JSONArray jsonArray = new JSONArray();
		
		try {
			Map<String, Integer> raceResults = ds.getLeaderboard();
			
			for (Map.Entry<String, Integer> entry : raceResults.entrySet()) {
				JSONObject jo = new JSONObject();
				jo.put("Turtle", entry.getKey());
				jo.put("Totalpoints", entry.getValue());
				jsonArray.put(jo);			
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		
		
		return jsonArray;
	}
	
	
	
	
	
	
	
	


}

