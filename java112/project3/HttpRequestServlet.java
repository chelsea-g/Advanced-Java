/**
 * HttpRequestServlet sets properties of the HttpRequestData
 * object and sends it's properties within a server request.
 * The jsp receives the request and sends a response
 * to the client with the desired request information
 * specified in the jsp.
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
	name = "requestServlet",
	urlPatterns = { "/request-servlet" }
)

public class HttpRequestServlet extends HttpServlet {

	/**
	 * Sets HttpRequestData properties and dispatches the
	 * data to be used in the jsp.
	 *
	 * @param request request to be sent
	 * @param response response received
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate instance of HttpRequestData
		HttpRequestData hrd = new HttpRequestData();

        // Assign all properties of HttpRequestData w/ info from request object
        hrd.setRemoteComputer(request.getRemotePort());
		hrd.setRemoteComputerAddress(request.getRemoteAddr());
		hrd.setHttpMethodOfRequest(request.getMethod());
		hrd.setRequestURI(request.getRequestURI());
		hrd.setRequestURL(request.getRequestURL());
		hrd.setRequestProtocol(request.getProtocol());
		hrd.setServerName(request.getServerName());
		hrd.setServerPortNumber(request.getServerPort());
		hrd.setServerLocale(request.getLocale());
		hrd.setUserAgent(request.getHeader("User-Agent"));

		// Display "Null" if there wasn't a query string/query parameter
		if (request.getQueryString() == null) {

			hrd.setQueryString("Null");

		} else {

			hrd.setQueryString(request.getQueryString());

		}

		if (request.getParameter("queryParameter") == null) {

			hrd.setQueryParameter("Null");

		} else {

			hrd.setQueryParameter(request.getParameter("queryParameter"));

		}


        // Add HttpRequestData instance to request as attribute
        request.setAttribute("HRD", hrd);

        // Forward to Httprequestdata JSP page specified below
		String url =  "/httpRequestData.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

	}

}
