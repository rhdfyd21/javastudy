package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;

public class LessonRegisterManager {
	public Scanner sc = new Scanner(System.in);
	//과목 등록(insert)
	public void insertManager() {
		LessonDAO idao = new LessonDAO();
		//화면으로 부터 입력을 받는다.

		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine());
	
		LessonVO lvo = new LessonVO();
		lvo.setNo(no);
		boolean successFlag = idao.lessonInsert(lvo);
		
		//화면 출력
		if(successFlag == true) {
			System.out.println(no + "번호가 입력 되었습니다.");
		}else {
			System.out.println(no + "번호 입력 실패하였습니다.");
		}
	}
	//과목 목록(select)
	public void selectManager() throws SQLException {
		LessonDAO idao = new LessonDAO();
		//화면으로 부터 입력을 받는다.	
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = idao.lessonSelect(lvo);
		
		//화면 출력
		if (lessonList.size() != 0) {
		printLessonList(lessonList);
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	
	}
	
	//과목 삭제(delete)
	public void deleteManager() {
		LessonDAO idao = new LessonDAO();
		//화면으로 부터 입력을 받는다.

		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine());
	
		LessonVO lvo = new LessonVO();
		lvo.setNo(no);
		boolean successFlag = idao.lessonDelete(lvo);
		
		//화면 출력
		if(successFlag == true) {
			System.out.println(no + "번호가 삭제 되었습니다.");
		}else {
			System.out.println(no + "번호 삭제 실패하였습니다.");
		}
	}
	//과목 수정(update)
	
	//과목 정렬(select)
	public void selectSortManager() throws SQLException {
		LessonDAO idao = new LessonDAO();
		//화면으로 부터 입력을 받는다.	
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = idao.lessonSelectSort(lvo);
		
		//화면 출력
		if (lessonList.size() != 0) {
		printLessonList(lessonList);
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	
	}
	private void printLessonList(ArrayList<LessonVO> lessonList) {
		for( LessonVO data : lessonList) {
			System.out.println(data);
		}
		
	}
}
