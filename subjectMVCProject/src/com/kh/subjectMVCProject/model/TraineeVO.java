package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
    private int no;         //--pk seq
    private String s_num;    //--student.num(fk) 학생번호
    private String abbre;     //--lesson.abbre(fk) 과목요약
    private String section; //--전공,부전공,교양
    private Date regdate;     //--수강신청일
    //join된 student 변수
    private String S_name;
    //join된 lesson 변수
    private String l_name;
    public TraineeVO() {}
    public TraineeVO(int no, String s_num, String abbre, String section, Date regdate) {
        super();
        this.no = no;
        this.s_num = s_num;
        this.abbre = abbre;
        this.section = section;
        this.regdate = regdate;
    }
    //조인을 위한 생성자
    public TraineeVO(int no, String s_num, String abbre, String section, Date regdate, String s_name, String l_name) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.regdate = regdate;
		S_name = s_name;
		this.l_name = l_name;
	}
	public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getS_num() {
        return s_num;
    }
    public void setS_num(String s_num) {
        this.s_num = s_num;
    }
    public String getAbbre() {
        return abbre;
    }
    public void setAbbre(String abbre) {
        this.abbre = abbre;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    
    public String getS_name() {
		return S_name;
	}
	public void setS_name(String s_name) {
		S_name = s_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	@Override
    public String toString() {
        return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section + ", regdate="
                + regdate + "]";
    }
	public String toAllString() {
		return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section + ", regdate="
				+ regdate + ", S_name=" + S_name + ", l_name=" + l_name + "]";
	}
}