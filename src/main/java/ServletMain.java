import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Turtle;
import service.Race;

@WebServlet("/race")
public class ServletMain extends HttpServlet {
	
	TurtleThread turtleThread;
	
	@Override
	public void init() {
		
		turtleThread = new TurtleThread();
		turtleThread.start();

	}
	
	public void destroy() {
		
		turtleThread.interrupt();
	}
	
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		response.getWriter().println("Here Be Turtles...(check log)");
	}

}

