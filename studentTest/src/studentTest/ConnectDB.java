package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees>employeesList = new ArrayList<Employees>();
		//1번과 2번을 가져오기 - DBconnection.java
		con = DBconnection.dbCon();
		
		//3. statement(query: c, u, r, d)
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employees");
			while(rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				int salary = rs.getInt("SALARY");
				Employees employees = new Employees(employeeID, firstName, salary);
				employeesList.add(employees);
			}
		} catch (SQLException e) {
			System.out.println("데이타베이스 실행문 에러"+e.toString());
		}
		
		//4. result set
		
		//5. 데이터 저장 진행

		// 데이터 출력
		employeesListPrint(employeesList);
		//6. close 
		DBconnection.dbClose(con, stmt, rs);
	}

	private static void employeesListPrint(ArrayList<Employees> employeesList) {
		for( Employees e : employeesList) {
		System.out.println(e.toString());
	}
	}
}
