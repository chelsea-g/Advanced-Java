package java112.project2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
    name = "LabFiveServlet",
    urlPatterns = {"/lab5", "/labfive"}
)

public class LabFiveServlet extends HttpServlet {

    private int hitCount = 0;
    private String firstAccessDate;
    private Calendar cal;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        if (hitCount == 0) {
            
            firstAccessDate = getDateTime();
            
        }
        
        hitCount++;
        
        response.setContentType("text/html");
        PrintWriter  out  = response.getWriter();
        out.print("<HTML>");
        out.print("<HEAD><TITLE>LabFiveServlet Output</TITLE></HEAD>");
        out.print("<BODY>");
        out.print("<h1>LabFiveServlet Here!</h1>");
        out.print("<h2>Total Hits: " + hitCount + "</h2>");
        out.print("<h2>First Access Date: " + firstAccessDate + "</h2>");
        out.print("<h2>Current Date: " + getDateTime() + "</h2>");
        out.print("</BODY>");
        out.print("</HTML>");
        out.close();
        
        log("DO GET: executed");
            
    }
    
    public void init() throws ServletException {
        
        log("INIT: executed");
    
    }
    
    public String getDateTime() {
    
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        
        return calendar.getTime().toString();
    
    }
            
            

}