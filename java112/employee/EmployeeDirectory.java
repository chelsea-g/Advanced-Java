/**
 * Manages the application's database
 * queries, updates, and connections.
 *
 * @author Chelsea Greger
 * @since v1.0
 */
package java112.employee;

import java.util.*;
import java.sql.*;
import java.io.*;

public class EmployeeDirectory {

	// Declare instance variables
	private Properties properties;

	/**
	 * Empty constructor.
	 */
	public EmployeeDirectory() { }

	/**
	 * Properties constructor.
	 *
	 * @param  properties application's properties collection
	 */
	public EmployeeDirectory(Properties properties) {

		this();

		this.properties = properties;

	}

	/**
	 * Makes a connection to the employees database.
	 *
	 * @return the connection to the database.
	 */
	private Connection establishConnection() {

		Connection connection = null;

		try {

			// Initialize the database driver class
			Class.forName(properties.getProperty("database.driver"));

			// Connect to the database
			connection = DriverManager.getConnection(
					properties.getProperty("database.url"),
					properties.getProperty("database.username"),
					properties.getProperty("database.password"));

		} catch (ClassNotFoundException classNotFound) {

            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();

        } catch (Exception exception) {

            System.err.println("General Error");
            exception.printStackTrace();

        }

		return connection;

	}

	/**
	 * Adds the given employee information to the
	 * employees database.
	 *
	 * @param  firstName   Employee's first name.
	 * @param  lastName    Employee's last name.
	 * @param  socialSecurityNumber Employee's SSN.
	 * @param  department  Employee's department name.
	 * @param  roomNumber  Employee's room number.
	 * @param  phoneNumber Employee's phone number.
	 * @return  success message/error message
	 */
	public String addNewEmployee(String firstName, String lastName,
					String socialSecurityNumber, String department,
					String roomNumber, String phoneNumber) {

		// Local variables
		Connection connection = establishConnection(); // connect to the database
		PreparedStatement insertEmployee = null;
		String message = "Unable to add new employee."; // default message
														// until an employee is
														// successfully added

		try {

			// Query to insert the employee into the database.
			String queryString = "INSERT INTO employees (first_name, last_name, "
					+ "ssn, dept, room, phone) VALUES(?, ?, ?, ?, ?, ?)";

			// Prepare the insertEmployee query.
			insertEmployee = connection.prepareStatement(queryString);

			// Bind the insertEmployee query parameters.
			insertEmployee.setString(1, firstName);
	   		insertEmployee.setString(2, lastName);
	   		insertEmployee.setString(3, socialSecurityNumber);
	   		insertEmployee.setString(4, department);
	   		insertEmployee.setString(5, roomNumber);
	   		insertEmployee.setString(6, phoneNumber);

			// Execute the update.
			insertEmployee.executeUpdate();

			// If program gets to this line without throwing error,
			// the employee was successfully added.
			// Change the message to success.
			message = "Employee " + firstName + " " + lastName + " successfully added!";

		} catch (SQLException sqlException) {

            System.err.println("Error in connecting to database "
                    + sqlException);
            sqlException.printStackTrace();

		} catch (Exception exception) {

	            System.err.println("General Error");
	            exception.printStackTrace();

		} finally {

			try {

				// No result set used

				// Close the prepared statement
	            if (insertEmployee != null) {

	                insertEmployee.close();

	            }

				// Close the connection
	            if (connection != null) {

	                connection.close();

	            }

            } catch (Exception exception) {

                System.err.println("General Error");
                exception.printStackTrace();

            }

		}

		// Return the query's success/error message.
		return message;

	}

	/**
	 * Queries the employee database by ID of an
	 * employee.
	 *
	 * @param search object that holds search data
	 */
	public void searchEmployeesByID(Search search) {

		// Local variables
		Connection connection = establishConnection(); // Connect to database
		ResultSet resultSet = null;
		PreparedStatement getEmployee = null;
		Employee employee = null;
		String employeeID = search.getSearchTerm();
		String searchTerm = search.getSearchTerm();
		int rows = 0; // Rows returned is zero until
					  // rows are returned & without error

		try {

			// Query to find employee by id
			String queryString = "SELECT * FROM employees WHERE emp_id=?";

			// Prepare the getEmployee statement
			getEmployee = connection.prepareStatement(queryString);

			// Bind the getEmployee statement parameters
			getEmployee.setString(1, employeeID);

			// Execute the query
			resultSet = getEmployee.executeQuery();

			// Get results row count
			rows = getResultsRowCount(resultSet);

			// Employees were found.
			if (rows != 0) {

				// Set the employees found boolean to true.
				search.setEmployeesFound(true);

				while (resultSet.next()) {

					// Create a new employee object for each found employee
					// using data found.
					employee = createNewEmployee(resultSet);

					// Add the employee to the found employees/results list
					search.addFoundEmployee(employee);

				}

			// No employees found.
			} else {

				// Set the employees found boolean to false.
				search.setEmployeesFound(false);

			}

		} catch (SQLException sqlException) {

            System.err.println("Error in connecting to database "
                    + sqlException);
            sqlException.printStackTrace();

		} catch (Exception exception) {

				rows = 0;
	            System.err.println("General Error");
	            exception.printStackTrace();

		} finally {

            try {

				// Close the result set
				if (resultSet != null) {

                    resultSet.close();

                }

				// Close the prepare statement
                if (getEmployee != null) {

                    getEmployee.close();

                }

				// Close the connection
                if (connection != null) {

                    connection.close();

                }

            } catch (Exception exception) {

                System.err.println("General Error");
                exception.printStackTrace();

            }

		}

	}

