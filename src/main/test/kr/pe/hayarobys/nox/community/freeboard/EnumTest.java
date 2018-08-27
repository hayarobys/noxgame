package kr.pe.hayarobys.nox.community.freeboard;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suph.security.core.enums.TempSaveCategory;

public class EnumTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(EnumTest.class);
	@Test
	public void test(){
		
		// 결과: FREEBOARD
		LOGGER.debug("프리보드.네임: {}", TempSaveCategory.FREEBOARD.name());
		// 결과: 1
		LOGGER.debug("프리보드.오디널: {}", TempSaveCategory.FREEBOARD.ordinal());
		// 결과: FREEBOARD
		LOGGER.debug("밸류오브(프리보드): {}", TempSaveCategory.valueOf("FREEBOARD"));
		
		// 결과: FREEBOARD
		LOGGER.debug("프리보드: {}", TempSaveCategory.FREEBOARD);
		// 결과: FREEBOARD
		LOGGER.debug("프리보드.투스트링: {}", TempSaveCategory.FREEBOARD.toString());
		
		// 결과: 2
		//LOGGER.debug("프리보드.겟밸류: {}", TempSaveCategory.FREEBOARD.getValue());
		// 결과: FREEBOARD
		//LOGGER.debug("밸류오브(2): {}", TempSaveCategory.valueOf(2));
	}

}
