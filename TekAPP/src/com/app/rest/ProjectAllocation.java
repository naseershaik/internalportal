package com.app.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.app.bean.EmployeeBean;
import com.app.service.ProjectAllocationService;
import com.app.serviceimpl.ProjectAllocationServiceImpl;


@Path("/myrest")
public class ProjectAllocation {
	
	public ProjectAllocationService service=new ProjectAllocationServiceImpl();
	
	@POST
	@Path("/addProjectDet")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String addProjectDetails(@FormParam("projectname") String projectname,@FormParam("startdate") String startdate,
            @FormParam("enddate") String enddate,@FormParam("customer") String customer) throws Exception {
		System.out.println(projectname+""+startdate+""+enddate+""+customer);
		
		String msg=service.addProjectDet(projectname,startdate,enddate,customer);
			System.out.println("msg"+msg);
		return msg;
			}
	
	@GET
	@Path("/getprojNames")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	public List<EmployeeBean> getprojNames() throws Exception {
		System.out.println("get emp Details");
		List<EmployeeBean> list=service.getprojNamesDao();
			System.out.println("list values"+list);
		return list;
			}
	
	@POST
	@Path("/projectAllocationAdd")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String addProjAlloc(@FormParam("empID") String empID,@FormParam("projectID") String projectID,
            @FormParam("startdate") String startDate,@FormParam("enddate") String endDate) throws Exception {
		System.out.println(empID+""+startDate+""+startDate+""+projectID);
		
		String msg=service.addProjAllocDao(empID,projectID,startDate,endDate);
			System.out.println("msg"+msg);
		return msg;
			}


}
