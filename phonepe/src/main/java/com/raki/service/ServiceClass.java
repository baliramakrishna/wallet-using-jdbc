package com.raki.service;

import com.raki.bean.Account;
import com.raki.dao.DaoClass;
import com.raki.dao.DaoInterface;

public class ServiceClass implements ServiceInterface {
    
	
	DaoInterface dao=new DaoClass();
	public boolean validate(String username, String password) {
		// TODO Auto-generated method stub
		
		
		return dao.validate(username, password);
	}
	public void createAccount(String name,String username,String password) {
		
		dao.createAccount(name, username, password);
		
	}

	public double getbalance(Account a) {
		// TODO Auto-generated method stub
		return dao.getbalance(a);
	}

	public boolean deposit(Account a, double bal) {
		// TODO Auto-generated method stub
		return dao.deposit(a, bal);
	}

	public boolean withdraw(Account a, double bal) {
		// TODO Auto-generated method stub
		return dao.withdraw(a, bal);
	}

	public String transaction(Account a) {
		// TODO Auto-generated method stub
		return dao.transaction(a);
		

	}
	public Account getUser(String username,String password) {
		return dao.getUser(username, password);
		
		
	}
	public void connection() {
     
		dao.connection();
	}
}
