package bookTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int PRINT = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	public static final int EIXT = 5;

	public static void main(String[] args) throws SQLException {
		// 사용자로부터 Books 출력,입력,수정,삭제,종료 입력받기.
		boolean exitFlag = false;
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case PRINT:
				booksPrint();
				break;
			case INSERT:
				booksInsert();
				break;
			case UPDATE:
				booksUpdate();
				break;
			case DELETE:
				booksDelete();
				break;
			case EIXT:
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
		Statement stmt = null;
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		System.out.println("삭제할 번호>>");
		int no = Integer.parseInt(sc.nextLine());

		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM BOOKS WHERE ID = " + no);

		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// sql 객체 만남
		DBconnection.dbClose(con, stmt);

	}

	private static void booksUpdate() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		//수정할 데이터를 입력
		Books books = new Books(3, "JAVA java", "kdj", "2024", 33000);

		stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE BOOKS SET TITLE ='"+books.getTitle()+"',PUBLISHER "
				+ "= '"+books.getPublisher()+"', YEAR = '"+2024+"', PRICE = "+33000+" WHERE ID = "+3);

		// 내용이 잘 입력이 되었는지 check 하기
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		// sql 객체 만남
		DBconnection.dbClose(con, stmt);

	}

	private static void booksInsert() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		// 1 Load,2 connect
		con = DBconnection.dbCon();
		// 3. statement
		Books books = new Books(0, "Head First JAVA", "kdj", "2008", 23000);
		String publisher = "kdj";

		stmt = con.createStatement();
		int result = stmt.executeUpdate("INSERT INTO books VALUES " + "(BOOKS_ID_SEQ.nextval,'" + books.getTitle()
        + "','" + books.getPublisher() + "','" + books.getYear() + "'," + books.getPrice() + ")");

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
		System.out.println(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}
}
