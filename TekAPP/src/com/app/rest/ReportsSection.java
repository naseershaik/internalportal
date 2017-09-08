package com.app.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.app.bean.EmployeeExcelBean;
import com.app.service.ReportsSectionService;
import com.app.serviceimpl.ReportsSectionServiceImpl;

@Path("/myrest")
public class ReportsSection {
	
	public ReportsSectionService service=new ReportsSectionServiceImpl();
	
	@GET
	@Path("/MATCExcelReport")
	public List<EmployeeExcelBean> generateMATCExcelReport()throws Exception
	{
	
		List<EmployeeExcelBean> MATClist=service.getMATCExcelReport();
		System.out.println("list values"+MATClist);
		//excelRepo exlObj = new excelRepo();
		//excelRepo.generateMATCExcel();
		return MATClist;
	}

}
