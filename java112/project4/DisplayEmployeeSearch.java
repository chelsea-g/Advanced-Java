package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(

	name = "DisplayEmployeeSearch",
	urlPatterns = {"/employee-search"}

)

public class DisplayEmployeeSearch extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/employeeSearch.jsp";

		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher(url);

		// Forward to the employee search page
		dispatcher.forward(request, response);


  	}

}
