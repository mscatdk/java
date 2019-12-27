package com.msc.demo.das;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.msc.demo.model.Movement;
import com.msc.demo.model.MovementType;

@Component
public class MovementDAO {
	
	private List<Movement> movementList = new ArrayList<>();
	
	// Inserting demo data at startup
	{
		Movement movement01 = new Movement("", "8888888822", new BigDecimal("2000"), MovementType.PAYMENT);
		movementList.add(movement01);
		
		Movement movement02 = new Movement("", "3337777777", new BigDecimal("2000000"), MovementType.PAYMENT);
		movementList.add(movement02);
	}
	
	/**
	 * Save movement object
	 * @param movement
	 * @return
	 */
	public Movement save(Movement movement) {
		movementList.add(movement);
		return movement;
	}

	public List<Movement> getMovementByAccount(String accountNumber) {
		return movementList.stream().filter(s -> s.getToAccount().equals(accountNumber))
									.collect(Collectors.toList());
	}
	
}
