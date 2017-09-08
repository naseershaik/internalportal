package com.app.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.bean.EmployeeExcelBean;
import com.app.constants.DBConstant;
import com.app.service.DBConnect;
import com.app.service.ReportsSectionService;

public class ReportsSectionServiceImpl implements ReportsSectionService {
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;


	@Override
	public List<EmployeeExcelBean> getMATCExcelReport() {
		
		List<EmployeeExcelBean> MATCExcelList=new ArrayList<EmployeeExcelBean>();
		DBConnect db=DBConnect.getDbCon();
		EmployeeExcelBean empExcelBean=null;
		try {
			stmt=db.conn.prepareStatement(DBConstant.MATX_EXCEL_REPORT);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				empExcelBean=new EmployeeExcelBean();
				empExcelBean.setEmpId(rs.getString("EmpId"));
				empExcelBean.setEmpName(rs.getString("EmpName"));
				empExcelBean.setDesignation(rs.getString("designation"));
				empExcelBean.setEmployeeType(rs.getString("employee_type"));
				empExcelBean.setEmail(rs.getString("mail_id"));
				empExcelBean.setPsft_id(rs.getString("psft_id"));
				empExcelBean.setJoiningDt(rs.getString("date_of_joining"));
				empExcelBean.setTekExp(rs.getString("TEKExperience"));
				empExcelBean.setTotalPastExp(rs.getString("total_past_exp")); 
				empExcelBean.setTotalExperience(rs.getString("TotalExperience"));
				empExcelBean.setFamily(rs.getString("Family"));
				empExcelBean.setManager(rs.getString("Manager"));
				empExcelBean.setReportingManager(rs.getString("ReportingManager"));
				empExcelBean.setLevel1Manger(rs.getString("Level1Manager"));
				empExcelBean.setProjectName(rs.getString("project_name"));
				empExcelBean.setBillable(rs.getString("Billable"));
				empExcelBean.setComments(rs.getString("comments"));
				empExcelBean.setRequirementMapping(rs.getString("RequirementMapping"));
				empExcelBean.setOrganization(rs.getString("Organization"));
				empExcelBean.setPrimarySkills(rs.getString("PrimarySkill"));
				empExcelBean.setCoreStrategy(rs.getString("core_or_strategic"));
				empExcelBean.setCrossTraining(rs.getString("Cross_training"));
				MATCExcelList.add(empExcelBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return MATCExcelList;
	}

}
