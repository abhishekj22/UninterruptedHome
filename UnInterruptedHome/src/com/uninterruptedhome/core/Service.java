package com.uninterruptedhome.core;

public abstract class Service {
	protected int id;
	private static int count;
	private String serviceName;
	private boolean isPaid;
	private double amount;
	
	public Service() {
		count++;
		id = count;
	}

	public int getId() {
		return id;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public boolean isPaid() {
		return isPaid;
	}
	
	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
