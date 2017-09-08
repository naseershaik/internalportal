package com.app.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.bean.EmployeeBean;
import com.app.constants.DBConstant;
import com.app.ldap.LdapAuthentication;
import com.app.service.DBConnect;
import com.app.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;


	@Override
	public EmployeeBean login(String userName, String pwd) {

		String page=null;
		String empName=null;
		int key=0;
		String type=null;
		String keyMsg=null;
		EmployeeBean empBean=new EmployeeBean();
		
		//Ldap Authentication
		LdapAuthentication ldapAuth=new LdapAuthentication();
		String response=ldapAuth.ldapLoginAuth(userName, pwd);
		
		DBConnect db=DBConnect.getDbCon();
		
		try
		{
			stmt=db.conn.prepareStatement(DBConstant.EMPLOYEE_ACCESS);
			stmt.setString(1, userName);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				 key=rs.getInt("COUNT");
				type=rs.getString("EMP_TYPE");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		if(response=="SUCCESS")
		{
			if(key==1)
			{
				if(type.equals("ADMIN")){
					page="ADMIN";
				}
				else{
					page="USER";
				}
			}
			
		}
		int index=userName.indexOf('@');
		empName=userName.substring(0, index);
		empBean.setEmpName(empName);
		empBean.setPage(page);
		empBean.setMsg(response);
		System.out.println("Key-----"+key);
		empBean.setKey(key);
		
		
		
		return empBean;
	}

}
