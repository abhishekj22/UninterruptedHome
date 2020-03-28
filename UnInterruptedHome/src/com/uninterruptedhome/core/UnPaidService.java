package com.uninterruptedhome.core;

import java.text.ParseException;
import java.util.Date;

public class UnPaidService extends Service implements DateFormatSupport {

	public UnPaidService() {
		super();
	}
	
	public UnPaidService(int sid, String name, double amount, Date date) {
		id = sid;
		setServiceName(name);
		setAmount(amount);
		setIsPaid(false);
		setDueDate(date);
	}
	
	public UnPaidService(int sid, String name, double amount, String date) throws ParseException {
		id = sid;
		setServiceName(name);
		setAmount(amount);
		setIsPaid(false);
		setDueDateInString(date);
	}
	
	private Date dueDate;
	private Date lastPaymentDate;
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	
	public void setDueDateInString(String date) throws ParseException {
		this.dueDate = dateFormat.parse(date);
	}
	
	public void setLastPaymentDateInString(String date) throws ParseException {
		if(date == null) {
			this.lastPaymentDate = null;
		}
		else {
			this.lastPaymentDate = dateFormat.parse(date);
		}
	}
	
	public String getDueDateInString() {
		return dateFormat.format(dueDate);
	}
	
	public String getLastPaymentDateInString() {
		return dateFormat.format(lastPaymentDate);
	}
}
