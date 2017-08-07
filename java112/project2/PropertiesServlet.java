package java112.project2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "Properties Servlet",
    urlPatterns = {"/propertiesservlet", "/PropertiesServlet"}
)

public class PropertiesServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "/project2.properties";
    private Properties properties;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

		//Open the writer
        PrintWriter  out  = response.getWriter();

        out.print("<HTML>");
        out.print("<HEAD><TITLE>Properties Servlet</TITLE></HEAD>");
        out.print("<BODY>");
        out.print("<h1>Properties Servlet</h1>");
		out.print("<table cellpadding=\"5px\" border=\"solid\"><tr><td>Servlet Author: </td><td>" + properties.getProperty("author.name") + "</td></tr>");
		out.print("<tr><td>Author Email: </td><td>" + properties.getProperty("author.email") + "</td></tr>");
		out.print("<tr><td>Course: </td><td>" + properties.getProperty("course.title") + "</td></tr>");
		out.print("<tr><td>Course Meeting Time: </td><td>" + properties.getProperty("course.meetings") + "</td></tr>");
		out.print("<tr><td>Instructor: </td><td>" + properties.getProperty("instructor.name") + "</td></tr>");
		out.print("<tr><td>Project Description: </td><td>" + properties.getProperty("project.description") + "</td></tr></table>");
		out.print("</br><a href=\"/java112\">Back to Home Page</a></br>");
        out.print("</BODY>");
        out.print("</HTML>");

		//Close the writer
        out.close();

    }

    public void init() throws ServletException {

        loadProperties(PROPERTIES_FILE_PATH);

    }

    public void loadProperties(String propertiesFilePath)  {

        properties = new Properties();

        try {

            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {

            System.out.println("Can't load the properties file");

            ioe.printStackTrace();

        } catch (Exception e) {

            System.out.println("Problem: " + e);

            e.printStackTrace();

        }

    }



}
