import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dataaccess.DataStorage;
import dataaccess.DataStorageFactory;
import domain.Turtle;
import service.Race;

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
		
		String userRequest = request.getParameter("get");
		
		if (userRequest == null  ) {
			response.setContentType("text/plain");
			response.getWriter().println("Here Be Turtles...(check log)");
		} else if (userRequest == "turtles") {
			response.getWriter().println(getAllTurtlesJson());						
		} else {
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
	
	
	
	
	


}

