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
class SampleServiceTest {
	@Autowired
	SampleService sampleService;

	@Test
	void insertSamples() {
		Instant start = Instant.now();
		
		sampleService.insertSamples();
		
		Instant end = Instant.now();
		log.info("insertSamples duration: {} ms", Duration.between(start, end).toMillis());
	}

	@Test
	void insertSample2s() {
		Instant start = Instant.now();
		
		sampleService.insertSample2s();
		
		Instant end = Instant.now();
		log.info("insertSample2s duration: {} ms", Duration.between(start, end).toMillis());
	}

	@Test
	void insertSampleAndSample2s() {
		Instant start = Instant.now();
		
		sampleService.insertSampleAndSample2s();
		
		Instant end = Instant.now();
		log.info("insertSample2s duration: {} ms", Duration.between(start, end).toMillis());
	}

}
