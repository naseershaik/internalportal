package com.app.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.bean.EmployeeBean;
import com.app.constants.DBConstant;
import com.app.service.DBConnect;
import com.app.service.ProjectAllocationService;

public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;

	@Override
	public String addProjectDet(String projectname, String startdate,
			String enddate, String customer) {

		DBConnect db=DBConnect.getDbCon();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date stDate,endDate;
		String sdate,edate;
		String msg=null;

		try{
			stDate=(Date) dt.parse(startdate);
			endDate=(Date) dt.parse(enddate);

			sdate = df.format(stDate);
			edate=df.format(endDate);
			System.out.println("startdate---"+sdate);
			stmt=db.conn.prepareStatement(DBConstant.ADD_PROJECT_DET);
			stmt.setString(1, projectname);
			stmt.setString(2, sdate);
			stmt.setString(3, edate);
			stmt.setString(4, customer);
			int count=stmt.executeUpdate();
			if(count==1)
				msg="Project Details Sucessfully Added ";
		}
		catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<EmployeeBean> getprojNamesDao() {
		// TODO Auto-generated method stub
				List<EmployeeBean> list=new ArrayList<EmployeeBean>();
				DBConnect db=DBConnect.getDbCon();
				EmployeeBean empBean=null;
				try {
					stmt=db.conn.prepareStatement(DBConstant.Query_PROJNAMES);
					rs=stmt.executeQuery();
					while(rs.next())
					{
						empBean=new EmployeeBean();
						
						empBean.setProjID(rs.getString("project_id"));
						empBean.setProjectName(rs.getString("project_name"));
						list.add(empBean);
						System.out.println("projectName"+rs.getString("project_name"));
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return list;
	}

	@Override
	public String addProjAllocDao(String empID, String projectID,
			String startdate, String enddate) {
		// TODO Auto-generated method stub
				DBConnect db=DBConnect.getDbCon();
				SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Date stDate,endDate;
				String sdate,edate;
				String msg=null;

				try{
					stDate=(Date) dt.parse(startdate);
					endDate=(Date) dt.parse(enddate);

					sdate = df.format(stDate);
					edate=df.format(endDate);
					System.out.println("startdate---"+sdate);
					stmt=db.conn.prepareStatement(DBConstant.PROJECT_ALLOCDETAILS);
					stmt.setString(1, empID);
					stmt.setString(2, projectID);
					stmt.setString(3, sdate);
					stmt.setString(4, edate);
					int count=stmt.executeUpdate();
					if(count==1)
						msg="Allocation Details have Added ";
				}
				catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return msg;
	}

}
