package com.uninterruptedhome.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uninterruptedhome.core.PaidService;
import com.uninterruptedhome.core.Service;
import com.uninterruptedhome.core.UnPaidService;

public class ServiceImpl {
	
	private ServiceImpl() {}
	private static ServiceImpl serviceImpl;
	private List<Service> allService = new ArrayList<Service>();

	public List<Service> getAllService() {
		return allService;
	}

	public void addService(String serviceName, double amount, Date dueDate, Date lastPaymentDate) {
		UnPaidService newService = new UnPaidService();
		newService.setServiceName(serviceName);
		newService.setAmount(amount);
		newService.setDueDate(dueDate);
		newService.setLastPaymentDate(lastPaymentDate);
		newService.setIsPaid(false);
		allService.add(newService);
	}
	
	public void addService(String serviceName, double amount, String dueDate, String lastPaymentDate) throws ParseException {
		UnPaidService newService = new UnPaidService();
		newService.setServiceName(serviceName);
		newService.setAmount(amount);
		newService.setDueDateInString(dueDate);
		newService.setLastPaymentDateInString(lastPaymentDate);
		newService.setIsPaid(false);
		allService.add(newService);
	}
	
	public void addService(String serviceName, double amount, String dueDate) throws ParseException {
		addService(serviceName, amount, dueDate, null);
	}
	
	public void addService(String serviceName, double amount, Date dueDate) {
		addService(serviceName, amount, dueDate, null);
	}

	public void removeService(int num) {
		Service serviceToRemove = null;
		for(Service s: getAllService()) {
			if(num == s.getId()) {
				serviceToRemove = s;
				break;
			}
		}
		getAllService().remove(serviceToRemove);
	}
	
	public String getServiceName(int num) throws IllegalAccessException {
		return getParticularService(num).getServiceName();
	}
	
	private Service getParticularService(int num) throws IllegalAccessException {
		Service service = null;
		for(Service s: getAllService()) {
			if(num == s.getId())
				service = s;
		}
		if(service == null) {
			throw new IllegalAccessException();
		}
		return service;
	}

	public void paymentForService(int num, int isToday, String dueDate) throws ParseException, IllegalAccessException {
		Service service = getParticularService(num);
		if(service instanceof PaidService) {
			throw new IllegalAccessException();
		}
		PaidService ps = null;
		if(isToday == 1) 
			ps = new PaidService(service.getId(), service.getServiceName(), service.getAmount(), new Date());
		else if(isToday == 2)
			ps = new PaidService(service.getId(), service.getServiceName(), service.getAmount(), dueDate);
		else
			throw new IllegalAccessException();
		
		getAllService().remove(service);
		getAllService().add(ps);
	}
	
	public void changeToUnpaidService(int num, int isToday, String dueDate) throws ParseException, IllegalAccessException {
		Service service = getParticularService(num);
		if(service instanceof UnPaidService) {
			throw new IllegalAccessException();
		}
		UnPaidService ups = null;
		if(isToday == 2) 
			ups = new UnPaidService(service.getId(), service.getServiceName(), service.getAmount(), new Date());
		else if(isToday == 1)
			ups = new UnPaidService(service.getId(), service.getServiceName(), service.getAmount(), dueDate);
		else
			throw new IllegalAccessException();
		
		getAllService().remove(service);
		getAllService().add(ups);
	}
	
	public void changeDueDate(int num, int isToday, String dueDate) throws ParseException, IllegalAccessException {
		Service service = getParticularService(num);
		if(service instanceof PaidService) {
			throw new IllegalStateException();
		}
		UnPaidService ups = (UnPaidService) service;
		if(isToday == 1) 
			ups.setDueDate(new Date());
		else if(isToday == 2)
			ups.setDueDateInString(dueDate);
	}
	
	public void changePaidDate(int num, int isToday, String paidDate) throws ParseException, IllegalAccessException {
		Service service = getParticularService(num);
		if(service instanceof UnPaidService) {
			throw new IllegalStateException();
		}
		PaidService ps = (PaidService) service;
		if(isToday == 1) 
			ps.setPaidDate(new Date());
		else if(isToday == 2) 
			ps.setPaidDateInString(paidDate);
	}
	
	public void printAllService() {
		System.out.println("Services are: ");
		System.out.println("ID " + " Status         " + "Amount   " + "Name   ");
		for(Service s: allService) {
			System.out.println(s.getId() + ".  " + (s.isPaid()? "Paid Amount: " : "UnPaid Amount: ") + s.getAmount() + "    " + s.getServiceName());
		}
		System.out.println();
	}
	
	public void printUnpaidService() {
		System.out.println("Unpaid Services are: ");
		System.out.println("ID " + "Amount  " + "Due Date          " + "Name   ");
		for(Service s: allService) {
			if(s instanceof UnPaidService) {
				UnPaidService ups = (UnPaidService) s;
				System.out.println(ups.getId() + ".  " + ups.getAmount() + "   " + ups.getDueDateInString() + "    " + ups.getServiceName());
			}
		}
		System.out.println();
	}
	
	public void printPaidService() {
		System.out.println("Paid Services are: ");
		System.out.println("ID " + "Amount  " + "Paid Date     " + "Name   ");
		for(Service s: allService) {
			if(s instanceof PaidService) {
				PaidService ps = (PaidService) s;
				System.out.println(ps.getId() + ". "+ ps.getAmount() + "   " + ps.getPaidDateInString() + "    "+ ps.getServiceName());
			}
		}
		System.out.println();
	}
	
	public static ServiceImpl getServices() {
		if(serviceImpl == null)
			serviceImpl = new ServiceImpl();
		return serviceImpl;
	}
}