	/**
	 * Queries the employee database by last name of an
	 * employee.
	 *
	 * @param search object that holds search data
	 */
	public void searchEmployeesByLastName(Search search) {

		// Local variables
		Connection connection = establishConnection(); // Connect to database
		ResultSet resultSet = null;
		PreparedStatement getEmployee = null;
		Employee employee = null;
		String lastName = search.getSearchTerm();
		String searchTerm = search.getSearchTerm();
		int rows = 0; // Rows returned is zero until
					  // rows are returned & without error

		try {

			//% must be escaped for query to work correctly in prepared statement
			// TODO: find out why this works...not sure why??
			lastName = lastName.replace("%", "!%");

			// Query to find employee by last name
			String queryString = "SELECT * FROM employees WHERE last_name LIKE ?";

			// Prepare the getEmployee statement
			getEmployee = connection.prepareStatement(queryString);

			// Bind the getEmployee statement parameters
			getEmployee.setString(1, lastName+ "%");

			// Execute the query
			resultSet = getEmployee.executeQuery();

			// Get results row count
			rows = getResultsRowCount(resultSet);

			// Employees were found.
			if (rows != 0) {

				// Set the employees found boolean to true.
				search.setEmployeesFound(true);

				// Create a new employee object for each found employee
				// using data found.
				while (resultSet.next()) {

					employee = createNewEmployee(resultSet);

					// Add the employee to the found employees/results list
					search.addFoundEmployee(employee);

				}

			// No employees found.
			} else {

				// Set the employees found boolean to false.
				search.setEmployeesFound(false);

			}

		} catch (SQLException sqlException) {

			System.err.println("Error in connecting to database "
					+ sqlException);
			sqlException.printStackTrace();

		} catch (Exception exception) {

				System.err.println("General Error");
				exception.printStackTrace();

				// Make sure rows is set to 0 when a general error is thrown.
				rows = 0;

		} finally {

			try {

				// Close the result set
				if (resultSet != null) {

					resultSet.close();

				}

				// Close the prepare statement
				if (getEmployee != null) {

					getEmployee.close();

				}

				// Close the connection
				if (connection != null) {

					connection.close();

				}

			} catch (Exception exception) {

				System.err.println("General Error");
				exception.printStackTrace();

			}

		}
	}


	/**
	 * Finds the number of rows in the result set. Will throw
	 * a general exception if there are zero rows.
	 *
	 * @param  results ResultSet from query.
	 * @return number of rows in the result set.
	 * @throws Exception when zero rows were found.
	 */
	public int getResultsRowCount(ResultSet resultSet)
			throws Exception {

		int rows;

		// Move the cursor to the last row in the result set
		resultSet.last();

		// Set the number of found rows to the last rows number
		rows = resultSet.getRow();

		// Move the curosr back to before the first row in the result set
		resultSet.beforeFirst();

		return rows;

	}

	/**
	 * Creates a new employee object for a found employee.
	 *
	 * @param results ResultSet from query.
	 * @throws SQLException
	 * @return the new Employee object
	 */
	public Employee createNewEmployee(ResultSet resultSet)
	 	throws SQLException {

		// Define employee data
		String employeeID = resultSet.getString("emp_id");
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		String socialSecurityNumber = resultSet.getString("ssn");
		String department = resultSet.getString("dept");
		String roomNumber = resultSet.getString("room");
		String phoneNumber = resultSet.getString("phone");

		// Create employee
		Employee employee = new Employee();

		// Populate employee data
		employee.setemployeeID(employeeID);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setSocialSecurityNumber(socialSecurityNumber);
		employee.setDepartment(department);
		employee.setRoomNumber(roomNumber);
		employee.setPhoneNumber(phoneNumber);

		return employee;

	}
}
