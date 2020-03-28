package project.user;

import java.util.Date;
import java.util.Scanner;

import com.uninterruptedhome.impl.ServiceImpl;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ServiceImpl services = ServiceImpl.getServices();
		services.addService("Electricity", 1500, "20/03/2020");
		services.addService("DTH", 450, "25/03/2020");
		services.addService("Mobile", 555, new Date());
		printMenu(sc, services);

	}

	public static void printMenu(Scanner sc, ServiceImpl services) {
		int input;
		do {
			System.out.println("---------Welcome to Uninterrupted Home---------");
			System.out.println("1. Show All Services");
			System.out.println("2. Show Paid Services");
			System.out.println("3. Show Unpaid Services");
			System.out.println("4. Add New Service");
			System.out.println("5. Remove a Service");
			System.out.println("6. Pay a Service");
			System.out.println("7. Change due date of a Service");
			System.out.println("8. Change paid date of a Service");
			System.out.println("9. Accidentally Paid service? Change to Unpaid service");
			System.out.println("10. Exit");
			System.out.print("Please select a menu to proceed: ");
			input = sc.nextInt();
			System.out.println();
			int choice;
			switch(input) {
			case 1:
				services.printAllService();
				break;
			case 2:
				services.printPaidService();
				break;
			case 3:
				services.printUnpaidService();
				break;
			case 4:
				System.out.println("Enter Service Name: ");
				String sname = sc.next();
				System.out.println("Enter Amount: ");
				double amt = sc.nextDouble();
				services.addService(sname, amt, new Date());
				System.out.println("Service added successfully!");
				break;
			case 5:
				services.printUnpaidService();
				System.out.println("0: Exit ");
				System.out.println("Select any service to remvove: ");
				choice = sc.nextInt();
				if(choice != 0) {
					services.removeService(choice, sc);
				}
				break;
			case 6:
				services.printUnpaidService();
				System.out.println("0: Exit ");
				System.out.println("Select any service to pay: ");
				choice = sc.nextInt();
				if(choice != 0) {
					System.out.println("Did you paid today? ");
					System.out.println("1. Yes");	
					System.out.println("2. No");
					int isToday = sc.nextInt();
					String date = null;
					if(isToday == 2) {
						System.out.println("Please Enter Date in DD/MM/YYYY format: ");
						date = sc.next();
					}
					services.paymentForService(choice, isToday, date);
					System.out.println("Paid successfully!");
				}
				break;
			case 7:
				services.printUnpaidService();
				System.out.println("0: Exit ");
				System.out.println("Select any service to change due date: ");
				choice = sc.nextInt();
				if(choice != 0) {
					System.out.println("is Due date today? ");
					System.out.println("1. Yes");	
					System.out.println("2. No");
					int isToday = sc.nextInt();
					String date = null;
					if(isToday == 2) {
						System.out.println("Please Enter Date in DD/MM/YYYY format: ");
						date = sc.next();
					}
					services.changeDueDate(choice, isToday, date);
					System.out.println("Date Changed successfully!");
				}
				break;
			case 8:
				services.printPaidService();
				System.out.println("0: Exit ");
				System.out.println("Select any service to change due date: ");
				choice = sc.nextInt();
				if(choice != 0) {
					System.out.println("Did you paid today? ");
					System.out.println("1. Yes");	
					System.out.println("2. No");
					int isToday = sc.nextInt();
					String date = null;
					if(isToday == 2) {
						System.out.println("Please Enter Date in DD/MM/YYYY format: ");
						date = sc.next();
					}
					services.changePaidDate(choice, isToday, date);
					System.out.println("Date Changed successfully!");
				}
				break;
			case 9:
				services.printPaidService();
				System.out.println("0: Exit ");
				System.out.println("Select any service to change due date: ");
				choice = sc.nextInt();
				if(choice != 0) {
					System.out.println("Due date set to default today's date. Do you want to change due date?");
					System.out.println("1. Yes");	
					System.out.println("2. No");
					int isToday = sc.nextInt();
					String date = null;
					if(isToday == 2) {
						System.out.println("Please Enter Date in DD/MM/YYYY format: ");
						date = sc.next();
					}
					services.changePaidDate(choice, isToday, date);
					System.out.println("Service is now unpaid. Please pay at earliest!");
				}
				break;
			case 10:
				break;
			default:
				System.out.println("Please select a valid number");
				break;
			}
		}while(input != 10);
	}
}
