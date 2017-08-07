import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;


@WebServlet(
	name = "MvcGameServlet", 
	urlPatterns = { "/MVCdemo", "/mvcgame" }
)


public class MvcGameServlet extends HttpServlet {
	
	private static final String PROPERTIES_FILE_PATH = "/mvc-game.properties";
    private Properties properties;
	private ArrayList<Scenario> scenarios;
	private ArrayList<String> fileLines;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
				 
		Scenario scenario = new Scenario();
				 
		scenario.setImg("images/MvcGame/forest-path.jpg");
		scenario.setFlavorText("You are lost in the forest. You find a path that looks promising.");
		scenario.setOption1("Go left.");
		scenario.setOutcome1("Oh no! A tree fell on you! Game over.");
		scenario.setOption2("Go right.");
		scenario.setOutcome2("Oh no! A tree fell on you! Game over.");
		scenario.setOption3("Go straight.");
		scenario.setOutcome3("true");
		scenarios.add(scenario);
				 
		request.setAttribute("Scenario1", scenarios.get(0));
		
		String url = "/MvcGame.jsp";
 
        RequestDispatcher dispatcher 
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
		
	}
					  
	public void init() throws ServletException {

        loadProperties(PROPERTIES_FILE_PATH);
		loadScenariosFile(properties.getProperty("scenarios.file"));
		log(properties.getProperty("scenarios.file"));
		generateScenarios();

    }
					  
	public void loadScenariosFile(String scenariosFile) {
		
		fileLines = new ArrayList<String>();
		
		ServletContext context = getServletContext();
		
		
			
        try (InputStream is = context.getResourceAsStream("scenarios.txt")) {
			
			BufferedReader reader = null;
			
            if (is != null) {
				InputStreamReader isr = new InputStreamReader(is);
				reader = new BufferedReader(isr);
				
			}
			
            while (reader.ready()) {
             	
                fileLines.add(reader.readLine());
                
            }
        
        } catch (FileNotFoundException fileNotFoundException) {
            
            log("There was a problem opening the file.");
            
            fileNotFoundException.printStackTrace();
        
        } catch (IOException ioException) {
            
            log("There was a problem reading the file.");
            
            ioException.printStackTrace();
            
        } catch (Exception exception) {
         
            log("There was some other problem.");
            
            exception.printStackTrace();
        
        }
		
	}
					  
	public void generateScenarios() {
		
		scenarios = new ArrayList<Scenario>();
			
		for (int i = 0; i < fileLines.size(); i += 8) {
			
			Scenario scenario = new Scenario();
			
			scenario.setImg(fileLines.get(i));
			scenario.setFlavorText(fileLines.get(i+1));
			scenario.setOption1(fileLines.get(i+2));
			scenario.setOutcome1(fileLines.get(i+3));
			scenario.setOption2(fileLines.get(i+4));
			scenario.setOutcome2(fileLines.get(i+5));
			scenario.setOption3(fileLines.get(i+6));
			scenario.setOutcome3(fileLines.get(i+7));
			
			scenarios.add(scenario);
			
		}
			
	}

    public void loadProperties(String propertiesFilePath)  {

        properties = new Properties();

        try {

            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));

        } catch (IOException ioe) {

            log("Can't load the properties file");

            ioe.printStackTrace();

        } catch (Exception e) {

            log("Problem: " + e);

            e.printStackTrace();

        }

    }
}