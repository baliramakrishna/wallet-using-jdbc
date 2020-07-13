package com.raki.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.raki.bean.Account;


public class DaoClass implements DaoInterface {
    
	static Connection dbCon;
	PreparedStatement pstmt;
	
	public boolean validate(String username, String password) {
		// TODO Auto-generated method stub
		String qry="select * from users where username=? AND password=?";
		try {
			
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			int i=0;
			while(rs.next())
				i++;
			
			if(i==0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return false;
	}

	public double getbalance(Account a) {
		// TODO Auto-generated method stub
		
		int acno=a.getAcno();
		String qry="select * from users where acno=?";
		try {
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setInt(1,a.getAcno());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			return rs.getDouble("balance");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public boolean deposit(Account a, double bal) {
		// TODO Auto-generated method stub
		
		double balance=getbalance(a)+bal;
		String qry="update users set balance=?";
		try {
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setDouble(1,balance);
			if(pstmt.executeUpdate() > 0) {
				String transaction=transaction(a)+"deposited "+bal;
				updateTransaction(a,transaction);
				System.out.println("deposited..."+bal);
				return true;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	public boolean withdraw(Account a, double bal) {
		// TODO Auto-generated method stub
		double balance=getbalance(a)-bal;
		
		if(balance<=500)
			return false;
		else {
			
			String qry="update users set balance=?";
			try {
				pstmt=dbCon.prepareStatement(qry);
				pstmt.setDouble(1,balance);
				if(pstmt.executeUpdate() > 0) {
					String transaction=transaction(a)+"withdrawn "+bal;
					updateTransaction(a,transaction);
					System.out.println("withdrawn succesfully...");
				}else
					System.out.println("not withdrawn");
				
				
				
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return false;
		}
		
		
	}

	public String transaction(Account a) {
		// TODO Auto-generated method stub
		
		int acno=a.getAcno();
		String qry="select * from users where acno=?";
		try {
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setInt(1,a.getAcno());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			return rs.getString("transaction");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       return null;
	}

	public Account getUser(String username, String password) {
		// TODO Auto-generated method stub
		String qry="select * from users where username=? AND password=?";
		try {
			
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			Account a=new Account();
			
			while(rs.next()) {
			a.setAcno(rs.getInt("acno"));
            a.setBalance(rs.getDouble("balance"));
			}
            
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println(e);
		}
		
		return null;
	}

	public void createAccount(String name, String username, String password) {
		// TODO Auto-generated method stub
		
		String qry="insert into users (name,username,password) values(?,?,?)";
		try {
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setString(1,name);
			pstmt.setString(2,username);
			pstmt.setString(3,password);
			if( pstmt.executeUpdate()>0)
				System.out.println("succesfully created");
		   	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println(e);
		}
		
          
	}
	
	public void updateTransaction(Account a,String transaction) {
		
		String qry="update users set transaction=?";
		try {
			pstmt=dbCon.prepareStatement(qry);
			pstmt.setString(1,transaction);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void connection() {
		try {
			//Load the Driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			
	//Try establishing the connection with the database
			 
			 
			 
			dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/socgen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load the driver..." + e);
		} catch (SQLException e) {
			System.out.println("Unable to establish connection" + e);
		}
	}

}
