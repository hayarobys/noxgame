package com.suph.security.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suph.security.core.dto.TestVO;

@Repository
public interface TestDAO{
	public abstract void insertTestFile(TestVO testVO);
	public abstract List<TestVO> selectTestFile();
}
