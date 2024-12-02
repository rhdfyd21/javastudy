-- 자바 프로젝트 사용자계정 생성하기
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
DROP USER subjectdb CASCADE; -- 기존 사용자 삭제
CREATE USER subjectdb IDENTIFIED BY 123456 -- 사용자 이름: Model, 비밀번호 : 1234
    DEFAULT TABLESPACE USERS    -- 데이터 저장소
    TEMPORARY TABLESPACE TEMP;   -- 임시저장장소
GRANT connect, resource, dba TO SUBJECTDB; -- 권한 부여

-- 학과 (01-컴퓨터학과 / 02-교육학과 / 03-신문방송학과 / 04-인터넷비즈니스과 / 05-기술경영과)
create table subject( 
    no number,                 -- pk, seq
    num varchar2(2) not null,  -- 학과번호 01, 02, 03,04,05
    name varchar2(24) not null -- 학과이름
);
Alter table subject add constraint subject_no_pk primary key(no); 
Alter table subject add constraint subject_num_uk UNIQUE(num);

create sequence subject_seq
start with 1
increment by 1; 

insert into subject(no, num, name) values (subject_seq.nextval, 03, 보안학과);
select * from subject;
DELETE FROM SUBJECT WHERE NUM = '05';
UPDATE SUBJECT SET NAME ='보안학과' WHERE NUM = '03';
SELECT * FROM SUBJECT ORDER BY NUM;
COMMIT; 

-- 학생
create table student( 
    no number,                    --pk, seq
    num varchar2(8) not null,     --학번(년도학과번호)
    name varchar2(12) not null,   --이름
    id varchar2(12) not null,     --아이디
    passwd varchar2(12) not null, --패스워드
    s_num varchar2(2) not null,   --학과번호(fk)
    birthday varchar2(8) not null,--생년월일 
    phone varchar2(15) not null,  --전화번호
    address varchar2(80) not null,--주소
    email varchar2(40) not null,  --이메일
    sdate date default sysdate    --등록일
);
commit;
SELECT * FROM STUDENT WHERE no = 1;
UPDATE STUDENT SET  passwd = 1234, birthday = '20001111', address = '화성', email = 'wohyuck@adf.sd' WHERE NO = 1;
--학생 검색
SELECT NUM,NAME,EMAIL FROM STUDENT WHERE NAME = '홍길동';
Alter table student add constraint student_no_pk primary key(no); 
Alter table student add constraint student_id_uk UNIQUE(id);
Alter table student add constraint student_num_uk UNIQUE(num);
Alter table student add constraint student_subject_num_fk 
    FOREIGN key(s_num) References subject(num) on delete set null;
alter table student drop constraint student_subject_num_fk;

create sequence student_seq
start with 1
increment by 1; 

select * from student;

INSERT INTO student 
VALUES (
    student_seq.nextval,  -- 시퀀스를 이용한 자동 증가
    '20230101',           -- 학번
    '홍길동',             -- 이름
    'hong123',            -- 아이디
    'password',           -- 패스워드
    '03',                 -- 학과번호 (보안학과)
    '20000101',           -- 생년월일
    '010-1234-5678',      -- 전화번호
    '서울특별시 강남구',    -- 주소
    'hong123@example.com',-- 이메일
    sysdate               -- 현재 날짜
); 
UPDATE STUDENT SET PASSWD = 1234;
select COUNT(*) AS COUNT from student where id = 10;
-- 동일학과번호 총갯수
select LPAD(count(*)+1,4,'0') as total_count from student where s_num = '05'; 
select * from student;
-- SUBJECT STUDENT INNER JOIN
SELECT STU.NO, STU.NUM, STU.NAME, STU.ID,PASSWD,STU.S_NUM,SUB.NAME AS SUBJECT_NAME ,BIRTHDAY,PHONE,ADDRESS, EMAIL, SDATE
FROM STUDENT STU INNER JOIN SUBJECT SUB ON STU.S_NUM = SUB.NUM ; 



-- lesson 과목

create table lesson( 
    no number ,                 --pk seq
    abbre varchar2(2) not null, --과목요약
    name varchar2(20) not null  --과목이름
);
Alter table lesson add constraint lesson_no_pk primary key(no); 
Alter table lesson add constraint lesson_abbre_uk UNIQUE(abbre);

create sequence lesson_seq 
start with 1
increment by 1;

commit;
drop table LESSON;
--테스팅 
select * from lesson;
DELETE FROM LESSON WHERE NO = 10;
UPDATE LESSON SET ABBRE = '01', NAME = '컴퓨터구조론' WHERE NO = 10;
INSERT INTO LESSON VALUES(LESSON_SEQ.NEXTVAL, '10','컴퓨터구조론');
-- trainee 수강신청
drop table trainee;
create table trainee( 
    no number ,                     --pk seq
    s_num varchar2(8) not null,     --student.num(fk) 학생번호
    abbre varchar2(2) not null,     --lesson.abbre(fk) 과목요약
    section varchar2(20) not null,  --전공,부전공,교양
    regdate date default sysdate      --수강신청일
);
select T.no, T.section, T.regdate, S.num, S.name, L.abbre, L.name as abbreName from trainee T 
inner join student S on T.s_num = S.num
inner join lesson L on T.abbre = L.abbre  
order by T.no;

Alter table trainee add constraint trainee_no_pk primary key(no);
Alter table trainee add constraint trainee_student_num_fk 
    FOREIGN key(s_num) References student(num) on delete set null;
Alter table trainee add constraint trainee_lesson_abbre_fk 
    FOREIGN key(abbre) References lesson(abbre) on delete set null;
    
    --테스팅
    UPDATE trainee 
SET s_num = 'DEFAULT_SNUM', abbre = 'DEFAULT_ABBRE', section = 'DEFAULT_SECTION' 
WHERE no = 10;

INSERT INTO trainee 
VALUES (trainee_seq.NEXTVAL, 'DEFAULT_SNUM', 'DEFAULT_ABBRE', 'DEFAULT_SECTION', sysdate);
create sequence trainee_seq 
start with 1
increment by 1;

commit;
drop table LESSON;
--테스팅 
select * from lesson;
DELETE FROM LESSON WHERE NO = 10;
UPDATE LESSON SET ABBRE = '01', NAME = '컴퓨터구조론' WHERE NO = 10;
INSERT INTO LESSON VALUES(LESSON_SEQ.NEXTVAL, '10','컴퓨터구조론');

-- 공공 데이터 (전통시장 정보)
create table LANDPRICE(
    NODENO NUMBER,                  
    GPSLATI NUMBER, 
    GPSLONG NUMBER,
    NODEID VARCHAR2(20) not null,
    NODENM VARCHAR2(50)
    );
ALTER TABLE LANDPRICE ADD CONSTRAINT LANDPRICE_NODENO_PK PRIMARY KEY(NODENO);
ALTER TABLE LANDPRICE ADD CONSTRAINT LANDPRICE_NODEID_UK UNIQUE(NODEID);

SELECT count(*) AS count FROM LANDPRICE WHERE NODENO = 10;

select * from landprice;