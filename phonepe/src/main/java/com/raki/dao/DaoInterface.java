package com.raki.dao;

import com.raki.bean.Account;

public interface DaoInterface {
   
	boolean validate(String username,String password);
	double getbalance(Account a);
	boolean deposit(Account a,double bal);
	boolean withdraw(Account a,double bal);
	String transaction(Account a);
	Account getUser(String username,String password);
	void createAccount(String name,String username,String password);
	void connection();
	
}
