package java112.labs4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "Lab41Servlet",
    urlPatterns = {"/lab41servlet"}
)

public class Lab41Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String favoriteColor = request.getParameter("favoriteColor");

		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("favoriteColor", favoriteColor);

		String url = "/lab41.jsp";

		RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher(url);

		dispatcher.forward(request, response);

	}

}
