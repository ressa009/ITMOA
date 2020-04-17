SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS rep;
DROP TABLE IF EXISTS review_brd;
DROP TABLE IF EXISTS zzim;
DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS cur;
DROP TABLE IF EXISTS ins;
DROP TABLE IF EXISTS mb;
DROP TABLE IF EXISTS news_brd;




/* Create Tables */

CREATE TABLE class
(
	cur_uid int NOT NULL,
	ins_uid int NOT NULL,
	class_uid int NOT NULL AUTO_INCREMENT,
	class_zzimcnt int DEFAULT 0,
	PRIMARY KEY (class_uid)
);


CREATE TABLE cur
(
	cur_uid int NOT NULL AUTO_INCREMENT,
	cur_name varchar(50) NOT NULL,
	cur_hours int NOT NULL,
	cur_months int NOT NULL,
	cur_month1 text,
	cur_month2 text,
	cur_month3 text,
	cur_month4 text,
	cur_month5 text,
	cur_month6 text,
	PRIMARY KEY (cur_uid)
);


CREATE TABLE ins
(
	ins_uid int NOT NULL AUTO_INCREMENT,
	ins_name varchar(50) NOT NULL,
	ins_tel varchar(20) NOT NULL,
	ins_zip int NOT NULL,
	ins_add1 text NOT NULL,
	ins_add2 text NOT NULL,
	ins_location varchar(20) NOT NULL,
	ins_branch varchar(20) NOT NULL,
	ins_img text,
	ins_x double,
	ins_y double,
	PRIMARY KEY (ins_uid)
);


CREATE TABLE mb
(
	mb_uid int NOT NULL AUTO_INCREMENT,
	mb_id varchar(20) NOT NULL,
	mb_pw varchar(20) NOT NULL,
	mb_name varchar(20) NOT NULL,
	mb_email varchar(60) NOT NULL,
	mb_zip int NOT NULL,
	mb_add1 text NOT NULL,
	mb_add2 text NOT NULL,
	mb_level int DEFAULT 1,
	mb_regdate datetime DEFAULT now(),
	mb_img text,
	PRIMARY KEY (mb_uid),
	UNIQUE (mb_id),
	UNIQUE (mb_email)
);


CREATE TABLE news_brd
(
	news_brd_uid int NOT NULL AUTO_INCREMENT,
	news_brd_title varchar(100) NOT NULL,
	news_brd_img varchar(150),
	news_brd_content text,
	news_brd_viewcnt int DEFAULT 0,
	PRIMARY KEY (news_brd_uid),
	UNIQUE (news_brd_title)
);


CREATE TABLE rep
(
	mb_uid int NOT NULL DEFAULT 0,
	review_brd_uid int NOT NULL,
	rep_uid int NOT NULL AUTO_INCREMENT,
	rep_content text NOT NULL,
	rep_regdate datetime DEFAULT now(),
	PRIMARY KEY (rep_uid)
);


CREATE TABLE review_brd
(
	review_brd_uid int NOT NULL AUTO_INCREMENT,
	review_brd_title varchar(50) NOT NULL,
	review_brd_content text NOT NULL,
	review_brd_regdate datetime DEFAULT now(),
	review_brd_viewcnt int DEFAULT 0,
	class_uid int NOT NULL,
	mb_uid int NOT NULL DEFAULT 0,
	PRIMARY KEY (review_brd_uid)
);


CREATE TABLE zzim
(
	mb_uid int NOT NULL DEFAULT 0,
	class_uid int NOT NULL,
	zzim_uid int NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (zzim_uid)
);



/* Create Foreign Keys */

