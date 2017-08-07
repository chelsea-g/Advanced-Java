/**
 * Employee Model that holds and accesses information
 * about an employee.
 *
 * @author Chelsea Greger
 * @since v1.0
 */

package java112.employee;

public class Employee {

	// Declare instance variables
	private String employeeID;
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	private String department;
	private String roomNumber;
	private String phoneNumber;

	/**
	 * Empty constructor.
	 */
	public Employee() { }

	/**
	* Returns value of employeeID
	* @return
	*/
	public String getemployeeID() {	return employeeID; }

	/**
	* Returns value of firstName
	* @return
	*/
	public String getFirstName() { return firstName; }

	/**
	* Returns value of lastName
	* @return
	*/
	public String getLastName() { return lastName; }

	/**
	* Returns value of socialSecurityNumber
	* @return
	*/
	public String getSocialSecurityNumber() { return socialSecurityNumber; }

	/**
	* Returns value of department
	* @return
	*/
	public String getDepartment() { return department; }

	/**
	* Returns value of roomNumber
	* @return
	*/
	public String getRoomNumber() { return roomNumber; }

	/**
	* Returns value of phoneNumber
	* @return
	*/
	public String getPhoneNumber() { return phoneNumber; }

	/**
	* Sets new value of employeeID
	* @param
	*/
	public void setemployeeID(String employeeID) { this.employeeID = employeeID; }

	/**
	* Sets new value of firstName
	* @param
	*/
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/**
	* Sets new value of lastName
	* @param
	*/
	public void setLastName(String lastName) { this.lastName = lastName; }

	/**
	* Sets new value of socialSecurityNumber
	* @param
	*/
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	* Sets new value of department
	* @param
	*/
	public void setDepartment(String department) { this.department = department; }

	/**
	* Sets new value of roomNumber
	* @param
	*/
	public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

	/**
	* Sets new value of phoneNumber
	* @param
	*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	* String representation of Employee for printing.
	* @return employee data as a string
	*/
	@Override
	public String toString() {

		return "employeeID= " + employeeID + ",\nfirstName= "
				+ firstName + ",\nlastName= " + lastName + ",\nsocialSecurityNumber= "
				+ socialSecurityNumber + ",\ndepartment= " + department
				+ ",\nroomNumber= " + roomNumber + ",\nphoneNumber= "
				+ phoneNumber;

	}
}
