//Define an angular module for our app
var sampleApp2 = angular.module("tekapp1", ["ngRoute","ngTable","datatables","ngSanitize", "ngCsv","ui.bootstrap"]);
var loginapp=angular.module("loginapp",[]);

sampleApp2.config(function($routeProvider) {
	$routeProvider
	.when("/EmpSkillMatrix", {
		templateUrl : "./pages/skillMatrix.html",
		controller : "skillMatrixController"
	})
	.when("/trainingPlan", {
		templateUrl : "./pages/trainingPlan.html",
		controller : "trainingPController"
	})
	.when("/updateTraningPlan", {
		templateUrl : "./pages/updateTrainingplan.html",
		controller : "updateTplanController"
	})
	.when("/AddProjects", {
		templateUrl : "./pages/addProject.html",
		controller : "addProjectController"
	})
	.when("/empBenchReport", {
		templateUrl : "./pages/empBenchReport.html",
		controller : "empBenchReportController"
	})
	.when("/empProjAlloDet", {
		templateUrl : "./pages/empProjAllocationDet.html",
		controller : "empProjAllocationController"
	})
	.when("/AddEmployee", {
		templateUrl : "./pages/addEmployee.html",
		controller : "addEmployeeController"
	})


});


sampleApp2.controller('test', function($scope,$http,$rootScope) {
	 $rootScope.myValue = true;
	 $rootScope.filename = "matc";
	 
	 $http({
	  method: 'GET',
	  url: 'rest/myrest/MATCExcelReport'
	 }).success(function(data){
		 $rootScope.getArray = [];
		 angular.forEach(data, function(value){
		    // console.log(value.empId);
			 $rootScope.getArray.push({
				  EmployeeID: value.empId,
				  EmployeeName: value.empName,
				  designation: value.designation,
				  employeeType : value.employeeType,
				  mailId: value.email,
				  psftID: value.psft_id,
				  dateOfJoining: value.joiningDt,
				  TekExp: value.tekExp,
				  TotalPastExp: value.totalPastExp,
				  ToalExp: value.totalExperience,
				  Family: value.family,
				  Manager: value.manager,
				  ReportingMangr: value.reportingManager,
				  level1Mngr: value.level1Manger,
				  ProjectName: value.projectName,
				  Billable: value.billable,
				  Comments: value.comments,
				  RequirementMapping :value.requirementMapping,
				  Organization:value.organization,
				  primarySkill:value.primarySkills,
				  CoreStrategy:value.coreStrategy,
				  CrossTraining:value.crossTraining
			 })
		});
		 
	  $rootScope.excelList=data;
	 }).error(function(){
	  alert("error");
	 });

	 $rootScope.getHeader = function () {return ["Employee ID", "Employee Name","Designation","Employee Type","Mail ID","PSFTID","Date_of_joining","TEKExperieance","Total Past Experieance","Total Experieance","Family","Manager","Reporting Manager","Level1Manger","Project Name","Billable","Comments","Requirement Mapping","Organization","Primary Skills","Core or Strategic","Cross_training"]};
	});

sampleApp2.controller('MATCExcelReportController', function($scope,$rootScope,$http,NgTableParams){
	
	$rootScope.title="Employee Allocation Report";
	$rootScope.myValue = false;  
	$http({
		method : 'GET',
		url: 'rest/myrest/MATCExcelReport'
	}).success(function(data){
		$scope.allocationList = data;
	}).error(function(){
		alert("error")
	});

});


