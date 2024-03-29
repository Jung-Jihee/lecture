### Misolab Web Multi module Template

개발언어 : JAVA , JPA

프레임워크 : Spring Boot, Maven 

DBMS : MYSQL

### 테이블  생성 
CREATE TABLE tb_lecture (

                      class_No        INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,

                      lecturer     VARCHAR(20)  NULL,

                      class    VARCHAR(20)  NULL,

                      lecturetime   VARCHAR(50)  NULL,

                      cnt  int NOT NULL default 0,

                      content    VARCHAR(500)  NULL,

                      created_Date datetime  NULL,

                      modified_Date datetime NULL

)



insert into tb_lecture (lecturer, class, lecturetime,  content, created_Date, modified_Date)
values ('김영수','CLASS-A','2023-08-28 10:00','YouTube 이용 컨텐츠 제작',now(),now())

insert into tb_lecture (lecturer, class, lecturetime,  content, created_Date, modified_Date)
values ('이미아','CLASS-B','2023-08-28 11:00','파이썬 기초강의',now(),now())


insert into tb_lecture (lecturer, class, lecturetime,  content, created_Date, modified_Date)
values ('김영수','CLASS-C','2023-08-28 10:00','스페인어 기초강의',now(),now())


CREATE TABLE tb_lecture_reg (

                                seq        INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,

                                class_No     int  NOT NULL,

                                sabun       varchar(5),

                                created_Date datetime  NULL,

                                modified_Date datetime NULL

)


#### View 생성
create view view_lectureReg as
	select b.seq, a.class_No, a.lecturetime, a.content ,a.lecturer , a.class
	,a.cnt,b.sabun,b.created_Date,b.modified_Date
	from tb_lecture a 
    inner join tb_lecture_reg b on a.class_No = b.class_No;


#### 테스트 URL
1. 전체강의목록 
http://localhost:8080/list
2. 강의 저장
http://localhost:8080/lecturesave
{"lecturer":"이미아","classname":"CLASS-G","lecturedate":"2023-08-30","lecturehour":"10",
"lectureminute":"30" ,"content":"파이썬 기조강의의"}

3. 강연신청자 목록(강연별 신청한 사번 목록)
http://localhost:8080/classreg?classNo=1

4.Front 강연 신청(사번 입력, 같은 강연 중복 신청 제한)
http://localhost:8080/front/lectureproc
{"classNo":2, "sabun":"15412"}

5. 신청내역 조회(사번 입력)
http://localhost:8080/front/lectureView?classNo=2&sabun=12431

6. 신청한 강연 취소
http://localhost:8080/front/lectureDel?seq=1

7. 실시간 인기 강연
http://localhost:8080/front/favorite