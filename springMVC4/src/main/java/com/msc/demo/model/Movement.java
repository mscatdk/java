package com.msc.demo.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.joda.time.DateTime;

import com.msc.demo.common.MessageUtil;

public class Movement {
	
	private String fromAccount;
	
	private String toAccount;
	
	private DateTime dateOfEntry;
	
	private BigDecimal amount;
	
	private MovementType movementType;

	public Movement() { }
	
	public Movement(String fromAccountNumber, String toAccountNumber, BigDecimal amount, MovementType movementType) {
		this.fromAccount = fromAccountNumber;
		this.toAccount = toAccountNumber;
		this.amount = amount;
		this.dateOfEntry = new DateTime();
		this.movementType = movementType;
	}
	
	public Movement(Account fromAccount, Account toAccount, BigDecimal amount, MovementType movementType) {
		this.fromAccount = fromAccount.getAccountNumber();
		this.toAccount = toAccount.getAccountNumber();
		this.amount = amount;
		this.dateOfEntry = new DateTime();
		this.movementType = movementType;
	}
	
	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public DateTime getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(DateTime dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}
	
	public String getAmountFormated() {
		return NumberFormat.getCurrencyInstance(Locale.CANADA).format(this.amount.setScale(2, 0));
	}
	
	public String getDateOfEntryFormatted() {
		return this.dateOfEntry.toString("yyyy-MM-dd");
	}
	
	public String getMovementTypeFormatted() {
		String key = "ENUM.MovementType." + MovementType.PAYMENT.name();
		return MessageUtil.getMessage(key);
	}
	
}
