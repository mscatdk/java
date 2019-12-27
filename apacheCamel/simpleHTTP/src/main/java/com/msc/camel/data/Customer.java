package com.msc.camel.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {

	private Long id;
	private String name;
	private String role;
	
}
