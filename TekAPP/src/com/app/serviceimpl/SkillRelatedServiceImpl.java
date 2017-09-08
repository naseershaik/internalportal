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
import com.app.service.SkillRelatedService;

public class SkillRelatedServiceImpl implements SkillRelatedService {
	
	private Connection conn;
	private PreparedStatement stmt;
	public ResultSet rs;

	@Override
	public List<EmployeeBean> getSkillMatrixDetails() {
		System.out.println("inside DAO Class");
		List<EmployeeBean> list=new ArrayList<EmployeeBean>();
		DBConnect db=DBConnect.getDbCon();
		EmployeeBean empBean=null;
		try {
			stmt=db.conn.prepareStatement(DBConstant.Query_SKILLMATRIX);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				empBean=new EmployeeBean();
				empBean.setEmpID(rs.getString("emp_id"));
				empBean.setEmpFirstName(rs.getString("first_name"));
				empBean.setEmpLastName(rs.getString("last_name"));
				empBean.setEmail(rs.getString("mail_id"));
				empBean.setTotalExperience(rs.getString("total_past_exp"));
				empBean.setSkillName(rs.getString("skill_name"));
				empBean.setSecondaySkills(rs.getString("Other_Skills"));
				empBean.setSkillID(rs.getString("emp_skill_id"));
				empBean.setPrimarySkillID(rs.getString("primary_skill_id"));
				
				list.add(empBean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		
	}

	@Override
	public String skillMatrixRowUpdateDao(String empID, String skilID,
			String secondaySkills) {
		int empId=Integer.parseInt(empID);
		int skillID=Integer.parseInt(skilID);
		DBConnect db=DBConnect.getDbCon();
		String msg=null;
		try{	
			stmt=db.conn.prepareStatement(DBConstant.SKILLMATRIX_ROWUPDATE);
			//UPDATE trainings_offered SET training_name=?,courses_offered=?,start_date=?,end_date=?,modified_date=CURDATE() WHERE training_id=?";
			stmt.setString(1, secondaySkills);
			stmt.setInt(2, skillID);
			stmt.setInt(3, empId);
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
	public String skillMatrixDeleteDao(String skillID, String empID) {
		// TODO Auto-generated method stub
				int empId=Integer.parseInt(empID);
				int skilID=Integer.parseInt(skillID);
				DBConnect db=DBConnect.getDbCon();
				String msg=null;
				try{	
					stmt=db.conn.prepareStatement(DBConstant.SKILLMATRIX_ROWDELETE);

					System.out.println(skilID+"--"+empId);
					stmt.setInt(1,skilID);
					stmt.setInt(2,empId);
					int count=stmt.executeUpdate();
					System.out.println("count---"+count);
					if(count==1)
						msg="Data has successfully Deleted";
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("exceptions--"+e.toString());
				}
				return msg;
	}

}
