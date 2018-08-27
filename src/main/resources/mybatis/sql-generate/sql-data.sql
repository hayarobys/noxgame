INSERT INTO auth_tb (AUTH_NM_UNQ,AUTH_EXPLN) VALUES 
('ROLE_MANAGER','매니저에게 부여되는 권한 입니다. 대다수 회원정보를 관리할 수 있습니다.')
,('ROLE_USER','회원에게 부여되는 권한 입니다. 일반적인 조회,삭제,수정 권한을 지니고 있습니다.')
,('ROLE_ADMIN','관리자에게 주어지는 최고권한 입니다. 관리자 이외 누구에게도 부여하지 마십시오.')
,('ROLE_ANONYMOUS','미로그인한 사람을 지칭하는 용어 입니다.')
,('ROLE_GHOST','유령회원에게 부여되는 권한 입니다. 본인 글의 조회와 정회원 전환 신청이 가능합니다.')
;

INSERT INTO mem_tb (MEM_JOIN_DT,MEM_LAST_LOGIN_DT,MEM_STATE,MEM_NICKNM,MEM_ID,MEM_PSWD) VALUES 
('2018-07-27 15:33:14.000','2017-03-12 08:40:00.000','ACTIVE','관리자','admin','$2a$10$NZvPdl81VgihX9ZKchaYZunWsd1wmm2Ap4IWtjXR.8sHvFReE9awy')
,('2017-01-01 09:00:00.000','2018-03-12 09:16:49.000','ACTIVE','일반인','user','$2a$10$nDi7TrWFH0mRXzk6Ncfd3uZke0JUmgPLxOMxudsSClhu0iKJpIddS')
,('2017-01-01 09:00:00.000','2017-03-12 09:17:00.000','ACTIVE','매니저','manager','$2a$10$LP3.bRbdubi788K18uwi..cd.pCGYNeIUuBavTDuuiUXOSuxWqMIe')
,('2017-01-01 09:00:00.000','2018-03-14 06:23:51.000','ACTIVE','테스터','tester','$2a$10$qRw0wtqj372RRGDggxMtW.WS/XGEnpbqvaHrZYoNTG8NKIzFbNdmO')
,('2018-07-27 15:33:22.000','2018-05-03 04:18:15.000','ACTIVE','테스터23','tester2','$2a$10$UwQYF9VDwvysO4tMQ45CCeeyNFRdWPGpwmhwvjf6Hz1CQ7yDoblYi')
;

INSERT INTO res_tb (HTTP_METHOD_PK,RES_ORD,RES_TYPE,RES_NM_UNQ,RES_PATTERN) VALUES 
('GET',40000,'url','관리자 페이지','/admin/**')
,('GET',30000,'url','메인 페이지','/main')
,('GET',10000,'url','모든 페이지','/**')
,('GET',50000,'url','리소스 관리 페이지 이동','/resource/edit')
,('GET',50100,'url','모든 리소스 목록 반환','/resource')
,('POST',50200,'url','리소스 추가','/resource')
,('PATCH',50300,'url','특정 리소스 수정','/resource/*')
,('DELETE',50400,'url','하나 또는 여러개의 리소스 삭제','/resource/*')
,('GET',60000,'url','HTTP 메소드 관리 페이지 이동','/http-method/edit')
,('GET',60100,'url','모든 HTTP 메소드 목록 반환','/http-method')
;
INSERT INTO res_tb (HTTP_METHOD_PK,RES_ORD,RES_TYPE,RES_NM_UNQ,RES_PATTERN) VALUES 
('GET',70000,'url','리소스-권한 관리 페이지 이동','/resource-auth/edit')
,('GET',70100,'url','특정 리소스 접근에 필요한 권한 목록 반환','/resource/*/auth')
,('GET',60200,'url','특정 일련 번호의 HTTP 메소드 반환','/http-method/*')
,('PATCH',70200,'url','특정 리소스 접근에 필요한 권한 목록 업데이트','/resource/*/auth')
,('GET',80000,'url','권한 관리 페이지 이동','/auth/edit')
,('GET',80100,'url','모든 권한 목록 반환','/auth')
,('DELETE',80200,'url','특정 권한 삭제','/auth/*')
,('GET',90000,'url','계정별 권한 관리 페이지 이동','/member-auth/edit')
,('GET',90100,'url','특정 계정이 보유한 권한 목록 반환','/member/*/auth')
,('PATCH',90200,'url','특정 계정이 보유한 권한 목록 업데이트','/member/*/auth')
;
INSERT INTO res_tb (HTTP_METHOD_PK,RES_ORD,RES_TYPE,RES_NM_UNQ,RES_PATTERN) VALUES 
('GET',20000,'url','로그인 페이지','/login/edit')
,('POST',20001,'url','post 로그인 페이지','/login/edit')
,('PATCH',20003,'url','patch 로그인 페이지','/login/edit')
,('DELETE',20004,'url','delete 로그인 페이지','/login/edit')
,('PATCH',80300,'url','특정 권한 수정','/auth/*')
,('POST',80400,'url','권한 추가','/auth')
,('GET',100000,'url','계정 관리 페이지 이동','/member/edit')
,('GET',100100,'url','모든 계정 목록 반환(password제외)','/member')
,('PATCH',100200,'url','특정 계정 수정','/member/*')
,('GET',100300,'url','회원가입 페이지 이동','/member/registraion')
;
INSERT INTO res_tb (HTTP_METHOD_PK,RES_ORD,RES_TYPE,RES_NM_UNQ,RES_PATTERN) VALUES 
('POST',100400,'url','계정 중복 검사','/check/member/id')
,('POST',100500,'url','회원 등록','/member')
,('DELETE',100600,'url','특정 계정 삭제 / 회원 탈퇴','/member/*')
,('GET',110000,'url','차단 계정 관리 페이지로 이동','/block-member/edit')
,('GET',110100,'url','모든 차단 계정 목록 조회','/block-member')
,('POST',110200,'url','차단 계정 추가','/block-member')
,('PATCH',110300,'url','차단 계정 정보 수정','/block-member/*')
,('DELETE',110400,'url','차단 계정 정보 삭제','/block-member/*')
,('GET',120000,'url','자유게시판 신규 글 작성 페이지','/community/freeboard/write')
,('POST',120100,'url','자유게시판 신규 글 등록','/community/freeboard/write')
;
INSERT INTO res_tb (HTTP_METHOD_PK,RES_ORD,RES_TYPE,RES_NM_UNQ,RES_PATTERN) VALUES 
('GET',120200,'url','자유게시판 상세 조회','/community/freeboard/*')
,('GET',120300,'url','자유게시판 특정 글 수정 페이지','/community/freeboard/*/edit')
,('PATCH',120400,'url','자유게시판 특정 글 수정','/community/freeboard/*')
,('GET',120500,'url','자유 게시판 수정 취소','/community/freeboard/edit-cancel')
;

