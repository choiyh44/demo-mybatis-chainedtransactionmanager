package kr.ensmart.demo.chainedtransactionmanager.app.sample.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("sampleParam")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleParam {
	private Integer id;
	private String name;
	private String description;
}
