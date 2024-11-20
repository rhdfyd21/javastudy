package bookTest2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		// 사용자로부터 Books 출력,입력,수정,삭제,종료 입력받기.
		boolean exitFlag = false;
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case BookMenu.PRINT:
				booksPrint();
				break;
			case BookMenu.INSERT:
				booksInsert();
				break;
			case BookMenu.UPDATE:
				booksUpdate();
				break;
			case BookMenu.DELETE:
				booksDelete();
				break;
			case BookMenu.SALARY_UP_PROC:
				employeeSalaryUpProc();
				break;
			case BookMenu.SALARY_UP_FUNC:
				employeeSalaryUpFunc();
				break;
			case BookMenu.EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected0value: " + num);
			}
			
		}
		System.out.println("the end");
	}
	private static void employeeSalaryUpFunc() throws SQLException {
        // Connection
        Connection con = null;
        CallableStatement cstmt = null;

        // 1 Load,2 connect
        con = DBconnection.dbCon();
        System.out.print("조회할 ID 입력: >>");
        int id = Integer.parseInt(sc.nextLine());

        // 3. cstmt = con.prepareCall("{ ? = call BOOKS_FUNCTION(?)}");
        cstmt = con.prepareCall("{ ? = call BOOKS_FUNCTION2(?)}");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.setInt(2, id);
        // 출력될 데이터값으로 3번을 바인딩시킨다.

        int result = cstmt.executeUpdate();
        String message = cstmt.getString(1);
        System.out.println(message);
        // 4.내용이 잘 입력이 되었는지 check
        System.out.println((result != 0) ? "FUNCTION 성공" : "FUNCTION 실패");
        // 6.sql 객체 반남
        DBconnection.dbClose(con, cstmt);

    }
	//연봉 인상 10%
	private static void employeeSalaryUpProc() throws SQLException {
        // Connection
        Connection con = null;
        CallableStatement cstmt = null; 

        // 1 Load,2 connect
        con = DBconnection.dbCon();
        System.out.print("인상될 ID 입력: >>");
        int id = Integer.parseInt(sc.nextLine()); 
        System.out.print("인상금액: >>");
        int price = Integer.parseInt(sc.nextLine()); 

        // 3. cstmt = con.prepareCall("{call EMP1_PROCEDURE(?,?,?)}");
        cstmt =con.prepareCall("{call BOOKS_PROCEDURE(?, ?, ?)}");
        cstmt.setInt(1, id);
        cstmt.setDouble(2, price);
        // 출력될 데이터값으로 3번을 바인딩시킨다.
        cstmt.registerOutParameter(3, Types.VARCHAR);

        int result = cstmt.executeUpdate();
        String message = cstmt.getString(3);
        System.out.println(message);
        // 4.내용이 잘 입력이 되었는지 check
        System.out.println((result != 0) ? "책값 인상 프로시저성공" : "책값 인상 프로시저실패");
        // 6.sql 객체 반남
        DBconnection.dbClose(con, cstmt);
    }

	private static void booksDelete() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		System.out.println("삭제할 번호>>");
		int no = Integer.parseInt(sc.nextLine());
		pstmt = con.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
		pstmt.setInt(1, no);
		int result = pstmt.executeUpdate();

		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// sql 객체 만남
		DBconnection.dbClose(con, pstmt);

	}

	private static void booksUpdate() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		//수정할 데이터를 입력
		Books books = new Books(3, "JAVA", "kdj", "2022", 444400);

		pstmt = con.prepareStatement("UPDATE BOOKS SET TITLE = ?, PUBLISHER = ?, YEAR = ? , PRICE = ? WHERE ID = ?" );
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		
		int result = pstmt.executeUpdate();

		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		// sql 객체 만남
		DBconnection.dbClose(con, pstmt);

	}

	private static void booksInsert() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		con.setAutoCommit(false);
		Books books = new Books(0, "Head First JAVA", "kdj", "2008", 23000);

		pstmt = con.prepareStatement("INSERT INTO books VALUES (Books_id_seq.nextval,?, ?, ?,?)");
		pstmt.setString(1,books.getTitle());
		pstmt.setString(2,books.getPublisher());
		pstmt.setString(3,books.getYear());
		pstmt.setInt(4,books.getPrice());
		
		int result = pstmt.executeUpdate();
		
		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		if(result != 0) {
			con.commit();
		}else {
			con.rollback();
		}
		// sql 객체 만남
		DBconnection.dbClose(con, stmt);

	}

	// 출력
	private static void booksPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from BOOKS");

		// 4.테이블 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}
		// 5 출력하기
		booksListPrint(booksList);
		// .6 sql 객체 반납
		DBconnection.dbClose(con, stmt, rs);
	}

	private static void printMenu() {
		System.out.println("Books Menu(1. 출력, 2. 입력, 3. 수정, 4. 삭제, 5. 연봉인상(emp), 6.책값조회 7. 종료");
		System.out.print(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}
}
