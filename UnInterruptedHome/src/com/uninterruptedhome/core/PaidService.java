package com.uninterruptedhome.core;

import java.text.ParseException;
import java.util.Date;

public class PaidService extends Service implements DateFormatSupport {
	
	public PaidService(int sid, String name, double amount, Date date) {
		id = sid;
		setServiceName(name);
		setAmount(amount);
		setIsPaid(true);
		setPaidDate(date);
	}
	
	public PaidService(int sid, String name, double amount, String date) throws ParseException {
		id = sid;
		setServiceName(name);
		setAmount(amount);
		setIsPaid(true);
		setPaidDateInString(date);
	}
	
	private Date paidDate;

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	public void setPaidDateInString(String date) throws ParseException {
		this.paidDate = dateFormat.parse(date);
	}
	
	public String getPaidDateInString() {
		return dateFormat.format(paidDate);
	}

}
