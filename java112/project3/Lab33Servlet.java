package java112.project3;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "Lab33Servlet",
    urlPatterns = {"/lab33", "/labthree"}
)

public class Lab33Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create a HashMap
        Map map = new HashMap();


        // Populate map
        map.put("number", 6);
        map.put("text", "This is some sample text.");
        map.put("html", "<h2>This is an h2</h2>");
        map.put("aDate", new Date());


        // Add the map to the request parameter
        request.setAttribute("myMap", map);

        // Forward to lab33.jsp
        String url =  "/lab33.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

    }

}
