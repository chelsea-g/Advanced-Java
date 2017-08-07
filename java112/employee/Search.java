/**
 * Search model that holds and accesses information
 * about a user's search placed from the
 * Employee Search page.
 *
 * @author Chelsea Greger
 * @since v1.0
 */
package java112.employee;

import java.util.*;

public class Search {

	// Declare instance variables
	private String searchType;
	private String searchTerm;
	private ArrayList<Employee> results;
	private boolean employeesFound;

	/**
	 * Empty constructor.
	 */
	public Search() { }

	/**
	 * Search constructor that creates the ArrayList
	 * of found employees and defines the searchTerm and
	 * searchType.
	 *
	 * @param searchTerm term that the user wants to query.
	 * @param searchType type of search that the user wants to query.
	 */
	public Search(String searchTerm, String searchType) {

		this();

		results = new ArrayList<Employee>();

		this.searchTerm = searchTerm;
		this.searchType = searchType;

	}

	/**
	 * Adds a found employee to the results list.
	 *
	 * @param employee found in the results of the query.
	 */
	public void addFoundEmployee(Employee employee) {

		results.add(employee);

	}

	/**
	* Returns value of searchType
	* @return
	*/
	public String getSearchType() {
		return searchType;
	}

	/**
	* Returns value of searchTerm
	* @return
	*/
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	* Returns value of results
	* @return
	*/
	public ArrayList<Employee> getResults() {
		return results;
	}

	/**
	* Returns value of employeesFound
	* True when employees are found.
	* False when no employees are found.
	*
	* @return
	*/
	public boolean isEmployeesFound() {
		return employeesFound;
	}

	/**
	* Sets new value of searchType
	* @param
	*/
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	* Sets new value of searchTerm
	* @param
	*/
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	* Sets new value of results
	* @param
	*/
	public void setResults(ArrayList<Employee> results) {
		this.results = results;
	}

	/**
	* Sets new value of employeesFound
	* @param
	*/
	public void setEmployeesFound(boolean employeesFound) {
		this.employeesFound = employeesFound;
	}

}
