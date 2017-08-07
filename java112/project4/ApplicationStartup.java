package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java112.employee.*;

@WebServlet(

	name = "ApplicationStartup",
	urlPatterns = {"/project4-startup"},
	loadOnStartup = 1 // Causes this servlet to be loaded at
					  // startup and its init() method will be run

)

public class ApplicationStartup extends HttpServlet {

	private Properties properties;
	private EmployeeDirectory employeeDirectory;

	public void init() {

		// Load the properties object
		loadProperties("/project4.properties");

		// Create the EmployeeDirectory
		employeeDirectory = new EmployeeDirectory(properties);

		// Add properties and employeeDirectory to the ServletContext
		getServletContext().setAttribute("project4Properties", properties);
		getServletContext().setAttribute("employeeDirectory", employeeDirectory);

	}


	public void loadProperties(String propertiesFilePath) {

		// Create a new instance of the Properties object
		properties = new Properties();

		// Load the properties file
        try {

            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {

            System.out.println("Can't load the properties file");

            ioe.printStackTrace();

        } catch (Exception e) {

            System.out.println("Problem: " + e);

            e.printStackTrace();

        }

	}

}
