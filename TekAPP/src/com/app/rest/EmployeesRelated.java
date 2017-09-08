package com.app.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.app.bean.EmployeeBean;
import com.app.service.EmployeesRelatedService;
import com.app.serviceimpl.EmployeesRelatedServiceImpl;

@Path("/myrest")
public class EmployeesRelated {
	
	public EmployeesRelatedService service=new EmployeesRelatedServiceImpl();
	
	@GET
	@Path("/getEmployeNames")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	public List<EmployeeBean> getEmployeNames() throws Exception {
		System.out.println("get emp Details");
		List<EmployeeBean> list=service.getEmpNamesDao();
			System.out.println("list values"+list);
		return list;
			}
	
	@GET
	@Path("/BenchReport")
	public List<EmployeeBean> empBenchReport()throws Exception
	{
		List<EmployeeBean> list=service.getBenchReport();
		System.out.println("list values"+list);
		return list;
	}
	

	@PUT
	@Path("/updateAllocationDet")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String updateAllocationDetails(@FormParam("allocID") String allocID,@FormParam("startDate") String startDate,
            @FormParam("endDate") String endDate) throws Exception {
		System.out.println(allocID+""+startDate+""+endDate);
		
		String msg=service.updateAllocationDet(allocID,startDate,endDate);
			System.out.println("msg"+msg);
		return msg;
			}
	
	@GET
	@Path("/empAlloDetails")
	public List<EmployeeBean> empAllocationReport()throws Exception
	{
		
		List<EmployeeBean> list=service.empAllocationReport();
		System.out.println("list values"+list);
		return list;
	}
	
	
	@POST
	@Path("/addEmpIntegration")
	public String addEmpAccess(@FormParam("empID") String empID,@FormParam("empEmail") String empEmail)throws Exception
	{
		
		System.out.println(empID+"-------"+empEmail);
	
		String msg=service.addEmpAccesServiceImpl(empID,empEmail);
		System.out.println("msg"+msg);
		return msg;
	}



}
