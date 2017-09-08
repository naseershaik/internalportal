package com.app.constants;

public class DBConstant {

	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_NAME = "mydb";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";
	public static final String Query_SKILLMATRIX="SELECT esd.emp_skill_id,esd.skill_desc Other_Skills,esd.Emp_id,emp.first_name,emp.last_name,emp.mail_id,emp.primary_skill_id,pss.skill_name,emp.date_of_joining,emp.total_past_exp FROM EMPLOYEE_SKILL_DETAILS esd,employee emp,primary_skill_sets pss WHERE esd.emp_id=emp.emp_id AND emp.primary_skill_id= pss.skill_id";
	public static final String TRAININGPLAN_INSERT="INSERT INTO trainings_offered(training_name,courses_offered,start_date,end_date) VALUES(?,?,?,?)";
	public static final String TRAININGPLAN_GET="SELECT training_id,training_name,courses_offered,start_date,end_date FROM trainings_offered";
	public static final String TRAININGPLAN_ROWUPDATE="UPDATE trainings_offered SET training_name=?,courses_offered=?,start_date=?,end_date=?,modified_date=CURDATE() WHERE training_id=?";
	public static final String TRAININGPLAN_ROWDELETE="DELETE FROM trainings_offered WHERE training_id=?";
	public static final String EMP_BENCH_REPORT = "SELECT emp.Emp_id,emp.first_name,emp.last_name,emp.total_past_exp,pss.skill_name,esd.skill_desc,emp.phone_no,emp.mail_id FROM employee emp LEFT JOIN allocation_dtls ON emp.emp_id=allocation_dtls.emp_id LEFT JOIN EMPLOYEE_SKILL_DETAILS esd ON emp.Emp_id=esd.EMP_ID,primary_skill_sets pss WHERE allocation_dtls.allocation_id IS  NULL AND emp.primary_skill_id = pss.skill_id";
	public static final String EMP_PROJ_ALLOCATION_DET = "SELECT emp.Emp_id,emp.first_name,emp.last_name,emp.total_past_exp,pss.skill_name,proj.project_name,proj.customer,allocation_dtls.allocation_id,allocation_dtls.start_date,allocation_dtls.end_date FROM employee emp LEFT JOIN allocation_dtls ON emp.emp_id=allocation_dtls.emp_id, primary_skill_sets pss,projects proj WHERE allocation_dtls.allocation_id IS  NOT NULL AND emp.primary_skill_id = pss.skill_id AND allocation_dtls.project_id = proj.project_id";
	public static final String UPDATE_ALLOCATION_DET = "UPDATE allocation_dtls SET start_date=?,end_date=? WHERE allocation_id =?";
	public static final String ADD_PROJECT_DET = "INSERT INTO projects(project_name,prj_start_date,prj_end_date,customer)VALUES(?,?,?,?)";
	public static final String MATX_EXCEL_REPORT = "SELECT emp.emp_id AS EmpId, CONCAT(emp.first_name,' ' ,emp.last_name) AS EmpName,emp.designation,emp.employee_type,emp.mail_id,emp.psft_id,emp.date_of_joining, ROUND(DATEDIFF(CURDATE(),emp.date_of_joining)/365,1) TEKExperience,emp.total_past_exp,(emp.total_past_exp+ROUND(DATEDIFF(CURDATE(),emp.date_of_joining)/365,1)) TotalExperience,'Project' AS Family,'Amulya' AS Manager,'' AS ReportingManager,'' AS Level1Manager,prjs.project_name,IF((SELECT COUNT(*) FROM allocation_dtls WHERE emp_id = emp.emp_id)=1,'yes','no') AS Billable,emp.comments,'' RequirementMapping,dpt.department_name AS Organization,pss.skill_name PrimarySkill,emp.core_or_strategic,'' AS Cross_training FROM employee emp LEFT OUTER JOIN ALLOCATION_DTLS allDtls ON emp.emp_id=allDtls.emp_id LEFT JOIN projects prjs ON allDtls.project_id=prjs.project_id INNER JOIN  department dpt ON emp.department_id=dpt.department_id INNER JOIN primary_skill_sets pss ON pss.skill_id=emp.primary_skill_id";
	public static final String LOGIN_AUTHENTICATION="SELECT emp.emp_id,emp.first_name,roles.role_name,emp.mail_id,roles.role_id FROM employee emp,roles,login_credentials lc WHERE emp.emp_role_id=roles.role_id AND emp.mail_id=lc.user_name AND lc.user_name=? AND lc.login_password= ?";
	public static final String Query_EMPLOYEDETAILS="SELECT emp_id,first_name,last_name FROM employee";
	public static final String Query_PROJNAMES="SELECT project_id,project_name FROM projects";
	public static final String PROJECT_ALLOCDETAILS="INSERT INTO allocation_dtls(emp_id,project_id,start_date,end_date,create_date) VALUES(?,?,?,?,CURDATE())";
	public static final String SKILLMATRIX_ROWUPDATE="UPDATE employee_skill_details SET skill_desc=?,modified_date=CURDATE() WHERE emp_skill_id=? AND emp_id=?";
	public static final String SKILLMATRIX_ROWDELETE="DELETE FROM EMPLOYEE_SKILL_DETAILS WHERE emp_skill_id=? AND emp_id=?";
	public static final String EMPADDINTEGRATION="INSERT INTO EMP_ADD_INTEGRATION(EMP_ID,EMP_EMAIL,EMP_TYPE) VALUES(?,?,?)";
	public static final String EMPLOYEE_ACCESS="SELECT COUNT(*) As COUNT,EMP_TYPE FROM EMP_ADD_INTEGRATION WHERE EMP_EMAIL=?";
}
