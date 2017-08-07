package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(

	name = "EmployeeAddAction",
	urlPatterns = {"/employee-add-action"}

)

public class EmployeeAddAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Access the applications employee directory object
		EmployeeDirectory employeeDirectory =
				(EmployeeDirectory) getServletContext().getAttribute("employeeDirectory");

		// Get the form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String socialSecurityNumber = request.getParameter("socialSecurityNumber");
		String department = request.getParameter("department");
		String roomNumber = request.getParameter("roomNumber");
		String phoneNumber = request.getParameter("phoneNumber");

		// Get the session
		HttpSession session = request.getSession();
		String url = "/java112/employeeAdd.jsp";

		// Add the employee using form data
		String addEmployeeMessage = employeeDirectory.addNewEmployee(firstName,
				lastName, socialSecurityNumber, department, roomNumber, phoneNumber);

		// Set the employee added message to display on the add employee page
		session.setAttribute("addEmployeeMessage", addEmployeeMessage);

		// Redirect back to the add employe page
		response.sendRedirect(url);

  	}

}
