package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "Project4SessionLab",
    urlPatterns = {"/project4sessionlab"}
)

public class Project4SessionLab extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Integer sessionCounter;

		sessionCounter = (Integer) session.getAttribute("project4SessionCounter");

		if (sessionCounter == null) {

			sessionCounter = new Integer(1);

			session.setAttribute("project4SessionCounter", sessionCounter);

		} else {

			sessionCounter++;

			session.setAttribute("project4SessionCounter", sessionCounter);

		}

		String url = "/project4Session.jsp";

		RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher(url);

		dispatcher.forward(request, response);

	}

}
