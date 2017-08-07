package java112.project4;

import java.io.*;
import java.sql.*;

public class JDBCPreparedStatement {

	public void runSample(String firstName, String lastName, String ssn, String dept, String room, String phone) {

		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement insertEmployee = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/student", "student", "student");

            insertEmployee = connection.prepareStatement
            		("INSERT INTO employees (first_name, last_name, ssn, dept, room, phone) VALUES"
            			+ "(?, ?, ?, ?, ?, ?)");

   			insertEmployee.setString(1, firstName);
   			insertEmployee.setString(2, lastName);
   			insertEmployee.setString(3, ssn);
   			insertEmployee.setString(4, dept);
   			insertEmployee.setString(5, room);
   			insertEmployee.setString(6, phone);

            insertEmployee.executeUpdate();

            System.out.println();

            statement = connection.createStatement();

            String queryString = "SELECT emp_id, first_name, last_name, ssn, dept, room, phone"
                    + " FROM employees " + "WHERE last_name like '"
                    + lastName + "%'";

            System.out.println("queryString: " + queryString);

            resultSet = statement.executeQuery(queryString);

            System.out.println();

            while (resultSet.next()) {
                String employeeID = resultSet.getString("emp_id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                ssn = resultSet.getString("ssn");
                dept = resultSet.getString("dept");
                room = resultSet.getString("room");
                phone = resultSet.getString("phone");

                System.out.println(" Row: "
                            + employeeID + " "
                            + firstName + " "
                            + lastName + " "
                            + ssn + " "
                            + dept + " "
                            + room + " "
                            + phone + " "
                            );
            }

            System.out.println();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columns = resultSetMetaData.getColumnCount();
            String nameOne = resultSetMetaData.getColumnName(1);
            String typeOne = resultSetMetaData.getColumnTypeName(1);
            String labelOne = resultSetMetaData.getColumnLabel(1);
            System.out.println(" Column count : " + columns);
            System.out.println(" Column 1 name : " + nameOne);
            System.out.println(" Column 1 type : " + typeOne);
            System.out.println(" Column 1 label name : " + labelOne);

            System.out.println();

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }

	}

	/**
     *  The main program for the JDBCSelectWhereExample class
     *
     *@param  args  The command line arguments
     *
     *@since
     *
     */
    public static void main(String[] args) {

        JDBCPreparedStatement employee = new JDBCPreparedStatement();

        employee.runSample(args[0], args[1], args[2], args[3], args[4], args[5]);

    }

}
