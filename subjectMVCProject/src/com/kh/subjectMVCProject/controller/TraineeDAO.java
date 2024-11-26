package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.StudentVO;


public class TraineeDAO {
	public static final String TRAINEE_SELECT = "SELECT * FROM STUDENT2";
	public static final String TRAINEE_INSERT = "INSERT INTO STUDENT2(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT2_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public static final String TRAINEE_CALLRANKPROC = "{call STUDENT2_RANK_PROC()}";
	public static final String TRAINEE_UPDATE = "UPDATE STUDENT2 SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ? ";
	public static final String TRAINEE_DELETE = "DELETE FROM STUDENT2 WHERE NO = ?";
	public static final String TRAINEE_SORT = "SELECT * FROM STUDENT2 order by RANK";

	public static ArrayList<StudentVO> studentSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT);
		if(rs.next()) {
            do{
                int no = rs.getInt("NO");
                String name = rs.getString("NAME");
                int kor = rs.getInt("KOR");
                int eng = rs.getInt("ENG");
                int mat = rs.getInt("MAT");
                int total = rs.getInt("TOTAL");
                int ave = rs.getInt("AVE");
                int rank = rs.getInt("RANK");

                StudentVO stu = new StudentVO();
                studentList.add(stu);
            }while (rs.next());
        }else {
            studentList = null; 
        }
		
		// stuListPrint(studentList);
		DBUtility.dbClose(con, stmt, rs);
		return studentList;
	}
	// 입력
	public static boolean studentInsert(StudentVO svo) throws SQLException {
		// Conection
		boolean successFlag = false;
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(TRAINEE_INSERT);
		pstmt.setString(1, svo.getName());

		int result1 = pstmt.executeUpdate();
		System.out.println((result1 != 0) ? "입력성공" : "입력실패");

		cstmt = con.prepareCall(TRAINEE_CALLRANKPROC);
		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");

		DBUtility.dbClose(con, pstmt);
		successFlag = (result1 != 0 && result2 != 0) ? true : false;

		return successFlag;

	}

	// 수정
	public static boolean studentUpdate(StudentVO svo) throws SQLException {
		boolean successFlag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(TRAINEE_UPDATE);
		pstmt.setString(1, svo.getName());


		int result1 = pstmt.executeUpdate();

		cstmt = con.prepareCall(TRAINEE_CALLRANKPROC);
		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");

		successFlag = (result1 != 0 && result2 != 0) ? true : false;

		DBUtility.dbClose(con, pstmt, cstmt);

		return successFlag;
	}

	// 삭제
	public static boolean studentDelete(StudentVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(TRAINEE_DELETE);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();

		successFlag = (result != 0) ? true : false;

		DBUtility.dbClose(con, pstmt);

		return successFlag;
	}

	// 정렬
	public static ArrayList<StudentVO> studentSort() throws SQLException {
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

				con = DBUtility.dbCon();
				stmt = con.createStatement();
				rs = stmt.executeQuery(TRAINEE_SORT);

				while (rs.next()) {
					int no = rs.getInt("NO");
					String name = rs.getString("NAME");
					int kor = rs.getInt("KOR");
					int eng = rs.getInt("ENG");
					int mat = rs.getInt("MAT");
					int total = rs.getInt("TOTAL");
					int ave = rs.getInt("AVE");
					int rank = rs.getInt("RANK");
					
					
					StudentVO stu = new StudentVO();
					studentList.add(stu);
				}
				
				return studentList;
	}
}
