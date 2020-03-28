package com.uninterruptedhome.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	
	public void addService(String serviceName, double amount, String dueDate, String lastPaymentDate) {
		UnPaidService newService = new UnPaidService();
		newService.setServiceName(serviceName);
		newService.setAmount(amount);
		try {
			newService.setDueDateInString(dueDate);
			newService.setLastPaymentDateInString(lastPaymentDate);
		} catch (ParseException e) {
			System.out.println("Date format should be DD/MM/YYYY");
		}
		newService.setIsPaid(false);
		allService.add(newService);
	}
	
	public void addService(String serviceName, double amount, String dueDate) {
		addService(serviceName, amount, dueDate, null);
	}
	
	public void addService(String serviceName, double amount, Date dueDate) {
		addService(serviceName, amount, dueDate, null);
	}

	public void removeService(int num, Scanner sc) {
		Service service = getParticularService(num);
		String serviceName = service.getServiceName();
		System.out.println("Do you really want to remove service " + serviceName + " ?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int ch = sc.nextInt();
		if(ch == 1) {
			getAllService().remove(num);
			System.out.println(serviceName + " is removed");
		}
		
	}
	
	public Service getParticularService(int num) {
		Service service = getAllService().get(num);
		//to do: add exception if accessed element out of list
		return service;
	}

	public void paymentForService(int num, int isToday, String dueDate) {
		Service service = getParticularService(num);
		if(service instanceof PaidService) {
			//to do throw error
			return;
		}
		PaidService ps = null;
		if(isToday == 1) 
			ps = new PaidService(service.getId(), service.getServiceName(), service.getAmount(), new Date());
		else if(isToday == 2) {
			try {
				ps = new PaidService(service.getId(), service.getServiceName(), service.getAmount(), dueDate);
			} catch (ParseException e) {
				System.out.println("Date format should be DD/MM/YYYY");
			}
		}
		else
			return;
		
		getAllService().remove(service);
		getAllService().add(ps);
	}
	
	public void changeToUnpaidService(int num, int isToday, String dueDate) {
		Service service = getParticularService(num);
		if(service instanceof UnPaidService) {
			//to do throw error
			return;
		}
		UnPaidService ups = null;
		if(isToday == 1) 
			ups = new UnPaidService(service.getId(), service.getServiceName(), service.getAmount(), new Date());
		else if(isToday == 2) {
			try {
				ups = new UnPaidService(service.getId(), service.getServiceName(), service.getAmount(), dueDate);
			} catch (ParseException e) {
				System.out.println("Date format should be DD/MM/YYYY");
			}
		}
		else
			return;
		
		getAllService().remove(service);
		getAllService().add(ups);
	}
	
	public void changeDueDate(int num, int isToday, String dueDate) {
		Service service = getParticularService(num);
		if(service instanceof PaidService) {
			//to do throw error
			return;
		}
		UnPaidService ups = (UnPaidService) service;
		if(isToday == 1) 
			ups.setDueDate(new Date());
		else if(isToday == 2) {
			try {
				ups.setDueDateInString(dueDate);
			} catch (ParseException e) {
				System.out.println("Date format should be DD/MM/YYYY");
			}
		}
	}
	
	public void changePaidDate(int num, int isToday, String paidDate) {
		Service service = getParticularService(num);
		if(service instanceof UnPaidService) {
			//to do throw error
			return;
		}
		PaidService ps = (PaidService) service;
		if(isToday == 1) 
			ps.setPaidDate(new Date());
		else if(isToday == 2) {
			try {
				ps.setPaidDateInString(paidDate);
			} catch (ParseException e) {
				System.out.println("Date format should be DD/MM/YYYY");
			}
		}
	}
	
	public void printAllService() {
		System.out.println("Services are: ");
		for(Service s: allService) {
			System.out.println(s.getId() + ".  " + s.getServiceName() + "    " + (s.isPaid()? "Paid Amount: " : "UnPaid Amount: ") + s.getAmount());
		}
		System.out.println();
	}
	
	public void printUnpaidService() {
		System.out.println("Unpaid Services are: ");
		for(Service s: allService) {
			if(s instanceof UnPaidService) {
				UnPaidService ups = (UnPaidService) s;
				System.out.println(ups.getId() + ".  " + ups.getServiceName() + "    " + "UnPaid Amount: " + ups.getAmount() + "  Due Date: " +ups.getDueDateInString());
			}
		}
		System.out.println();
	}
	
	public void printPaidService() {
		System.out.println("Paid Services are: ");
		for(Service s: allService) {
			if(s instanceof PaidService) {
				PaidService ps = (PaidService) s;
				System.out.println(ps.getId() + ".  " + ps.getServiceName() + "    " + "Paid Amount: " + ps.getAmount() + "  Paid Date: " +ps.getPaidDate());
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