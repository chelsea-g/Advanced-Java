package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(

	name = "DisplayEmployeeAdd",
	urlPatterns = {"/employee-add"}

)

public class DisplayEmployeeAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/employeeAdd.jsp";
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher(url);

		// forward to the add employee page
		dispatcher.forward(request, response);

  	}

}
