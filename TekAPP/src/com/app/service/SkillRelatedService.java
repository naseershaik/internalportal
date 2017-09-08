package com.app.service;

import java.util.List;

import com.app.bean.EmployeeBean;

public interface SkillRelatedService {

	List<EmployeeBean> getSkillMatrixDetails();
	String skillMatrixRowUpdateDao(String empID,String skilID,String secondaySkills);
	String skillMatrixDeleteDao(String skilID,String empID);

}
