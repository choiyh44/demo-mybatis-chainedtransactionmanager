package kr.ensmart.demo.chainedtransactionmanager.app.sample.service;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
//@TestMethodOrder(OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SampleService_Db2Db1TransactionManagerTest {
	@Autowired
	SampleService sampleService;

	@Test
	void insertExceptionFromDb1WithDb2Db1TxManager() throws Exception {
		Instant start = Instant.now();
		
		sampleService.insertExceptionFromDb1WithDb2Db1TxManager();
		
		Instant end = Instant.now();
		log.info("insertSample2s duration: {} ms", Duration.between(start, end).toMillis());
	}

	@Test
	void insertExceptionFromDb2WithDb2Db1TxManager() throws Exception {
		Instant start = Instant.now();
		
		sampleService.insertExceptionFromDb2WithDb2Db1TxManager();
		
		Instant end = Instant.now();
		log.info("insertSample2s duration: {} ms", Duration.between(start, end).toMillis());
	}

}
