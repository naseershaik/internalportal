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
import com.app.service.TrainingPlanService;

public class TrainingPlanServiceImpl implements TrainingPlanService {
	
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;

	@Override
	public String trainPlanInsertDao(String tname, String courses,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		DBConnect db=DBConnect.getDbCon();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date stdate,enddate;
		String sdate,edate;
		String msg=null;

		try{
			stdate=(Date) dt.parse(startDate);
			enddate=(Date) dt.parse(endDate);

			sdate = df.format(stdate);
			edate=df.format(enddate);
			System.out.println("startdate---"+sdate);

			stmt=db.conn.prepareStatement(DBConstant.TRAININGPLAN_INSERT);
			stmt.setString(1, tname);
			stmt.setString(2, courses);
			stmt.setString(3, sdate);
			stmt.setString(4, edate);
			int count=stmt.executeUpdate();
			if(count==1)
				msg="Training Plan Sucessfully Inserted ";
		}
		catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<EmployeeBean> updateTPlanGetDao() {
		// TODO Auto-generated method stub
				List<EmployeeBean> utpList=new ArrayList<EmployeeBean>();
				DBConnect db=DBConnect.getDbCon();
				EmployeeBean empBean=null;
				try {
					stmt=db.conn.prepareStatement(DBConstant.TRAININGPLAN_GET);
					rs=stmt.executeQuery();
					while(rs.next())
					{
						empBean=new EmployeeBean();
						empBean.setTrainingID(rs.getString("training_id"));
						empBean.setTrainingName(rs.getString("training_name"));
						empBean.setCourses(rs.getString("courses_offered"));
						empBean.setStartDate(rs.getString("start_date"));
						empBean.setEndDate(rs.getString("end_date"));
						utpList.add(empBean);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return utpList;
	}

	@Override
	public String traingPlanrowUpdateDao(String tid, String tname,
			String courses, String startDate, String endDate) {
		int trainingID=Integer.parseInt(tid);
		DBConnect db=DBConnect.getDbCon();
		String msg=null;
		try{	
			stmt=db.conn.prepareStatement(DBConstant.TRAININGPLAN_ROWUPDATE);

			stmt.setString(1, tname);
			stmt.setString(2, courses);
			stmt.setString(3, startDate);
			stmt.setString(4, endDate);
			stmt.setInt(5, trainingID);
			int count=stmt.executeUpdate();
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
	public String traingPlanrowDeleteDao(String tid) {
		// TODO Auto-generated method stub
				int trainingID=Integer.parseInt(tid);
				DBConnect db=DBConnect.getDbCon();
				String msg=null;
				try{	
					stmt=db.conn.prepareStatement(DBConstant.TRAININGPLAN_ROWDELETE);

					stmt.setInt(1, trainingID);
					int count=stmt.executeUpdate();
					if(count==1)
						msg="Data has been successfully Deleted";
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return msg;
		
	}

}
