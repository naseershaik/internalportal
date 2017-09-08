package com.app.service;

import java.util.List;

import com.app.bean.EmployeeBean;

public interface TrainingPlanService {
	
	String trainPlanInsertDao(String tname,String courses,String startDate,String endDate);
	List<EmployeeBean> updateTPlanGetDao();
	String traingPlanrowUpdateDao(String tid,String tname,String courses,String startDate,String endDate);
	String traingPlanrowDeleteDao(String tid);

}