sampleApp2.controller('empProjAllocationController', function($scope,$rootScope,$http,$modal,NgTableParams){

	$(".datepicker").datepicker({
		changeMonth: true,
		changeYear: true
		/*dateFormat: "yy-mm-dd"*/
	});
	$scope.Successmsg = false;
	
	$scope.modalHide=true; 	 
	$scope.view=false;
	$rootScope.title="Employee Allocation Report";
	
	 
	
	$rootScope.myValue = false; 
	$http({
		method : 'GET',
		url: 'rest/myrest/empAlloDetails'
	}).success(function(data){
		$scope.allocationList = data;
	}).error(function(){
		alert("error")
	});
	
	
	
	$scope.editingData = {};
	for (var i = 0; i < $rootScope.count; i++) {
		$scope.editingData[$rootScope.editingData[i].empID] = false;
	}

	$scope.modify = function(Employee,index){
		$scope.Successmsg = false;
		$scope.editingData[index]=false;
		$scope.editingData[index] = true;
	};

	
	
	$scope.update = function(Employee,index){
		$scope.editingData[index] = false;
		alert(Employee.allocationID);

		$http({
			method: 'PUT',
			url: 'rest/myrest/updateAllocationDet',
			data : $.param({
				'allocID' : Employee.allocationID,
				'startDate' : Employee.startDate,
				'endDate' : Employee.endDate,
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){

			$scope.msg=data;
			$scope.Successmsg = true;
			
			if(data!==null)
			{
			$http({
				method: 'GET',
				url: 'rest/myrest/empAlloDetails'
			}).success(function(data){
				$rootScope.allocationList=data;
			})
		}
		}).error(function(){
			alert("error");
		});	


	};
	
	/*var value=document.getElementById("inputEmp").value;
	 alert("value--"+value);
	 if(value==undefined){
	 $scope.view=false;
	   }
	   else{
	   $scope.view=true;
	   }*/
	
	
	 $scope.show = function () {
		 $scope.view1=false;
		 $scope.view=true;
		 $http({
				method : 'GET',
				url: 'rest/myrest/getEmployeNames'
			}).success(function(data){
				$scope.empDetailsList = data;
			}).error(function(){
				alert("error")
			});
		 
		/* var value=document.getElementById("inputEmp").value;
			alert(value.length);
			if(value.length==0){
				$scope.view=false;
			   }
			   else{
			   $scope.view=true;
			   }*/
	 };
	 
	 $scope.showProj = function () {
		 $scope.view=false;
		 $scope.view1=true;
		 $http({
				method : 'GET',
				url: 'rest/myrest/getprojNames'
			}).success(function(data){
				$scope.projDetailsList = data;
			}).error(function(){
				alert("error")
			});
		 
		/* var value=document.getElementById("inputEmp").value;
		 if(value==" "){
		 $scope.view=false;
		   }
		   else{
		   $scope.view=true;
		   }*/
			
	 };
	 
	 
	 	$scope.display=function(emp){
		    $scope.searchText = emp.empName;
		    $scope.addEmpID=emp.empID
		    $scope.empDetailsList = {};
		  
		  };
		  
		  $scope.display1=function(emp){
			    $scope.searchProjText = emp.projectName;
			    $scope.projectID=emp.projID;
			    $scope.projDetailsList = {};
			  
			  };
			  $scope.hideSarchlist=function(){
					$scope.view=false;
			 }
			  $scope.hideProjList=function(){
					$scope.view1=false;
			 }
			   
	 
	 $http({
			method : 'GET',
			url: 'rest/myrest/empAlloDetails'
		}).success(function(data){
			$scope.empDetailsList = data;
		}).error(function(){
			alert("error")
		});
	 
	 /*Add Allocations*/
	
	 $scope.addAllocations=function(emp){
		 
		 
		 $http({
				method : 'POST',
				url : 'rest/myrest/projectAllocationAdd',
				data : $.param({
					'empID' : $scope.addEmpID,
					'projectID' : $scope.projectID,
					'startdate' : $scope.startDate,
					'enddate' :$scope.endDate
				}),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function (data) {
				$scope.result=data;
				alert($scope.result);
				$http({
					method : 'GET',
					url: 'rest/myrest/empAlloDetails'
				}).success(function(data){
					$scope.allocationList = data;
				}).error(function(){
					alert("error")
				});
				if(data!=null)
				{ 
					$scope.searchText=" ";
					$scope.searchProjText=" ";
					$scope.startDate=" ";
					$scope.endDate=" ";
					
				}
				
				
			}).error(function(status){
				alert("status..."+status);
			});
 
		 
	 }
		/*$http({
			method : 'GET',
			url: 'rest/myrest/empProjectsAlloc'
		}).success(function(data){
			$scope.empProjDetails = data;
		}).error(function(){
			alert("error")
		});*/
		


});


sampleApp2.controller('empBenchReportController', function($scope,$rootScope,$http,NgTableParams){
	$rootScope.title="Employee Bench Report";
	$rootScope.myValue = false; 
	$http({
		method : 'GET',
		url: 'rest/myrest/BenchReport'
	}).success(function(data){
		$scope.benchList = data;
	}).error(function(){
		alert("error")
	});
});

sampleApp2.controller('myctr', function($scope,$rootScope) {
	$rootScope.myValue = true;
});

sampleApp2.controller('skillMatrixController', function($scope,$rootScope,$http,NgTableParams) {
	$rootScope.title="Skill Matrix Page";
	$rootScope.myValue = false;
	$http({
		method: 'GET',
		url: 'rest/myrest/skillMatrix'
	}).success(function(data){
		$scope.list=data;
		/*$('#example').DataTable();*/
	}).error(function(){
		alert("error");
	});	
	
	$scope.editingData = {};

	for (var i = 0; i < $rootScope.count; i++) {
		$scope.editingData[$rootScope.list[i].empID] = false;
	}

	$scope.modify = function(tableData){
		/*alert("table id--"+tableData.trainingID);*/
		$scope.showMsg = false;
		$scope.editingData[tableData.empID] = true;
	};


	$scope.update = function(tableData){
		$scope.editingData[tableData.empID] = false;

		$http({
			method: 'PUT',
			url: 'rest/myrest/updateSkillMatix',
			data : $.param({
				'empID' : tableData.empID,
				'secondaySkills' :tableData.secondaySkills,
				'skilID' :tableData.skillID
				/*'skillName' :tableData.skillName,*/
				
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){

			$scope.msg=data;
			$scope.showMsg = true;
		}).error(function(){
			alert("error");
		});	


	};
	$scope.remove = function(tableData){
		/*$scope.editingData[tableData.trainingID] = false;*/
		alert(tableData.empID);
		alert(tableData.skillID);
		if (confirm("Are you sure want to delete?") == true) {
		$http({
			method: 'DELETE',
			url: 'rest/myrest/deleteSkillMatrixRow',
			data : $.param({
				'empID' : tableData.empID,
				'skillID': tableData.skillID
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){
			
			$scope.msg=data;
			$scope.showMsg = true;
				$http({
					method: 'GET',
					url: 'rest/myrest/skillMatrix'
				}).success(function(data){
					$scope.list=data;
				})
				
		}).error(function(){
			alert("error");
		});
		}


	};



});

sampleApp2.controller('trainingPController', function($scope,$rootScope,$http) {
	$rootScope.title="Training Plan Page";
	$rootScope.myValue = false;
	$(".datepicker").datepicker({
		changeMonth: true,
		changeYear: true
		/*dateFormat: "yy-mm-dd"*/
	});
	$scope.trainingPlan=function(){
		var startdate=$("#start_date").val();
		var enddate=$("#end_date").val();
		/*alert("startdate"+$scope.startDate);*/
		$http({
			method : 'POST',
			url : 'rest/myrest/trngPlanAdd',
			data : $.param({
				'trainingname' : $scope.trngName,
				'courses' : $scope.courses,
				'startdate' : startdate,
				'enddate' :enddate
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function (data) {
			$scope.result=data;
			if(data!=null)
			{
				$("#training_name").val("");
				$("#courses_").val("");
				$("#start_date").val("");
				$("#end_date").val("");
			}
		}).error(function(status){
			alert("status..."+status);
		});
	}

});



sampleApp2.controller('updateTplanController', function($scope,$rootScope,$http,NgTableParams) {
	$rootScope.title="Update Training Plan";
	$rootScope.Success_Msg = false;
	$rootScope.myValue = false;
	$http({
		method: 'GET',
		url: 'rest/myrest/updateTPlan'
	}).success(function(data){
		$rootScope.tabelsData=data;
		/*$rootScope.count=$rootScope.tabelsData.length;
		$scope.tableParams = new NgTableParams({}, { dataset: $rootScope.tabelsData});
*/	}).error(function(){
		alert("error");
	});	

	$scope.editingData = {};

	for (var i = 0; i < $rootScope.count; i++) {
		$scope.editingData[$rootScope.tabelsData[i].trainingID] = false;
	}

	$scope.modify = function(tableData){
		/*alert("table id--"+tableData.trainingID);*/
		$scope.editingData[tableData.trainingID] = true;
	};


	$scope.update = function(tableData){
		$scope.editingData[tableData.trainingID] = false;

		$http({
			method: 'PUT',
			url: 'rest/myrest/updateTPlanRow',
			data : $.param({
				'trainingid' : tableData.trainingID,
				'trainingname' : tableData.trainingName,
				'courses' : tableData.courses,
				'startdate' : tableData.startDate,
				'enddate' :tableData.endDate
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){

			$rootScope.msg=data;
			$rootScope.Success_Msg = true;
		}).error(function(){
			alert("error");
		});	


	};
	$scope.remove = function(tableData){
		/*$scope.editingData[tableData.trainingID] = false;*/
		if (confirm("Are you sure want to delete?") == true) {
		$http({
			method: 'DELETE',
			url: 'rest/myrest/deleteTPlanRow',
			data : $.param({
				'trainingid' : tableData.trainingID
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data){
			
			$rootScope.msg=data;
			$rootScope.Success_Msg = true;
			if(data!==null)
				{
				$http({
					method: 'GET',
					url: 'rest/myrest/updateTPlan'
				}).success(function(data){
					$rootScope.tabelsData=data;
				})
				
				}
		}).error(function(){
			alert("error");
		});
		}


	};

});


sampleApp2.controller('addProjectController', function($scope,$rootScope,$http) {
	$rootScope.myValue = false;
	$rootScope.title="Add Project";
	$scope.Success_Msg=false;
	$(".datepicker").datepicker({
		changeMonth: true,
		changeYear: true
		/*dateFormat: "yy-mm-dd"*/
	});
	$scope.addProject= function(){
		var startdate=$("#start_date").val();
		var enddate=$("#end_date").val();
		$http({
			method : 'POST',
			url : 'rest/myrest/addProjectDet',
			data : $.param({
				'projectname' : $scope.projName,
				'startdate' : startdate,
				'enddate' :enddate,
				'customer' : $scope.customer
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function (data) {
			$scope.Success_Msg=true;
			$scope.result=data;
			if(data!=null)
			{
				$("#projectname").val("");
				$("#customer").val("");
				$("#start_date").val("");
				$("#end_date").val("");
			}

		}).error(function(status){
			alert("status..."+status);
		});

		
	}
	$scope.trainingPlan=function(){
		var startdate=$("#start_date").val();
		var enddate=$("#end_date").val();
		alert("startdate"+$scope.startDate);
		$http({
			method : 'POST',
			url : 'rest/myrest/trngPlanAdd',
			data : $.param({
				'trainingname' : $scope.trngName,
				'courses' : $scope.courses,
				'startdate' : startdate,
				'enddate' :enddate
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function (data) {
			$scope.result=data;
			if(data!=null)
			{
				$("#training_name").val("");
				$("#courses_").val("");
				$("#start_date").val("");
				$("#end_date").val("");
			}

			alert("result---"+result)
		}).error(function(status){
			alert("status..."+status);
		});
	}

});



sampleApp2.controller('addEmployeeController', function($scope,$rootScope,$http) {
	$rootScope.myValue = false;
	$rootScope.title="Add Employee";
	$scope.Success_Msg=false;
	$scope.addEmpIntegration= function(){
		$http({
			method : 'POST',
			url : 'rest/myrest/addEmpIntegration',
			data : $.param({
				'empID' : $scope.empID,
				'empEmail' : $scope.empEmail
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function (data) {
			$scope.Success_Msg=true;
			$scope.result=data;
			if(data!=null)
			{
				$("#empID").val("");
				$("#empEmail").val("");
			}

		}).error(function(status){
			alert("status..."+status);
		});

		
	}
});




sampleApp2.controller('profileController', function($scope,$rootScope) {
	$rootScope.myValue = false;
});


sampleApp2.controller('allocationController', function($scope,$rootScope) {
	console.log("inside my controller");

	$scope.message = 'This is user My allocation page';
	$rootScope.myValue = false;
});




loginapp.controller('loginctrl', function($scope,$rootScope,$http,$window){
	
	$scope.Success_Msg=false;
	$scope.login= function(){
	$http({
		method : 'GET',
		url : 'rest/myrest/loginAuth',
		params: {email: $scope.email,pwd:$scope.pwd},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		}
	}).success(function(data){
		$scope.response = data;
		$scope.name=$scope.response.empName;
		if($scope.response.msg=="SUCCESS")
			{
			if($scope.response.key==1){
					
					if($scope.response.page=="ADMIN"){
						 window.location="../TekAPP/index.html";
						 $window.sessionStorage.setItem(name,$scope.name);
						}
					else{
						window.location="../TekAPP/userLandingPage.html";
						 $window.sessionStorage.setItem(name,$scope.name);
					}
				}
			else{
				$scope.errormsg="User does not exist in Integration database";
				$scope.Success_Msg=true;
			}
			}
		else{
			$scope.errormsg="Invalid Credentials";
			$scope.Success_Msg=true;
		}
		
	}).error(function(){
		alert("error")
	});
	}
});
	
sampleApp2.controller('sesionCtrl', function($scope,$rootScope,$window) {
	$scope.logname=$window.sessionStorage.getItem(name);
});


	


/*$(document).ready(function() {

    $('#example').DataTable();
} );*/


