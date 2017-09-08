package com.app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.bean.EmployeeBean;
import com.app.service.LoginService;
import com.app.serviceimpl.LoginServiceImpl;

@Path("/myrest")
public class LoginAuth {

	
	@GET
	@Path("/loginAuth")
	@Produces(MediaType.APPLICATION_JSON)
	
	public EmployeeBean loginAuthentication(@QueryParam("email") String email,@QueryParam("pwd") String password) throws Exception {
	/*	System.out.println(email+""+password);*/
		
		LoginService service=new LoginServiceImpl();
		
		EmployeeBean empbeanBean=null;
		 empbeanBean=service.login(email,password);
		
		return empbeanBean;
			}
	
		
}
