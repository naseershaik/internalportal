package com.app.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.app.bean.EmployeeBean;
import com.app.service.TrainingPlanService;
import com.app.serviceimpl.TrainingPlanServiceImpl;

@Path("/myrest")
public class TrainingPlan {
	
	public TrainingPlanService service=new TrainingPlanServiceImpl();
	
	@POST
	@Path("/trngPlanAdd")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public String trngPlanInsert(@FormParam("trainingname") String trainingName,
            @FormParam("courses") String courses,
            @FormParam("startdate") String startDate,@FormParam("enddate") String endDate) throws Exception {
		
		String result=service.trainPlanInsertDao(trainingName, courses, startDate, endDate);
		System.out.println("result---------"+result);
		return result;
			}
	

	@GET
	@Path("/updateTPlan")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public List<EmployeeBean> updateTPlan() throws Exception {
		
		List<EmployeeBean> list=service.updateTPlanGetDao();
			System.out.println("list values"+list);
		return list;
			}


	@PUT
	@Path("/updateTPlanRow")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String updateTPlanRow(@FormParam("trainingid") String trainingID,@FormParam("trainingname") String trainingName,
            @FormParam("courses") String courses,
            @FormParam("startdate") String startDate,@FormParam("enddate") String endDate) throws Exception {
		System.out.println(trainingID+""+trainingName+""+courses+""+startDate+""+endDate);
		
		String msg=service.traingPlanrowUpdateDao(trainingID,trainingName,courses,startDate,endDate);
			System.out.println("msg"+msg);
		return msg;
			}

	@DELETE
	@Path("/deleteTPlanRow")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String deleteTPlanRow(@FormParam("trainingid") String trainingID) throws Exception {
		System.out.println(trainingID);
		
		String msg=service.traingPlanrowDeleteDao(trainingID);
			System.out.println("msg"+msg);
		return msg;
			}


}