ALTER TABLE review_brd
	ADD FOREIGN KEY (class_uid)
	REFERENCES class (class_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE zzim
	ADD FOREIGN KEY (class_uid)
	REFERENCES class (class_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE class
	ADD FOREIGN KEY (cur_uid)
	REFERENCES cur (cur_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE class
	ADD FOREIGN KEY (ins_uid)
	REFERENCES ins (ins_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE rep
	ADD FOREIGN KEY (mb_uid)
	REFERENCES mb (mb_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE review_brd
	ADD FOREIGN KEY (mb_uid)
	REFERENCES mb (mb_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE zzim
	ADD FOREIGN KEY (mb_uid)
	REFERENCES mb (mb_uid)
	ON UPDATE cascade
	ON DELETE cascade
;


ALTER TABLE rep
	ADD FOREIGN KEY (review_brd_uid)
	REFERENCES review_brd (review_brd_uid)
	ON UPDATE cascade
	ON DELETE cascade
;



SELECT * FROM mb;
SELECT * FROM class;
SELECT * FROM cur;
select * from ins;
select * from zzim;
select * from review_brd;

SELECT z.*, ins_name, cur_name FROM zzim z, ins i, cur WHERE z.mb_uid = 2;

-- 2배로 늘리기 (회원관리)
INSERT INTO mb(mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2)
SELECT mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2 FROM mb;

update mb set mb_level = 3 where mb_id = 'admin';
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user1', 'Aabcd123!', '이미지', 'wkrud94@hanmail.net', 07030, '서울시', '동작구');
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user2', 'Bbsda912!', '이새힘', 'teste@yahoo.com', 03210, '서울시', '관악구');
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user3', 'Ddsrrws1@', '박지민', 'emmail@gmail.com', 14410, '인천광역시', '팔달구');
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user4', 'Rretw_12#', '박우람', 'abced@hanmail.net', 01035, '경기도', '평택');
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user5', 'A12_adsf$', '김민하', 'ddsf133@hanmail.net', 91105, '부천시', '원미구');
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2, mb_level) 
values ('admin', 'pw', '김민하', 'ddsf1asd33@hanm123ail.net', 91105, '부천시', '원미구', 3);


insert into ins (ins_name, ins_tel, ins_zip, ins_add1, ins_add2, ins_location, ins_branch, ins_img, ins_x, ins_y)
values
("코리아IT아카데미 강남점", "1588-5890", 06236, "서울 강남구 테헤란로 146", "4층", "서울", "강남", "data:image/jpeg;base64,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", "37.5635251", "126.6939152")
,("이젠아카데미컴퓨터학원 강남점", "02-532-6500", 06611, "서울특별시 서초구 1303-37", "서초W타워 13층", "서울", "강남", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.4987156", "127.0236953")
,("SBS아카데미컴퓨터아트학원 강남점", "1588-5530", 06615, "서울특별시 서초구 1317-20", "아라타워 5층", "서울", "강남", "https://post-phinf.pstatic.net/MjAxNzExMjdfMTQx/MDAxNTExNzU1OTU2MTk1.3_8sOk8RZdY3tMpkIX_nTjj4QxJWADQnlbdB1KanZV8g.ZrGx-K_k8PuPD6y8W-eXE_iVkD15nstihfy8g_albKsg.PNG/20171121_100844.png?type=w1200", "37.4985529", "127.0198392")
,("그린컴퓨터아카데미 강남점", "02-3481-1005", 06129, "서울특별시 강남구 역삼동 강남대로 428", "만이빌딩 2층", "서울", "강남", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "37.5008524", "127.027157")
,("더조은컴퓨터아카데미 강남점", "02-556-5611", 06612, "서울특별시 서초구 서초4동 강남대로 441", "서산빌딩 4층", "서울", "강남", "http://www.tjoeun.co.kr/images/tjdpimg.jpg", "37.5016885", "127.0250492")
,("이젠아카데미컴퓨터학원 신촌캠퍼스", "02-393-4321", 03780, "서울특별시 서대문구 104-48", "은하빌딩 1층", "서울", "신촌", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.5557105", "126.9374518")
,("이젠아카데미컴퓨터학원 신촌점", "02-364-0008", 03779, "서울특별시 서대문구 29-75", "YB산업빌딩 10층", "서울", "신촌", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.555896", "126.9362803")
,("SBS아카데미컴퓨터아트학원 신촌점", "02-312-9660", 03767, "서울특별시 서대문구 신촌로 183", "유인빌딩 5층", "서울", "신촌", "https://post-phinf.pstatic.net/MjAxNzExMjdfMTQx/MDAxNTExNzU1OTU2MTk1.3_8sOk8RZdY3tMpkIX_nTjj4QxJWADQnlbdB1KanZV8g.ZrGx-K_k8PuPD6y8W-eXE_iVkD15nstihfy8g_albKsg.PNG/20171121_100844.png?type=w1200", "37.5570255", "126.9462896")
,("그린컴퓨터아트학원 신촌점", "02-722-2150", 04101, "서울특별시 마포구 31-11", "5층", "서울", "신촌", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "37.554939", "126.9351203")
,("노량진 코딩학원", "02-3675-2221", 06928, "서울특별시 동작구 노량진동 장승배기로 171", "아이비빌딩 3층", "서울", "노량진", "https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20200110_51%2F15786186038233Ea6r_PNG%2FNKuPqGboSrjKQHHnMBEsQJrt.PNG.png", "37.5112823", "126.9421419")
,("코딩플러스학원 상도점", "02-2138-7577", 06921, "서울특별시 동작구 상도2동 26-11", "순아타운", "서울", "노량진", "https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20191114_59%2F1573703310532u2Pag_JPEG%2F%25BC%25B3%25B8%25ED%25BE%25F8%25B4%25C2%25C4%25DA%25B5%25F9%25B7%25CE%25B0%25ED_1242X1242.jpg", "37.5112198", "126.9374667")
,("이젠컴퓨터학원 고양점", "031-994-8111", 10414, "경기도 고양시 일산동구 장항동 890-4", "마두법조빌딩 9층", "경기", "고양", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.6585402", "126.7770107")
,("ICA일산컴퓨터학원", "031-905-6712", 10447, "경기도 고양시 일산동구 중앙로 1068", "리더스프라자 2층, 7층", "경기", "고양", "https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20180529_291%2F1527578230919B7M65_JPEG%2F5NNsSDGJOb-7RXvvC6vNyKim.jpg", "37.6566061", "126.7793179")
,("더조은컴퓨터아트학원 안양점", "031-463-5804", 14001, "만안구 안양동 782-68번지", "프로젝트 240호 타워 2층", "경기", "안양", "http://www.tjoeun.co.kr/images/tjdpimg.jpg", "37.3998911", "126.9198355")
,("이젠컴퓨터학원 안양점", "031-463-5800", 14001, "만안구 안양동 782-68번지", "프로젝트타워 2층", "경기", "안양", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.3998896", "126.919856")
,("그린컴퓨터아트학원 안양점", "031-446-2111", 14001, "경기도 안양시 만안구 안양4동 676-91", "안양메쎄포스빌 2층", "경기", "안양", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "37.3994682", "126.9187271")
,("더조은컴퓨터아카데미학원 부천점", "032-322-0154", 14545, "경기도 부천시 원미구 상동로 90", "메가플러스 A동 6층", "경기", "부천", "http://www.tjoeun.co.kr/images/tjdpimg.jpg", "37.4998757", "126.7626637")
,("이젠아카데미컴퓨터학원 성남분당점", "031-719-3688", 13618, "경기도 성남시 분당구 돌마로 73", "우방코아 7층", "경기", "성남", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.4951538", "127.0196903")
,("그린컴퓨터아트학원 성남분당점", "031-712-7447", 13630, "경기도 성남시 분당구 구미동 7-2", "5층", "경기", "성남", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "37.4951124", "127.0196903")
,("하이미디어컴퓨터학원 구리점", "031-567-0003", 11921, "경기도 구리시 인창동 건원대로 44", "태영빌딩 307호", "경기", "구리", "https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20170516_250%2F1494919961892ejCGQ_JPEG%2F186461583168294_1.jpeg", "37.6078545", "127.1247054")
,("이젠컴퓨터학원 구리점", "031-567-0003", 11921, "경기도 구리시 인창동 670-1", "태영빌딩 409호", "경기", "구리", "http://jr.ezenacademy.com/images/intro/ci_k_img02.jpg", "37.6078519", "127.1247054")
,("광명인스타컴퓨터학원", "02-899-8887", 14333, "경기도 광명시 소하동 909-39", "4층", "경기", "광명", "https://modo-phinf.pstatic.net/20180829_99/1535525007712rAtIf_JPEG/mosa1GwhPX.jpeg?type=a1100", "37.4503597", "126.8870249")
,("영종컴퓨터학원", "032-751-5357", 22397, "인천 중구 중산동 1883-9", "하늘프라자", "인천", "중구", "https://mblogthumb-phinf.pstatic.net/20160910_247/ksshty_1473452803050zVqIG_JPEG/%C5%AC%B8%AF.jpg?type=w2", "37.4915047", "126.578677")
,("유비스코딩", "042-484-2382", 21994, "인천광역시 연수구 송도동 2-6", "송도프라자 313호", "인천", "송도", "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/44464975_1903422503105712_588089367667408896_o.png?_nc_cat=108&_nc_oc=AQnPiOBAVSwrHf1z77RcI6AQEQXzY0Aa7fBgmVKJFpmP-_KFnYnnNnFEzb7P2TqCgtw&_nc_ht=scontent-icn1-1.xx&oh=0c84cb9e4c1800b3f24e4fd37c61dfc5&oe=5E9C9D0A", "37.3998022", "126.6455486")
,("이젠컴퓨터아트학원 대전점", "042-484-2382", 35214, "대전광역시 서구 대덕대로 325", "스타게이트빌딩 7층 둔산 롯데시네마 7층", "대전", "서구", "https://hacwon.kr/files/attach/images/131/032/008/eeb7d4f4c979b932dd840bbe6184cdff.jpg", "36.3387772", "127.3712019")
,("둔산컴퓨터학원", "042-471-1100", 35260, "대전광역시 서구 탄방동 734", "소천빌딩 3층", "대전", "서구", "http://duncom.co.kr/duncom/g5/theme/community/img/dun_main_3.jpg", "36.3387364", "127.3712018")
,("계룡컴퓨터학원", "042-584-9797", 34998, "대전광역시 중구 문화1동 374-5", "2층", "대전", "중구", "https://0701.static.prezi.com/preview/v2/ciookffkkluqjzlqyjkdcx4ifp6jc3sachvcdoaizecfr3dnitcq_3_0.png", "36.3220435", "127.4064239")
,("코리아IT아카데미 대구점", "053-710-5890", 41943, "대구광역시 중구 성내1동 중앙대로 366", "9층, 10층", "대구", "중구", "data:image/jpeg;base64,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", "35.8668578", "128.5615794")
,("이음컴퓨터디자인학원", "053-255-2015", 41941, "대구광역시 중구 동성로5길 31", "3층", "대구", "중구", "data:image/jpeg;base64,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", "35.867803", "128.596251")
,("수성컴퓨터학원", "053-746-7377", 42029, "대구광역시 수성구 범어2동 달구벌대로 2495", "LG하이프라자 4층", "대구", "수성", "https://modo-phinf.pstatic.net/20170314_47/1489456135620WRM5l_JPEG/mosaDSqqjI.jpeg?type=f320_320", "35.8591879", "128.6357839")
,("디랩코딩학원", "053-716-9301", 42098, "대구광역시 수성구 범어동 동대구로 300", "범어롯데캐슬 3층 309호", "대구", "수성", "http://dlabweb.cafe24.com/www/images/seo_og.jpg", "35.8591875", "128.6292179")
,("SBS아카데미컴퓨터아트학원 부산점", "051-933-3999", 47296, "부산광역시 부산진구 중앙대로666번길 17", "A-1 프라자 4층", "부산", "진구", "https://post-phinf.pstatic.net/MjAxNzExMjdfMTQx/MDAxNTExNzU1OTU2MTk1.3_8sOk8RZdY3tMpkIX_nTjj4QxJWADQnlbdB1KanZV8g.ZrGx-K_k8PuPD6y8W-eXE_iVkD15nstihfy8g_albKsg.PNG/20171121_100844.png?type=w1200", "35.15147", "129.0579685")
,("그린컴퓨터아카데미 부산점", "051-912-1000", 47296, "부산광역시 부산진구 부전동 중앙대로 688", "3층", "부산", "진구", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "35.15147", "129.0579685")
,("연제아이컴퓨터학원", "051-865-3967", 47541, "부산광역시 연제구 연산5동 1385-1", "3층", "부산", "연제구", "http://gukbinews.com/board_TZam52/files/attach/images/642/950/008/b5405e8e3fce0b6e6ccddafff0a816c7.jpg", "35.1830729", "129.0792165")
,("아이티뱅크", "051-743-6966", 48060, "부산광역시 해운대구 우동 센텀2로 25", "KG아이티 뱅크 센텀드림월드 11층", "부산", "해운대구", "https://post-phinf.pstatic.net/MjAxNzExMDZfOTkg/MDAxNTA5OTQ3MzcwMDIy.FzVWsCCcNxxI8uMNp-e_PHpTfKIpii1PHmRPm5fS8QYg.aZ_baKW7XvzOQtqcubVJbi7JyqI_faNEp09qh5NrBcgg.JPEG/image_126353451509946890246.jpg?type=w1200", "35.1806606", "129.135379")
,("부산IT교육센터", "051-753-5600", 47291, "부산광역시 부산진구 부전2동 중앙대로 708", "5층", "부산", "해운대구", "http://mblogthumb1.phinf.naver.net/MjAxODA0MjZfMTgx/MDAxNTI0NzEyMjI2MTIz.jVtednj55QotyHnsR2JyGFXNXa8R2NmIjbZ6TjhD8oog.Kwwx40KNvQHcG_hFuEyHZ6M_sgwfSgsEva8uKHslSbEg.JPEG.rae0625/image_5340267751524712050471.jpg?type=w800", "35.1597339", "129.0786326")
,("더조은컴퓨터아트학원 광주점", "062-366-8923", 61915, "광주광역시 서구 광천동 234", "광명빌딩 5층", "광주", "서구", "http://www.tjoeun.co.kr/images/tjdpimg.jpg", "35.1637446", "126.8785785")
,("더조은컴퓨터아트학원", "062-366-8923", 61915, "광주광역시 서구 광천동 234", "광명빌딩 5층", "광주", "북구", "http://www.tjoeun.co.kr/images/tjdpimg.jpg", "35.1820467", "126.8503583")
,("그린컴퓨터아카데미 울산점", "052-716-3199", 44705, "울산광역시 남구 삼산동 삼산중로100번길 26", "1층", "울산", "남구", "https://busan.greenart.co.kr/_img/event/presentCoffeeEvent/eventBanner_03.jpg", "35.5421081", "129.3218261")
,("크린컴퓨터학원", "052-271-6892", 44681, "울산광역시 남구 신정5동 272-4", "1층", "울산", "남구", "http://www.usca.co.kr/images/logo_top.png", "35.5473193", "129.3182459");


insert into cur(cur_name, cur_hours, cur_months, cur_month1, cur_month2, cur_month3, cur_month4, cur_month5, cur_month6)
values('웹앱', 3, 5,'java 기초', 'html/css/javaScript','jsp/File','Spring','기획서만들기/MVC모델','')
,('네트워크', 3, 5,'OT, 시스템 가상화/ WindowServer 설치', 'OT, 가상 시스템/라우팅 서버<br> 리눅스의 역사와 및 특징 ','사용자와 그룹 관리/라우팅 서버<br>Vi에디터 / 쉘 스크립트 ','접근제어 서비스관리 <br> 시스템 네트워킹1 시스템 네트워킹2','프로젝트','')
,('빅데이터', 3, 2,'빅데이터 분석을 위한 환경 구축의 이해(R 개발환경, R STUDIO 셋팅)<br>R 프로그래밍 기본:데이터타입, 기본문법, 수학함수, 통계함수, 사용자의 정의함수', '머신러닝을 위한 환경 구축의 이해(PYTHON설치,PYCHARM 셋팅)<br>파이썬 프로그래밍 기본: 데이터 타입, 기본문법, 사용자 정의 함수','','','','')
,('정보보안', 6, 6,'TCP/IP<br>정보 수집 기술<br>수동적/능동적 공격위협 유형<br>취약점 스캔 및 분석', 'Metasploit<br>Metasploit 스캔 및 외부 스캔 활용<br>Setoolkit - Beef 실습 및 xploit 보고서 작성','injection - html / sql injection 취약점 공격 및 대응<br> XSS <br> Security Misconfigration','PE 구조 이해 - 윈도우 PE 헤더 바디 구조 분석','쿠쿠샌드 박스 - 설치 및 악성코드 분석<br>스파이웨어 분석 - 스파이웨어 정적/동적 분석','안티 포렌식 - 디지털 포렌식 실습환경 구성/문서<br>네트워크 포렌식-프로토콜 분석/ 패킷분석')
,('게임', 5, 5,'게임 디자인의 이해, 게임 UI/UX 기획', '게임 원화 디자인, 게임 디자인의 이해<br>2D 그래픽 소프트 웨어 활용', '3D 그래픽 - 인터페이스와 기본 기능 익히기', '애니메이션 제작하기 - 게임 애니메이션 구상하기', '게임 이팩트 디자인 - 게임 그래픽 제작','')
,('영상', 4, 3,'프리미어 - 영상 기본이론, 활용예제 제작', '애프터이펙트 - 애프터이펙트의 기본원리, 모션, 합성, 익스프레션, 이펙트<br>활용예제 제작', '영상 CG작업 - 포트폴리오 제작','','','')
,('웹디자인',5, 4,'개발환경구축 및 HTML 기초문법<br>시맨틱웹','반응형웹 레이아웃/Flex 레이아웃/미디워쿼리','객체지향 자바스크립트 프로그래밍','웹서버 구축 및 웹프로그래밍의 이해','웹 API 구축/웹 소켓의 이해/간단한 채팅구형','')
,('AI',4, 9, 'java 기초', 'html/css/javaScript','jsp/File','Spring','Watson API / Pass 이해 / 웹 개발 예제 <br> 실무 개발 프로젝트','');

insert into class(ins_uid, cur_uid, class_zzimcnt)
values(1, 1, 10);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(1, 3, 121);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(2, 5, 90);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(2, 3, 430);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(3, 3, 44);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(3, 1, 64);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(4, 5, 4334);
insert into class(ins_uid, cur_uid, class_zzimcnt)
values(4, 2, 144);

-- 2배로 늘리기 (학원후기)
INSERT INTO review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
SELECT review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid FROM review_brd;

insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('코리아IT 선택은 신의한수였습니다.', '<p>여러 책의 개념과 예제를 함축시켜 핵심 위주의 이론 설명을 해주셔서 이해하기 쉬웠습니다.<br />
그리고, 숙제로 문제를 많이 내주셔서 반복해서 연습해보면서 내용을 익힐 수 있었습니다.ㅎㅎ<br />
유용한 사이트도 매시간 마다 알려주셔서 도움이 되었습니다.^^<br />
마지막 시간에 아나콘다 설치와 주피터 노트북 활용한 예제를 해볼 수 있어 매우 좋았습니다.<br />
특히 제가 질문한 내용을 수업 전에 직접 찾아보고오셔서 파이토치 관련 책저자 블로그 사이트 추천과<br />
파이토치 설치 및 사용법도 알려주시고, 관련 책도 보여주셔서 너무 만족스러웠습니다^^<br />
또한, AI 관련해서 어떻게 공부하면 좋은지도 친절하게 설명해주셔서 감사합니다~!<br />
다음에도 기회가 된다면 꼭 다시 수강하고 싶습니다^^<br />
선생님 화이팅! 선규 멘토님도 화이팅!</p>
', '2020-01-15 03:54:50', 1, 1);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('그린컴퓨터 당신은 도덕책', '<p>매일 수업마다 깔끔하게 자료들도 잘 정리해주시고, 이해하며 따라가기 쉽도록 꼼꼼하게 수업해 주신 덕에<br />
유니티를 잘 마무리 할 수 있었던 것 같습니다~!<br />
또, 전공자임에도 불구하고 파이썬은 처음 배우는거라 수강 전부터 걱정을 많이 했었는데<br />
선생님의 수업 덕분에 유니티에 흥미를 느끼게 되었습니다!<br />
그래서 저처럼 유니티에 입문하시는 분들께 추천드리고 싶네요!<br />
<br />
방학 특강이라 짧아서 아쉬웠지만 매일 아침 일찍 수업하시느라 정말 수고 많으셨습니다! ㅎㅎㅎㅎ 감사합니다~!</p>
', '2020-01-15 03:56:38', 2,1 );
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('SBS아카데미와 함께 찬란한 미래를 꿈꾸며', '<p>리눅스를 처음 혼자 접했을 때는 인터넷에서 보면서 따라했는데 똑같이 따라해도 안되고 가상머신 오류도 나고 해서 어려웠는데<br />
강사님의 수업을 듣고 나서 리눅스가 조금 더 가까워진 것 같습니다. 그리고 어렵고 재미없을 것 같았던 리눅스를 친절하고 재밌게<br />
설명해 주셔서 좋았습니다.</p>
', '2020-01-15 03:58:21', 1, 3);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('빅데이터는 이젠아카데미와 함께', '<p>처음 접해보는 리눅스 수업이라 걱정이 되었지만 강사님께서 이해하기 쉽고 편하게 수업을 진행해 주셔서 어려움 없이 수업을 따라 갈 수 있었다고 생각합니다.<br />
아직 리눅스를 배워야 할 부분이 더욱 많지만 처음 시작하는 경험이 좋았기 때문에 뒤에 할 공부들도 잘할 수 있을거라는 느낌이 들었습니다.<br />
여러 실무 경험담도 얻을 수 있어서 좋은 수업이었습니다.</p>
', '2020-01-15 03:59:40', 3, 2);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('코리아IT강남 웹개발 성연철 강사님 감사합니다!', '<p>자바기초반이라 기초적인 이론을 자세하게 알려주시고, 기초반이라고 쉬운 코딩으로 하지않고 학생들의 실력향상을 위해서 조금더 난이도가 있는 코딩을 가져와 알려주셨습니다.<br />
덕분에 기초적인부분도 잘 잡혔고 조금 어려운 코딩도 접해서 좋았습니다.<br />
수업도중에 선생님의 회사 경험담이나 회사면접때 이런것들을 물어보더라며 취업준비하는 우리에게 좋은팁들도 주어서 좋은 수업이였습니다.</p>
', '2020-01-15 04:01:32', 4, 3);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('빅데이터는 sbs아카데미와 함께!', '<p>2달간의 리눅스 과정을 마치고 수강후기 작성합니다.!<br />
<br />
처음 리눅스를 들을 때만 해도 굉장히 리눅스라는 것을 어려울꺼라 생각했었는데, 이문규강사님의<br />
수업지도방식으로 어려울 거라는 생각이 전부 사라지고 오히려 더 재밌어지고 공부도 잘되었습니다.<br />
차근차근 수업을 진행해 주면서 어려울수 있는 부분을 하나하나 체크해주시고 안되는 부분또한<br />
하나하나 체크하여서 해결해 주시면서 여러 상황에대해 필요한 공부를 할 수 있게 해주셔서 감사합니다.<br />
<br />
다른 수업과 마찬가지로 이번 리눅스 수업또한 정말 좋은 시간이 되어서 개인적으로도 너무 만족스럽고<br />
또 앞으로 수업들 중 어렵다고 생각할 수 있는 수업을 어렵다고 생각하지 않게 할 수 있는 정말 좋은 수업이였었습니다.<br />
<br />
이렇게 좋은 수업진행해주신 이문규 강사님께 다시한번 감사드립니다,<br />
2달간 리눅스를 사용할 수 있도록 지도해주시고, 서버를 구축 및 운영할 수 있도록 훌륭하게 지도해주셔서 정말감사합니다.</p>
', '2020-01-15 04:03:16', 3, 1);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('네트워크의 1인자가 되겠습니다.', '<p>텍스트로 이용하는 낯선 운영체계였지만 선생님의 친절한 설명 덕분에 금세 익숙해질 수 있었습니다.<br />
질문할 때에도 항상 친절하고 자세히 설명해주셔서 쉽지 않은 내용이었지만 흥미를 잃지 않고 따라갈 수 있었습니다.<br />
파이썬 수업 때에도 그랬지만 수업 내용 정리를 잘 해주셔서 복습할 때에도 놓치는 부분 없이 수업 내용 복기가 가능했습니다.<br />
수업 잘 이끌어주셔서 감사합니다 선생님.<br />
<br />
기초가 부족한 비전공자 분들도 따라가실 수 있도록 쉽고 친절한 강의라 수업 들으시려는 분들께 적극 추천드립니다.</p>
', '2020-01-15 04:04:52', 4, 3);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('데이터 개발자를 위한 초석', '<p>처음 배우는 프로그램언어였지만 쉬운것도 여러번 설명해 주시는 덕분에 놓치는 부분 거의 없이 수업에 참여할 수 있었습니다.<br />
질문하면 바로 자리로 오셔서 1대1로 차근히 설명해 주시고 , 수업중 어렵거나 실습이 많은 날은 수강생들 자리를 돌아보시는 경우가 있어서<br />
질문하시기 어려우신 분들에게도 편안한 수업이 될 수 있을 것 같습니다.</p>
', '2020-01-15 04:06:05', 5, 1);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('홈페이지를 만들고 싶다면 sbs로!', '<p>평일 오전 09:00~12:00 코리아IT아카데미 1타 강사 한동석 강사님의 DBMS 수업을 듣고 있는 강경표입니다.<br />
한동석 강사님의 JAVA2 수업을 듣고 강의 스타일에 감탄하여 1년에 한번 열리는 DBMS 수업을 바로 듣게 되었네요.<br />
<br />
한동석 강사님은 개념 설명을 하신 뒤, 기본 예제, 심화 예제, 기본 실습, 심화 실습을 하는 것으로 진행하십니다.<br />
수업 내용은 실제로 회사에서 어떻게 활용하는 지에 대해서 다루고 있으며 최대한 현업에서 이루어지는 내용을 위주로<br />
설명을 해주시며, SQLD 자격증 시험 준비도 도와주십니다.<br />
<br />
아무래도 실제 현업에서 잘 활용되는 내용을 위주로 설명하시다 보니, 난이도가 높은 편입니다.<br />
하지만, 강사님은 어려워 하는 학생들을 위해 1:1로 자리에 직접 가셔서 이해하기 쉽게 설명을 다시 해줍니다.<br />
<br />
다음 강의에서도 잘 부탁드립니다!! 한동석 강사님의 수업 추천드려요!ㅎㅎ</p>
', '2020-01-15 04:07:13', 5, 3);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('유니티가 즐겁습니다.', '<p>처음 DBMS를 배워서 계속 놓치고 따라가지 못했습니다.<br />
그래도 강사님이 쉬는시간에도 봐주시고 물어보면 일일히 알려주셔서 따라갈 수 있었습니다.<br />
강사 덕분에 강의 내용에 대해 이해가 많이 되었고, 수업을 듣고 난 후 자격증 시험에 도전하자는 생각이 들었습니다.<br />
강의 열심히 가르쳐주셔서 감사하고 나중에 또 강의에서 강사님을 만나면 좋겠습니다!</p>
', '2020-01-15 04:08:12',6, 1);
insert into review_brd(review_brd_title, review_brd_content, review_brd_regdate, class_uid, mb_uid)
values('빅데이터는 사랑입니다', '<p>최고에요</p>
', '2020-01-15 05:57:56', 1, 6);
select * from rep;
insert into rep(review_brd_uid, rep_content, mb_uid)
values(101, "감사합니다!", 2);
insert into rep(review_brd_uid, rep_content, mb_uid)
values(101, "도움이 되었습니다!", 3);


select * from mb m2;
delete from mb where mb_id = "admin";
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2, mb_level) 
values ('admin', 'ehqt7535', '이미지', 'ddsf1asd33@hanm123ail.net', 91105, '부천시', '원미구', 3);
update mb set mb_pw = "ehqt7535" where mb_id="user1";
insert into mb (mb_id, mb_pw, mb_name, mb_email, mb_zip, mb_add1, mb_add2) 
values ('user1', 'ehqt7535', '이미지', 'wkrud94@hanmail.net', 07030, '서울시', '동작구');