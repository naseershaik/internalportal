package com.app.service;

import java.util.List;

import com.app.bean.EmployeeBean;

public interface EmployeesRelatedService {
	List<EmployeeBean> getEmpNamesDao();
	List<EmployeeBean> getBenchReport();	
	String updateAllocationDet(String allocID,String startDate,String endDate);
	List<EmployeeBean> empAllocationReport();
	String addEmpAccesServiceImpl(String empID, String empEmail);
	

}
