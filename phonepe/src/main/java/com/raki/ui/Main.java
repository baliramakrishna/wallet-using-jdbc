package com.raki.ui;

import java.util.Scanner;

import com.raki.bean.Account;
import com.raki.service.ServiceClass;
import com.raki.service.ServiceInterface;

public class Main {
  
	static Scanner scan=new Scanner(System.in);
	static ServiceInterface service=new ServiceClass();
	public static void main(String[] args) {
		
		
			
	
			System.out.println("select one option\n 1.Already user \n 2. create Account");
			int option=scan.nextInt();
			
			
			if(option==1) {
				
				
				service.connection();
				
				System.out.println("Enter username\n");
				scan.nextLine();
				String username=scan.nextLine();
				System.out.println("Enter Password\n");
				String password=scan.nextLine();
				
				if(service.validate(username, password)) {
				
				Account a=service.getUser(username, password);
				
				while(true) {
				System.out.println("Enter your option \n 1.check balance \n 2.deposit \n 3.withdraw \n 4.mini transcitions\n 5.exit");
				int selection=scan.nextInt();
				
				
				if(selection==1)
				System.out.println(service.getbalance(a));
				
				else if(selection==2){
		
				System.out.println("Enter Amount\n");
				double amount=scan.nextInt();
				
				if(service.deposit(a, amount))
					System.out.println("deposited successfully;\n");
				else
					System.out.println("not deposited \n");
				
				}
				
				else if(selection==3) {
					
				   System.out.println("Enter Amount\n");
				   double amount=scan.nextInt();
				   if(service.withdraw(a, amount))
					   System.out.println("withdrawn successfully\n");
				   else
					   System.out.println("low balance\n");
					
				  }
				
				else if(selection==4)
				  System.out.println(service.transaction(a));
				else
					break;
					
			   }
				
				}
				
			
		     else 
			    System.out.println("wrong details\n");
				
			}
			
			else  {
				service.connection();
				createAccount();
			}
			

	}
	
	public  static void createAccount() {
		
		System.out.println("Enter Name");
		scan.nextLine();
		String name=scan.nextLine();
		
		System.out.println("Enter userName");
		String username=scan.nextLine();
		System.out.println("Enter password");
		String password=scan.nextLine();
		
		service.createAccount(name, username, password);
		
		
		
	}

}
	

