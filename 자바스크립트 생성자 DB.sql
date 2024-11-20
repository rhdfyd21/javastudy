drop table books;
-- books 
CREATE TABLE books (
    id number(4),
    title varchar2(50),
    publisher varchar2(30),
    year varchar2(10),
    price number(6)
);
ALTER TABLE BOOKS ADD CONSTRAINT BOOKS_ID_PK PRIMARY KEY(id);

drop sequence books_id_seq;
CREATE SEQUENCE books_id_seq
START WITH 1
INCREMENT BY 1;

DESC BOOKS;

SELECT * FROM USER_CONS_COLUMNS WHERE TABLE_NAME = 'BOOKS';
INSERT INTO books (id, title, publisher, year, price) VALUES (books_id_seq.nextval, 'Operating System Concepts', 'Wiley', '2003',30700);
INSERT INTO books (id, title, publisher, year, price) VALUES (books_id_seq.nextval, 'Head First PHP and MYSQL', 'OReilly', '2009', 58000);
INSERT INTO books (id, title, publisher, year, price) VALUES (books_id_seq.nextval, 'C Programming Language', 'Prentice-Hall', '1989', 35000);
INSERT INTO books (id, title, publisher, year, price) VALUES (books_id_seq.nextval, 'Head First SQL', 'OReilly', '2007', 43000);
commit;

select * from books;