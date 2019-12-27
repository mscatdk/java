package com.msc.demo.common;

import org.junit.Assert;
import org.junit.Test;

import com.msc.demo.model.MovementType;


public class MessageUtilTest {
	
	@Test
	public void getKey() {		
		String key = "ENUM.MovementType." + MovementType.PAYMENT.name();
		String msg = MessageUtil.getMessage(key);
		System.out.println(key);
		Assert.assertEquals("Message is wrong!", "Payment", msg);
	}

}
