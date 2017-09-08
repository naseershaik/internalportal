package com.app.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.bean.EmployeeBean;
import com.app.constants.DBConstant;
import com.app.service.DBConnect;
import com.app.service.EmployeesRelatedService;

public class EmployeesRelatedServiceImpl implements EmployeesRelatedService {
	
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;

	@Override
	public List<EmployeeBean> getEmpNamesDao() {
		List<EmployeeBean> list=new ArrayList<EmployeeBean>();
		DBConnect db=DBConnect.getDbCon();
		EmployeeBean empBean=null;
		try {
			stmt=db.conn.prepareStatement(DBConstant.Query_EMPLOYEDETAILS);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				empBean=new EmployeeBean();
				empBean.setEmpID(rs.getString("emp_id"));
				String fname=rs.getString("first_name");
				String lname=rs.getString("last_name");
				String fullname=fname+" "+lname;
				empBean.setEmpName(fullname);
				list.add(empBean);
				System.out.println("fullname"+fullname);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<EmployeeBean> getBenchReport() {
		List<EmployeeBean> empBenchList=new ArrayList<EmployeeBean>();
		DBConnect db=DBConnect.getDbCon();
		EmployeeBean empBean=null;
		try {
			stmt=db.conn.prepareStatement(DBConstant.EMP_BENCH_REPORT);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				empBean=new EmployeeBean();
				empBean.setEmpID(rs.getString("Emp_id"));
				empBean.setEmpFirstName(rs.getString("first_name"));
				empBean.setEmpLastName(rs.getString("last_name"));
				empBean.setTotalExperience(rs.getString("total_past_exp"));
				empBean.setPrimarySkills(rs.getString("skill_name"));
				empBean.setSecondaySkills(rs.getString("skill_desc"));
				empBean.setMobileNo(rs.getString("phone_no"));
				empBean.setEmail(rs.getString("mail_id"));
				empBenchList.add(empBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empBenchList;

	}

	@Override
	public String updateAllocationDet(String allocID, String startDate,
			String endDate) {
		int allocId=Integer.parseInt(allocID);
		DBConnect db=DBConnect.getDbCon();
		String msg=null;
		try{	
			stmt=db.conn.prepareStatement(DBConstant.UPDATE_ALLOCATION_DET);

			stmt.setString(1, startDate);
			stmt.setString(2, endDate);
			stmt.setInt(3, allocId);
			int count=stmt.executeUpdate();
			System.out.println(count);
			if(count==1)
				msg="Data has been successfully updated";
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<EmployeeBean> empAllocationReport() {

		List<EmployeeBean> empAllocationDetList=new ArrayList<EmployeeBean>();
		DBConnect db=DBConnect.getDbCon();
		EmployeeBean empBean=null;
		try {
			stmt=db.conn.prepareStatement(DBConstant.EMP_PROJ_ALLOCATION_DET);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				empBean=new EmployeeBean();
				empBean.setEmpID(rs.getString("Emp_id"));
				empBean.setEmpFirstName(rs.getString("first_name"));
				empBean.setEmpLastName(rs.getString("last_name"));
				empBean.setTotalExperience(rs.getString("total_past_exp"));
				empBean.setPrimarySkills(rs.getString("skill_name"));
				empBean.setProjectName(rs.getString("project_name"));
				empBean.setCustomer(rs.getString("customer"));
				empBean.setStartDate(rs.getString("start_date"));
				empBean.setEndDate(rs.getString("end_date"));
				empBean.setAllocationID(rs.getString("allocation_id"));
				empAllocationDetList.add(empBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empAllocationDetList;
	}

	@Override
	public String addEmpAccesServiceImpl(String empID,String empEmail) {
		// TODO Auto-generated method stub
		DBConnect db=DBConnect.getDbCon();
		EmployeeBean empBean=null;
		String msg=null;
		
		try {
			stmt=db.conn.prepareStatement(DBConstant.EMPADDINTEGRATION);
			int empid=Integer.parseInt(empID);
			
			stmt.setInt(1, empid);
			stmt.setString(2, empEmail);
			stmt.setString(3, "USER");
			int count=stmt.executeUpdate();
			
			if(count==1)
				msg="Employee have been successfully Added ";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msg;
	}

}
