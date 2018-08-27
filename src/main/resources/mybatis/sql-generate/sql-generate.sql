-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- MEM_TB Table Create SQL
CREATE TABLE MEM_TB
(
    `MEM_SQ_PK`          INT             NOT NULL    AUTO_INCREMENT COMMENT '계정 시퀀스 기본키', 
    `MEM_JOIN_DT`        TIMESTAMP       NOT NULL    COMMENT '계정 가입 날짜', 
    `MEM_LAST_LOGIN_DT`  TIMESTAMP       NOT NULL    COMMENT '계정 마지막 로그인 날짜', 
    `MEM_STATE`          VARCHAR(20)     NOT NULL    COMMENT '계정 상태', 
    `MEM_NICKNM`         VARCHAR(30)     NOT NULL    COMMENT '계정 닉네임', 
    `MEM_ID`             VARCHAR(50)     NOT NULL    COMMENT '계정 아이디', 
    `MEM_PSWD`           VARCHAR(300)    NOT NULL    COMMENT '계정 비밀번호', 
    PRIMARY KEY (MEM_SQ_PK)
);

ALTER TABLE MEM_TB COMMENT '계정 테이블';


-- AUTH_GRP_TB Table Create SQL
CREATE TABLE AUTH_GRP_TB
(
    `AUTH_GRP_PK`     VARCHAR(45)    NOT NULL    COMMENT 'PUBLIC, MEMBER, SECRET, CLOSE', 
    `AUTH_GRP_EXPLN`  VARCHAR(50)    NOT NULL    COMMENT '권한 묶음 설명', 
    PRIMARY KEY (AUTH_GRP_PK)
);

ALTER TABLE AUTH_GRP_TB COMMENT '최소 조회 권한 그룹 테이블';


-- FILE_GRP_TB Table Create SQL
CREATE TABLE FILE_GRP_TB
(
    `FILE_GRP_SQ_PK`   INT            NOT NULL    AUTO_INCREMENT COMMENT '파일 묶음 시퀀스 기본키', 
    `MEM_FK`           INT            NOT NULL    COMMENT '계정 외래키', 
    `AUTH_GRP_FK`      VARCHAR(45)    NOT NULL    COMMENT '최소 조회 권한', 
    `FILE_GRP_REG_DT`  TIMESTAMP      NOT NULL    COMMENT '파일 묶음 등록 날짜', 
    PRIMARY KEY (FILE_GRP_SQ_PK)
);

ALTER TABLE FILE_GRP_TB COMMENT '파일 묶음 테이블';

