package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(

	name = "EmployeeSearchResults",
	urlPatterns = {"/employee-search-results"}

)

public class EmployeeSearchResults extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = getServletContext();

		// Access the applications employee directory object
		EmployeeDirectory employeeDirectory =
				(EmployeeDirectory) context.getAttribute("employeeDirectory");
		HttpSession session = request.getSession();
	    String url = "/employeeSearchResults.jsp";
		RequestDispatcher dispatcher = context.getRequestDispatcher(url);

		// Get form data
		String searchTerm = request.getParameter("searchTerm");
		String searchType = request.getParameter("searchType");

		// Create a new search object for this specific search query
		Search search = new Search(searchTerm, searchType);

		// Search type is by ID, search by ID.
		if (search.getSearchType().equals("ID")) {

			employeeDirectory.searchEmployeesByID(search);

		// Search type is by last name, search by last name.
		} else {

			employeeDirectory.searchEmployeesByLastName(search);

		}

		// Put search into session variable so the application can access it later.
		session.setAttribute("search", search);

		// Forward to the search results page.
		dispatcher.forward(request, response);

  	}

}
