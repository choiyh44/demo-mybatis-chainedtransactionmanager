package kr.ensmart.demo.chainedtransactionmanager.app.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ensmart.demo.chainedtransactionmanager.app.sample.dto.Sample2Param;
import kr.ensmart.demo.chainedtransactionmanager.app.sample.dto.SampleParam;
import kr.ensmart.demo.chainedtransactionmanager.app.sample.repository.db1.SampleMapper;
import kr.ensmart.demo.chainedtransactionmanager.app.sample.repository.db2.Sample2Mapper;

@Service
public class SampleService {
	private static final int rows = 10; 
	
	@Autowired
	SampleMapper sampleMapper;
	
	@Autowired
	Sample2Mapper sample2Mapper;
	
	@Transactional()
	void insertSamples() {
		List<SampleParam> sampleParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sampleParamList.add(SampleParam.builder().name(i + " name").description(i+ " description").build());
		}
		
		for (SampleParam sampleParam : sampleParamList) {
			sampleMapper.insertSample(sampleParam);
		}

	}
	
	@Transactional()
	void insertSample2s() {
		List<Sample2Param> sample2ParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sample2ParamList.add(Sample2Param.builder().name(i + " name").description(i+ " description").build());
		}
		
		for (Sample2Param sample2Param : sample2ParamList) {
			sample2Mapper.insertSample2(sample2Param);
		}

	}
	
	@Transactional(transactionManager="db1Db2TransactionManager")
	void insertSampleAndSample2s() {
		insertSamples();
		insertSample2s();
	}
	
	@Transactional(transactionManager="db1Db2TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb1WithDb1Db2TxManager() throws Exception {
		List<SampleParam> sampleParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sampleParamList.add(SampleParam.builder().name(i + " name").description(i+ " description").build());
		}
		
		SampleParam sampleParam;
		for (int i = 0; i < rows; i++) {
			sampleParam = sampleParamList.get(i);
			sampleMapper.insertSample(sampleParam);
			if (i == 5) {
				throw new Exception("XXXXXXXXXXXXXXXX");
			}
		}
		
		insertSample2s();
	}
	
	
	@Transactional(transactionManager="db1Db2TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb2WithDb1Db2TxManager() throws Exception {
		
		insertSamples();

		List<Sample2Param> sample2ParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sample2ParamList.add(Sample2Param.builder().name(i + " name").description(i+ " description").build());
		}
		
		Sample2Param sample2Param;
		for (int i = 0; i < rows; i++) {
			sample2Param = sample2ParamList.get(i);
			sample2Mapper.insertSample2(sample2Param);
			if (i == 5) {
				throw new Exception("XXXXXXXXXXXXXXXX");
			}
		}
	}
	
	@Transactional(transactionManager="db2Db1TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb1WithDb2Db1TxManager() throws Exception {
		List<SampleParam> sampleParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sampleParamList.add(SampleParam.builder().name(i + " name").description(i+ " description").build());
		}
		
		SampleParam sampleParam;
		for (int i = 0; i < rows; i++) {
			sampleParam = sampleParamList.get(i);
			sampleMapper.insertSample(sampleParam);
			if (i == 5) {
				throw new Exception("XXXXXXXXXXXXXXXX");
			}
		}
		
		insertSample2s();
	}
	
	
	@Transactional(transactionManager="db2Db1TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb2WithDb2Db1TxManager() throws Exception {
		insertSamples();

		List<Sample2Param> sample2ParamList = new ArrayList<>();
		
		for (int i = 0; i < rows; i++) {
			sample2ParamList.add(Sample2Param.builder().name(i + " name").description(i+ " description").build());
		}
		
		Sample2Param sample2Param;
		for (int i = 0; i < rows; i++) {
			sample2Param = sample2ParamList.get(i);
			sample2Mapper.insertSample2(sample2Param);
			if (i == 5) {
				throw new Exception("XXXXXXXXXXXXXXXX");
			}
		}
	}
	
	@Transactional(transactionManager="db1Db2TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb1WithDb1Db2TxManagerRollbackTest() throws Exception {
		int i = 1;
		SampleParam sampleParam = SampleParam.builder().id(i).name(i + " name").description(i+ " description").build();
		
		sampleMapper.insertSampleWithId(sampleParam);

		sampleParam.setName("012345678901234567890123456789012345678901234567890123456789");
		sampleMapper.updateSampleName(sampleParam);
		
		insertSample2s();
	}
	
	
	@Transactional(transactionManager="db1Db2TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb2WithDb1Db2TxManagerRollbackTest() throws Exception {
		insertSamples();

		int i = 1;
		Sample2Param sample2Param = Sample2Param.builder().name(i + " name").description(i+ " description").build();
		
		sample2Mapper.insertSample2WithId(sample2Param);

		sample2Param.setName("012345678901234567890123456789012345678901234567890123456789");
		sample2Mapper.updateSample2Name(sample2Param);
	}
	
	@Transactional(transactionManager="db2Db1TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb1WithDb2Db1TxManagerRollbackTest() throws Exception {
		int i = 1;
		SampleParam sampleParam = SampleParam.builder().id(i).name(i + " name").description(i+ " description").build();
		
		sampleMapper.insertSampleWithId(sampleParam);

		sampleParam.setName("012345678901234567890123456789012345678901234567890123456789");
		sampleMapper.updateSampleName(sampleParam);
		
		insertSample2s();
	}
	
	
	@Transactional(transactionManager="db2Db1TransactionManager", rollbackFor=Exception.class)
	void insertExceptionFromDb2WithDb2Db1TxManagerRollbackTest() throws Exception {
		insertSamples();

		int i = 1;
		Sample2Param sample2Param = Sample2Param.builder().name(i + " name").description(i+ " description").build();
		
		sample2Mapper.insertSample2WithId(sample2Param);

		sample2Param.setName("012345678901234567890123456789012345678901234567890123456789");
		sample2Mapper.updateSample2Name(sample2Param);

	}
	
	
}
