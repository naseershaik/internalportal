
package com.app.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.app.bean.EmployeeBean;
import com.app.service.SkillRelatedService;
import com.app.serviceimpl.SkillRelatedServiceImpl;



@Path("/myrest")
public class SkillRelated {
	
	public SkillRelatedService service=new SkillRelatedServiceImpl();
	
	@GET
	@Path("/skillMatrix")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public List<EmployeeBean> getSkillDetails() throws Exception {
		
		List<EmployeeBean> list=service.getSkillMatrixDetails();
			System.out.println("list values"+list);
		return list;
			}
	
	@PUT
	@Path("/updateSkillMatix")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String updateSkillMatrixRow(@FormParam("empID") String empID,@FormParam("skilID") String skilID,@FormParam("secondaySkills") String secondaySkills) throws Exception {
		System.out.println(empID+" "+skilID+""+secondaySkills);
		
		String msg=service.skillMatrixRowUpdateDao(empID,skilID,secondaySkills);
			System.out.println("msg"+msg);
		return msg;
			}
	

	@DELETE
	@Path("/deleteSkillMatrixRow")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public String deleteSkillMatrixRow(@FormParam("empID") String empID,@FormParam("skillID") String skillID) throws Exception {
		System.out.println(skillID+"----"+empID);
		
		String msg=service.skillMatrixDeleteDao(skillID,empID);
			System.out.println("msg"+msg);
		return msg;
			}


}
