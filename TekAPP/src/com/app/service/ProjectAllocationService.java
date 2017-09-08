package com.app.service;

import java.util.List;

import com.app.bean.EmployeeBean;

public interface ProjectAllocationService {
	String addProjectDet(String projectname,String startdate,String enddate,String customer);
	List<EmployeeBean> getprojNamesDao();
	String addProjAllocDao(String empID,String projectID,String startdate,String enddate);

}
