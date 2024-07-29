package com.web.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Study01DTO {

	private int no;
	private byte[] file;
	private String type;
	private long size;
	private boolean del;
	
}
