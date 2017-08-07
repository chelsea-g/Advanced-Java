package java112.project2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "First112Servlet",
    urlPatterns = {"/first", "/First112Servlet"}
)

public class First112Servlet extends HttpServlet {

    private static final String AUTHOR_NAME = "Chelsea Greger";
    private static final String COURSE_TITLE = "Advanced Java Fall 2016";
    private static final String IMG_SRC = "images/javaservlets.jpg";
    private static final String HOME_PAGE_PATH = "/java112";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        //Open the writer
        PrintWriter  out  = response.getWriter();

        out.print("<HTML>");
        out.print("<HEAD><TITLE>First112Servlet</TITLE></HEAD>");
        out.print("<BODY>");
        out.print("<h1>First112Servlet</h1>");

        out.print("<p>Name: " + AUTHOR_NAME + "</br>");
        out.print("Course: " + COURSE_TITLE + "</br>");
        out.print("<img src=\"" + IMG_SRC + "\"/></br>");
        out.print("<a href=\"" + HOME_PAGE_PATH + "\">Back to Home Page</a></br></p>");

        out.print("</BODY>");
        out.print("</HTML>");

        //Close the writer
        out.close();

    }


}
