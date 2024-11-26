package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.LessonVO;

//자바에서 데이터베이스를 접근해서 curd를 전문적으로 담당하는 클래스
public class LessonDAO {
	public final String LESSON_SELECT = "SELECT * LESSON;";
	public final String LESSON_SELECT_SORT = "SELECT * FROM LESSON ORDER BY NAME;";
	public final String LESSON_DELETE = "DELETE FROM LESSON WHERE NO = ?";
	public final String LESSON_UPDATE = "UPDATE LESSON SET ABBRE = ?, NAME = ? WHERE NO = ?";
	public final String LESSON_INSERT = "INSERT INTO LESSON VALUES(LESSON_SEQ.NEXTVAL, ?,?)";
	
	
	//lesson 테이블에서 select 출력 레코드를 리턴한다(read)
	public ArrayList<LessonVO> lessonSelect(LessonVO lvo) throws SQLException{
		Connection con = null;					//오라클접속관문
		PreparedStatement pstmt = null;			//오라클에서 작업할 쿼리문 사용할게~ 하는 명령문
		ResultSet rs = null;					//오라클에서 결과물을 받는 객체
		ArrayList<LessonVO> lessonList = new ArrayList<LessonVO>(); // 결과값을 다른 객체에 전달하기 위해서 사용하는 객체
	
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			int no = rs.getInt("NO");
			String abbre = rs.getString("ABBRE");
			String name = rs.getString("NAME");
			LessonVO lessonVO = new LessonVO(no, abbre, name);
			lessonList.add(lessonVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally {
			DBUtility.dbClose(con, pstmt,rs);
		}
		
		return lessonList;
	}
	//lesson 테이블에서 select 출력 레코드를 리턴한다(read)
	public ArrayList<LessonVO> lessonSelectSort(LessonVO lvo) throws SQLException{
		Connection con = null;					//오라클접속관문
		PreparedStatement pstmt = null;			//오라클에서 작업할 쿼리문 사용할게~ 하는 명령문
		ResultSet rs = null;					//오라클에서 결과물을 받는 객체
		ArrayList<LessonVO> lessonList = new ArrayList<LessonVO>(); // 결과값을 다른 객체에 전달하기 위해서 사용하는 객체
		
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT_SORT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally {
			DBUtility.dbClose(con, pstmt,rs);
		}
		
		return lessonList;
	}
	//lesson 테이블에서 delete 레코드를 삭제한다.(delete)
	public boolean lessonDelete(LessonVO lvo) {
		Connection con = null;					//오라클접속관문
		PreparedStatement pstmt = null;			//오라클에서 작업할 쿼리문 사용할게~ 하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_DELETE);
			pstmt.setInt(1, lvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag;
	}
	
	//lesson 테이블에서 update  레코드를 수정한다. (update)
	public boolean lessonUpdate(LessonVO lvo) {
		Connection con = null;					//오라클접속관문
		PreparedStatement pstmt = null;			//오라클에서 작업할 쿼리문 사용할게~ 하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_UPDATE);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			pstmt.setInt(3, lvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag;
	}
	
	//lesson 테이블에서 insert 레코드를 삽입한다. (insert)
	public boolean lessonInsert(LessonVO lvo) {
		Connection con = null;					//오라클접속관문
		PreparedStatement pstmt = null;			//오라클에서 작업할 쿼리문 사용할게~ 하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_INSERT);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true):(false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag;
	}
}