INSERT INTO mem_auth_tb (MEM_FK_PK,AUTH_FK_PK) VALUES 
(3,1)
,(2,2)
,(4,2)
,(1,3)
,(5,5)
;

INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(1,1)
,(2,1)
,(3,1)
,(28,1)
,(31,1)
,(34,1)
,(35,1)
,(36,1)
,(37,1)
,(38,1)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(39,1)
,(40,1)
,(41,1)
,(42,1)
,(43,1)
,(44,1)
,(2,2)
,(3,2)
,(31,2)
,(39,2)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(40,2)
,(41,2)
,(42,2)
,(43,2)
,(44,2)
,(1,3)
,(2,3)
,(3,3)
,(4,3)
,(5,3)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(6,3)
,(7,3)
,(8,3)
,(9,3)
,(10,3)
,(11,3)
,(12,3)
,(13,3)
,(14,3)
,(15,3)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(16,3)
,(17,3)
,(18,3)
,(19,3)
,(20,3)
,(25,3)
,(26,3)
,(27,3)
,(28,3)
,(29,3)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(31,3)
,(32,3)
,(33,3)
,(34,3)
,(35,3)
,(36,3)
,(37,3)
,(38,3)
,(39,3)
,(40,3)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(41,3)
,(42,3)
,(43,3)
,(44,3)
,(2,4)
,(3,4)
,(21,4)
,(22,4)
,(23,4)
,(24,4)
;
INSERT INTO res_auth_tb (RES_FK_PK,AUTH_FK_PK) VALUES 
(30,4)
,(31,4)
,(41,4)
,(2,5)
,(3,5)
,(31,5)
;

INSERT INTO auth_grp_tb (AUTH_GRP_PK,AUTH_GRP_EXPLN) VALUES 
('PUBLIC','전체공개-비회원 조회가 가능합니다.')
,('MEMBER','회원공개-로그인 한 계정의 조회가 가능합니다.')
,('SECRET','비밀글-작성자와 답글의 경우 대상글의 작성자, 매니저, 어드민만 조회 할 수 있습니다.')
,('CLOSE','비공개-작성자와 매니저, 어드민만 조회 할 수 있습니다.')
;

INSERT INTO auth_grp_auth_tb (AUTH_GRP_FK_PK,AUTH_FK_PK) VALUES 
('PUBLIC',1)
,('MEMBER',1)
,('SECRET',1)
,('CLOSE',1)
,('PUBLIC',2)
,('MEMBER',2)
,('PUBLIC',3)
,('MEMBER',3)
,('SECRET',3)
,('CLOSE',3)
;
INSERT INTO auth_grp_auth_tb (AUTH_GRP_FK_PK,AUTH_FK_PK) VALUES 
('PUBLIC',4)
,('PUBLIC',5)
;

