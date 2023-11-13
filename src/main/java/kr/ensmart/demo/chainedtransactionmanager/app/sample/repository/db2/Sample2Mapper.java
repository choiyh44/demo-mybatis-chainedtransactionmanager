package kr.ensmart.demo.chainedtransactionmanager.app.sample.repository.db2;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.ensmart.demo.chainedtransactionmanager.app.sample.dto.Sample2Dto;
import kr.ensmart.demo.chainedtransactionmanager.app.sample.dto.Sample2Param;

@Repository
public interface Sample2Mapper {
	List<Sample2Dto> selectAllSample2s();
	
	void insertSample2(Sample2Param sample2Param);
	void insertSample2WithId(Sample2Param sample2Param);
	void updateSample2Name(Sample2Param sample2Param);

}
