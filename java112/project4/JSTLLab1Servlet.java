package java112.project4;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "JSTLLab1Servlet",
    urlPatterns = {"/jstllab2"}
)

public class JSTLLab1Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    List words = new ArrayList();

		words.add("Hello");
		words.add("World");
		words.add("How");
		words.add("Are");
		words.add("You");
		words.add("???");

		request.setAttribute("words", words);

		String url = "/jstl-lab2.jsp";
        
		RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher(url);

		dispatcher.forward(request, response);

	}

}
