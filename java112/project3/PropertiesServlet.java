/**
 * PropertiesServlet loads the project3 properties
 * file and sends the properties within a server request.
 * The jsp receives the request and sends a response
 * to the client with the desired information specified in
 * the jsp.
 *
 * @author Chelsea Greger
 * @since v1.0
 */


package java112.project3;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
	 name = "project3PropertiesServlet",
	 urlPatterns = { "/project3-properties" }
)

public class PropertiesServlet extends HttpServlet {

	// Instance variables
	private static final String PROPERTIES_FILE_PATH = "/project3.properties";
    private Properties properties;

	/**
	 * Dispatches properties in properties file
	 * to be used in the jsp.
	 *
	 * @param request request to be sent
	 * @param response request received
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Add properties reference to the request object as an attribute
        request.setAttribute("Properties", properties);

        // Forward to the Properties JSP page specified below
        String url =  "/projectProperties.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

    }
    /**
     * Initializes resources for the servlet.
     *
     * @throws Servlet Exception
     */
	public void init() throws ServletException {

        loadProperties(PROPERTIES_FILE_PATH);

    }

	/**
	 * Loads the properties from the properties file.
	 *
	 * @param propertiesFilePath path to the properties file
	 */
	public void loadProperties(String propertiesFilePath)  {

		// Initialize properties
        properties = new Properties();

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
