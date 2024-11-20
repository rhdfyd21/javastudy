package bookTest2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			case BookMenu.EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}

		}
		System.out.println("the end");
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
		Books books = new Books(0, "Head First JAVA", "kdj", "2008", 23000);

		pstmt = con.prepareStatement("INSERT INTO books VALUES (Books_id_seq.nextval,?, ?, ?,?)");
		pstmt.setString(1,books.getTitle());
		pstmt.setString(2,books.getPublisher());
		pstmt.setString(3,books.getYear());
		pstmt.setInt(4,books.getPrice());
		
		int result = pstmt.executeUpdate();
		
		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "입력성공" : "입력실패");
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
		System.out.println("Books Menu(1. 출력, 2. 입력, 3. 수정, 4. 삭제, 5. 종료)");
		System.out.print(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}
}
