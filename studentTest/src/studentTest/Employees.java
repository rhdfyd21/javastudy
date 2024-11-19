package studentTest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Employees implements Serializable{
	private int employeeID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobID;
	private double salary;
	private double commissionPct;
	private double managerID;
	private double departmentID;
	public Employees(int employeeID, String firstName, double salary) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.salary = salary;
	}
	public Employees(int employeeID, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobID, double salary, double commissionPct, double managerID, double departmentID) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobID = jobID;
		this.salary = salary;
		this.commissionPct = commissionPct;
		this.managerID = managerID;
		this.departmentID = departmentID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommissionPct() {
		return commissionPct;
	}
	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}
	public double getManagerID() {
		return managerID;
	}
	public void setManagerID(double managerID) {
		this.managerID = managerID;
	}
	public double getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(double departmentID) {
		this.departmentID = departmentID;
	}
	// 객체 비교 (주키) 
	@Override
	public int hashCode() {
		return Objects.hash(employeeID);
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Employees)) {
			return false;
		}
		return this.employeeID == ((Employees)obj).employeeID;
	}
	//to string
	@Override
	public String toString() {
		return "Employees [employeeID=" + employeeID + ", firstName=" + firstName + ", salary=" + salary + "]";
	}
	
	
	
}
