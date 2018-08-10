CREATE PROCEDURE `insert_freeboard_group`(
	IN _MEM_NO INT,
	IN _COMMENT_GROUP_NO INT,
	IN _FILE_GROUP_NO INT,
	IN _AUTH_GROU_NO INT,
	IN _FREEBOARD_GROUP_CLASS_ORDER INT,
	IN _FREEBOARD_GROUP_CLASS_DEPTH INT,
	OUT RESULT INT
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;        
		SET RESULT = -1;  
	END;
	
	START TRANSACTION;
		SET FOREIGN_KEY_CHECKS = 0;
		
		INSERT INTO FRBRD_GRP_TB(
			MEM_FK, CMT_GRP_FK, FILE_GRP_FK, AUTH_GRP_FK, FRBRD_GRP_CLS_ORD, FRBRD_GRP_CLS_DPTH
		)VALUES(
			_MEM_NO, _COMMENT_GROUP_NO, _FILE_GROUP_NO, _AUTH_GROU_NO, _FREEBOARD_GROUP_CLASS_ORDER, _FREEBOARD_GROUP_CLASS_DEPTH
		);
		
		UPDATE	FRBRD_GRP_TB
		SET		FRBRD_GRP_CLS_FK = LAST_INSERT_ID()
		WHERE	FRBRD_GRP_SQ_PK = LAST_INSERT_ID();
		
		SET FOREIGN_KEY_CHECKS = 1;
	COMMIT;
	SET RESULT = LAST_INSERT_ID();
END