ALTER TABLE FILE_GRP_TB ADD CONSTRAINT FK_FILE_GRP_TB_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE FILE_GRP_TB ADD CONSTRAINT FK_FILE_GRP_TB_AUTH_GRP_FK_AUTH_GRP_TB_AUTH_GRP_PK FOREIGN KEY (AUTH_GRP_FK)
 REFERENCES AUTH_GRP_TB (AUTH_GRP_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- AUTH_TB Table Create SQL
CREATE TABLE AUTH_TB
(
    `AUTH_SQ_PK`   INT            NOT NULL    AUTO_INCREMENT COMMENT '권한 시퀀스 기본키', 
    `AUTH_NM_UNQ`  VARCHAR(50)    NOT NULL    COMMENT '권한 이름 유니크', 
    `AUTH_EXPLN`   VARCHAR(50)    NOT NULL    COMMENT '권한 설명', 
    PRIMARY KEY (AUTH_SQ_PK)
);

ALTER TABLE AUTH_TB COMMENT '권한 테이블';

ALTER TABLE AUTH_TB
    ADD CONSTRAINT UC_AUTH_NM_UNQ UNIQUE (AUTH_NM_UNQ);


-- CMT_GRP_TB Table Create SQL
CREATE TABLE CMT_GRP_TB
(
    `CMT_GRP_SQ_PK`       INT        NOT NULL    AUTO_INCREMENT COMMENT '댓글 묶음 시퀀스 기본키', 
    `CMT_GRP_NEW_WRT_FL`  TINYINT    NOT NULL    DEFAULT true COMMENT '댓글 묶음 신규 작성 플래그 Y면 신규 작성 가능', 
    PRIMARY KEY (CMT_GRP_SQ_PK)
);

ALTER TABLE CMT_GRP_TB COMMENT '댓글 그룹 테이블';


-- FRBRD_GRP_TB Table Create SQL
CREATE TABLE FRBRD_GRP_TB
(
    `FRBRD_GRP_SQ_PK`     INT            NOT NULL    AUTO_INCREMENT COMMENT '자유게시판 묶음 시퀀스 기본키', 
    `MEM_FK`              INT            NOT NULL    COMMENT '계정 외래키', 
    `CMT_GRP_FK`          INT            NOT NULL    COMMENT '댓글 묶음 외래키', 
    `AUTH_GRP_FK`         VARCHAR(45)    NOT NULL    COMMENT '최소 조회 권한', 
    `FRBRD_GRP_CLS_FK`    INT            NULL        COMMENT 'DELETE CASCADE 계층의 부모 키 default는 자기 자신의 게시글 그룹 일련 번호와 동일', 
    `FRBRD_GRP_CLS_ORD`   INT            NOT NULL    DEFAULT 0 COMMENT '계층 내 정렬순서 default 0', 
    `FRBRD_GRP_CLS_DPTH`  INT            NOT NULL    DEFAULT 1 COMMENT '계층 내 깊이 default 1', 
    PRIMARY KEY (FRBRD_GRP_SQ_PK)
);

ALTER TABLE FRBRD_GRP_TB COMMENT '자유게시판 묶음 테이블';

ALTER TABLE FRBRD_GRP_TB ADD CONSTRAINT FK_FRBRD_GRP_TB_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE FRBRD_GRP_TB ADD CONSTRAINT FK_FRBRD_GRP_TB_CMT_GRP_FK_CMT_GRP_TB_CMT_GRP_SQ_PK FOREIGN KEY (CMT_GRP_FK)
 REFERENCES CMT_GRP_TB (CMT_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE FRBRD_GRP_TB ADD CONSTRAINT FK_FRBRD_GRP_TB_FRBRD_GRP_CLS_FK_FRBRD_GRP_TB_FRBRD_GRP_SQ_PK FOREIGN KEY (FRBRD_GRP_CLS_FK)
 REFERENCES FRBRD_GRP_TB (FRBRD_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE FRBRD_GRP_TB ADD CONSTRAINT FK_FRBRD_GRP_TB_AUTH_GRP_FK_AUTH_GRP_TB_AUTH_GRP_PK FOREIGN KEY (AUTH_GRP_FK)
 REFERENCES AUTH_GRP_TB (AUTH_GRP_PK)  ON DELETE CASCADE ON UPDATE RESTRICT;


-- RES_TB Table Create SQL
CREATE TABLE RES_TB
(
    `RES_SQ_PK`       INT             NOT NULL    AUTO_INCREMENT COMMENT '리소스 시퀀스 기본키', 
    `HTTP_METHOD_PK`  VARCHAR(10)     NOT NULL    COMMENT 'HTTP 메소드 기본키', 
    `RES_ORD`         INT             NOT NULL    COMMENT '리소스 정렬순서', 
    `RES_TYPE`        VARCHAR(10)     NOT NULL    COMMENT '리소스 타입 url : URL, method : Method', 
    `RES_NM_UNQ`      VARCHAR(50)     NOT NULL    COMMENT '리소스 이름 유니크', 
    `RES_PATTERN`     VARCHAR(100)    NOT NULL    COMMENT '리소스 패턴', 
    PRIMARY KEY (RES_SQ_PK, HTTP_METHOD_PK)
);

ALTER TABLE RES_TB COMMENT '리소스 테이블(복합키)';

ALTER TABLE RES_TB
    ADD CONSTRAINT UC_RES_NM_UNQ UNIQUE (RES_NM_UNQ);


-- CMT_TB Table Create SQL
CREATE TABLE CMT_TB
(
    `CMT_SQ_PK`     INT            NOT NULL    AUTO_INCREMENT COMMENT '댓글 시퀀스 기본키', 
    `CMT_GRP_FK`    INT            NOT NULL    COMMENT '댓글 묶음 외래키', 
    `MEM_FK`        INT            NOT NULL    COMMENT '계정 외래키', 
    `FILE_GRP_FK`   INT            NOT NULL    COMMENT '파일 묶음 외래키', 
    `AUTH_GRP_FK`   VARCHAR(45)    NOT NULL    COMMENT '최소 조회 권한', 
    `CMT_CLS_FK`    INT            NOT NULL    COMMENT '댓글 계층 외래키 default 댓글 일련 번호와 동일', 
    `CMT_CLS_ORD`   INT            NOT NULL    DEFAULT 0 COMMENT '댓글 계층 정렬순서 default 0', 
    `CMT_CLS_DPTH`  INT            NOT NULL    DEFAULT 1 COMMENT '댓글 계층 깊이 default 1', 
    `CMT_REG_DT`    TIMESTAMP      NOT NULL    COMMENT '댓글 등록 날짜', 
    `CMT_MOD_DT`    TIMESTAMP      NOT NULL    COMMENT '댓글 수정 날짜', 
    `CMT_SCRT_FL`   TINYINT        NOT NULL    DEFAULT false COMMENT '댓글 비밀 플래그 Y면 작성자, 부모댓글 작성자, 게시글 작성자, 관리자만 볼 수 있음', 
    `CMT_BODY`      TINYTEXT       NOT NULL    COMMENT '댓글 본문 255글자', 
    PRIMARY KEY (CMT_SQ_PK)
);

ALTER TABLE CMT_TB COMMENT '댓글 테이블';

ALTER TABLE CMT_TB ADD CONSTRAINT FK_CMT_TB_CMT_GRP_FK_CMT_GRP_TB_CMT_GRP_SQ_PK FOREIGN KEY (CMT_GRP_FK)
 REFERENCES CMT_GRP_TB (CMT_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE CMT_TB ADD CONSTRAINT FK_CMT_TB_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE CMT_TB ADD CONSTRAINT FK_CMT_TB_FILE_GRP_FK_FILE_GRP_TB_FILE_GRP_SQ_PK FOREIGN KEY (FILE_GRP_FK)
 REFERENCES FILE_GRP_TB (FILE_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE CMT_TB ADD CONSTRAINT FK_CMT_TB_CMT_CLS_FK_CMT_TB_CMT_SQ_PK FOREIGN KEY (CMT_CLS_FK)
 REFERENCES CMT_TB (CMT_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE CMT_TB ADD CONSTRAINT FK_CMT_TB_AUTH_GRP_FK_AUTH_GRP_TB_AUTH_GRP_PK FOREIGN KEY (AUTH_GRP_FK)
 REFERENCES AUTH_GRP_TB (AUTH_GRP_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- MEM_AUTH_TB Table Create SQL
CREATE TABLE MEM_AUTH_TB
(
    `MEM_FK_PK`   INT    NOT NULL    COMMENT '계정 외래키 기본키', 
    `AUTH_FK_PK`  INT    NOT NULL    COMMENT '권한 외래키 기본키', 
    PRIMARY KEY (MEM_FK_PK, AUTH_FK_PK)
);

ALTER TABLE MEM_AUTH_TB COMMENT '계정-권한 테이블(복합키)';

ALTER TABLE MEM_AUTH_TB ADD CONSTRAINT FK_MEM_AUTH_TB_MEM_FK_PK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK_PK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE MEM_AUTH_TB ADD CONSTRAINT FK_MEM_AUTH_TB_AUTH_FK_PK_AUTH_TB_AUTH_SQ_PK FOREIGN KEY (AUTH_FK_PK)
 REFERENCES AUTH_TB (AUTH_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- RES_AUTH_TB Table Create SQL
CREATE TABLE RES_AUTH_TB
(
    `RES_FK_PK`   INT    NOT NULL    COMMENT '리소스 외래키 기본키', 
    `AUTH_FK_PK`  INT    NOT NULL    COMMENT '권한 외래키 기본키', 
    PRIMARY KEY (RES_FK_PK, AUTH_FK_PK)
);

ALTER TABLE RES_AUTH_TB COMMENT '리소스-권한 테이블(복합키)';

ALTER TABLE RES_AUTH_TB ADD CONSTRAINT FK_RES_AUTH_TB_RES_FK_PK_RES_TB_RES_SQ_PK FOREIGN KEY (RES_FK_PK)
 REFERENCES RES_TB (RES_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE RES_AUTH_TB ADD CONSTRAINT FK_RES_AUTH_TB_AUTH_FK_PK_AUTH_TB_AUTH_SQ_PK FOREIGN KEY (AUTH_FK_PK)
 REFERENCES AUTH_TB (AUTH_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- AUTH_GRP_AUTH_TB Table Create SQL
CREATE TABLE AUTH_GRP_AUTH_TB
(
    `AUTH_GRP_FK_PK`  VARCHAR(45)    NOT NULL    COMMENT '권한 묶음 외래키 기본키', 
    `AUTH_FK_PK`      INT            NOT NULL    COMMENT '권한 외래키 기본키', 
    PRIMARY KEY (AUTH_GRP_FK_PK, AUTH_FK_PK)
);

ALTER TABLE AUTH_GRP_AUTH_TB COMMENT '권한 그룹-권한 테이블(복합키)';

ALTER TABLE AUTH_GRP_AUTH_TB ADD CONSTRAINT FK_AUTH_GRP_AUTH_TB_AUTH_FK_PK_AUTH_TB_AUTH_SQ_PK FOREIGN KEY (AUTH_FK_PK)
 REFERENCES AUTH_TB (AUTH_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE AUTH_GRP_AUTH_TB ADD CONSTRAINT FK_AUTH_GRP_AUTH_TB_AUTH_GRP_FK_PK_AUTH_GRP_TB_AUTH_GRP_PK FOREIGN KEY (AUTH_GRP_FK_PK)
 REFERENCES AUTH_GRP_TB (AUTH_GRP_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- BLOCK_TB Table Create SQL
CREATE TABLE BLOCK_TB
(
    `BLOCK_SQ_PK`        INT             NOT NULL    AUTO_INCREMENT COMMENT '차단 시퀀스 기본키', 
    `BLOCK_TRGT_MEM_FK`  INT             NOT NULL    COMMENT '차단 대상 계정 외래키', 
    `BLOCK_REG_MEM_FK`   INT             NOT NULL    COMMENT '차단 등록 계정 외래키', 
    `BLOCK_REG_DT`       TIMESTAMP       NOT NULL    COMMENT '차단 등록 날짜', 
    `BLOCK_START_DT`     TIMESTAMP       NOT NULL    COMMENT '차단 시작 날짜', 
    `BLOCK_EXPIRE_DT`    TIMESTAMP       NOT NULL    COMMENT '차단 만료 날짜', 
    `BLOCK_CAUSE`        VARCHAR(300)    NOT NULL    COMMENT '차단 이유', 
    PRIMARY KEY (BLOCK_SQ_PK)
);

ALTER TABLE BLOCK_TB COMMENT '차단 계정 테이블';

ALTER TABLE BLOCK_TB ADD CONSTRAINT FK_BLOCK_TB_BLOCK_TRGT_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (BLOCK_TRGT_MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE BLOCK_TB ADD CONSTRAINT FK_BLOCK_TB_BLOCK_REG_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (BLOCK_REG_MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- FILE_TB Table Create SQL
CREATE TABLE FILE_TB
(
    `FILE_SQ_PK`     INT             NOT NULL    AUTO_INCREMENT COMMENT '파일 시퀀스 기본키', 
    `FILE_GRP_FK`    INT             NOT NULL    COMMENT '파일 묶음 외래키', 
    `MEM_FK`         INT             NOT NULL    COMMENT '계정 외래키', 
    `TEMP_FL`        TINYINT         NOT NULL    DEFAULT true COMMENT '임시로 등록된 파일인지의 여부. 게시글 수정 취소 시 임시 플래그가 Y인 데이터들을 물리적 경로에서도 제거하기 위해 사용.', 
    `FILE_REG_DT`    TIMESTAMP       NOT NULL    COMMENT '파일 등록 날짜', 
    `DWN_CNT`        INT             NOT NULL    DEFAULT 0 COMMENT '다운로드 수', 
    `FILE_SIZE`      INT(11)         NOT NULL    COMMENT '파일 크기', 
    `EXT_NM`         VARCHAR(45)     NOT NULL    COMMENT '확장자 이름', 
    `ORGN_FILE_NM`   VARCHAR(200)    NOT NULL    COMMENT '원본 파일 이름', 
    `SAVE_FILE_NM`   VARCHAR(200)    NOT NULL    COMMENT '저장 파일 이름', 
    `FILE_SAVE_DIR`  VARCHAR(200)    NOT NULL    COMMENT '파일 저장 경로', 
    PRIMARY KEY (FILE_SQ_PK)
);

ALTER TABLE FILE_TB COMMENT '파일 테이블';

ALTER TABLE FILE_TB ADD CONSTRAINT FK_FILE_TB_FILE_GRP_FK_FILE_GRP_TB_FILE_GRP_SQ_PK FOREIGN KEY (FILE_GRP_FK)
 REFERENCES FILE_GRP_TB (FILE_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE FILE_TB ADD CONSTRAINT FK_FILE_TB_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- FRBRD_TB Table Create SQL
CREATE TABLE FRBRD_TB
(
    `FRBRD_SQ_PK`   INT           NOT NULL    AUTO_INCREMENT COMMENT '자유게시판 시퀀스 기본키', 
    `FRBRD_GRP_FK`  INT           NOT NULL    COMMENT 'DELETE CASCADE', 
    `FILE_GRP_FK`   INT           NOT NULL    COMMENT '파일 묶음 외래키', 
    `FRBRD_REG_DT`  TIMESTAMP     NOT NULL    COMMENT '자유게시판 등록 날짜', 
    `FRBRD_TITLE`   TINYTEXT      NOT NULL    COMMENT '자유게시판 제목', 
    `FRBRD_BODY`    MEDIUMTEXT    NOT NULL    COMMENT '자유게시판 본문', 
    PRIMARY KEY (FRBRD_SQ_PK)
);

ALTER TABLE FRBRD_TB COMMENT '자유게시판 상세 테이블';

ALTER TABLE FRBRD_TB ADD CONSTRAINT FK_FRBRD_TB_FRBRD_GRP_FK_FRBRD_GRP_TB_FRBRD_GRP_SQ_PK FOREIGN KEY (FRBRD_GRP_FK)
 REFERENCES FRBRD_GRP_TB (FRBRD_GRP_SQ_PK)  ON DELETE CASCADE ON UPDATE RESTRICT;
ALTER TABLE FRBRD_TB ADD CONSTRAINT FK_FRBRD_TB_FILE_GRP_FK_FILE_GRP_TB_FILE_GRP_SQ_PK FOREIGN KEY (FILE_GRP_FK)
 REFERENCES FILE_GRP_TB (FILE_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TEMP_SAVE_TB Table Create SQL
CREATE TABLE TEMP_SAVE_TB
(
    `TEMP_SAVE_SQ_PK`     INT                                                                                    NOT NULL    AUTO_INCREMENT COMMENT '임시 저장 시퀀스 기본키', 
    `MEM_FK`              INT                                                                                    NOT NULL    COMMENT '계정 외래키', 
    `FILE_GRP_FK`         INT                                                                                    NOT NULL    COMMENT '파일 묶음 외래키', 
    `TEMP_SAVE_CATEGORY`  ENUM('MEMBER_SOFTWARE','FREEBOARD','SCREENSHOT','ART_GALLERY','RECOMMEND_STRATEGY')    NOT NULL    COMMENT '어느 게시판의 데이터인지 Enum으로 관리', 
    `TEMP_SAVE_USE`       ENUM('WRITE','MODIFY')                                                                 NOT NULL    COMMENT '어떤 용도의 임시 저장 인지 Enum으로 관리. 쓰기 임시 저장과 수정 임시 저장을 구분하는 용도의 컬럼', 
    `TEMP_SAVE_MOD_DT`    TIMESTAMP                                                                              NOT NULL    COMMENT '임시 저장 수정 날짜', 
    `TEMP_SAVE_TITLE`     TINYTEXT                                                                               NOT NULL    COMMENT '임시 저장 제목', 
    `TEMP_SAVE_BODY`      MEDIUMTEXT                                                                             NOT NULL    COMMENT '임시 저장 본문', 
    PRIMARY KEY (TEMP_SAVE_SQ_PK)
);

ALTER TABLE TEMP_SAVE_TB COMMENT '게시글 임시 저장 테이블';

ALTER TABLE TEMP_SAVE_TB ADD CONSTRAINT FK_TEMP_SAVE_TB_MEM_FK_MEM_TB_MEM_SQ_PK FOREIGN KEY (MEM_FK)
 REFERENCES MEM_TB (MEM_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE TEMP_SAVE_TB ADD CONSTRAINT FK_TEMP_SAVE_TB_FILE_GRP_FK_FILE_GRP_TB_FILE_GRP_SQ_PK FOREIGN KEY (FILE_GRP_FK)
 REFERENCES FILE_GRP_TB (FILE_GRP_SQ_PK)  ON DELETE RESTRICT ON UPDATE RESTRICT;


