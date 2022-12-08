<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/registration/registration_controller.js"></script>
		
		<title>Registration</title>
		<!-- CSS only -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	</head>
	<body>
		<!-- This code shows a message on the registration page if user already exists -->
		<%	
			String error_message = (String) request.getAttribute("error_message");
			if (error_message != null) { 
				%>
			      <script>
			      	showErrorMessage('Your Account Already exists, Please click ok to go to Login Page');
			      </script>
			    <%
			}
		%>
		
		<section class="vh-100 gradient-custom">
			<div class="container py-5 h-100">
			  <div class="row justify-content-center align-items-center h-100">
			    <div class="col-12 col-lg-9 col-xl-7">
			      <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
			        <div class="card-body p-4 p-md-5">
			          <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
			          <form method="get" action="${pageContext.request.contextPath}/CreateUser.do" id="registration_form">
			
			            <div class="row">
			              <div class="col-md-6 mb-4">
			
			                <div class="form-outline">
			                  <input type="text" id="firstName" maxlength="40" name="firstname" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="firstName">First Name</label>
			                </div>
			
			              </div>
			              
			              <div class="col-md-6 mb-4">
			
			                <div class="form-outline">
			                  <input type="text" id="lastName" maxlength="40" name="lastname" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="lastName">Last Name</label>
			                </div>
			
			              </div>
			            </div>
			
			            <div class="row">
			              <div class="col-md-6 mb-4 d-flex align-items-center">
			
			                <div class="form-outline datepicker w-100">
			                  <input  type="number" id="broncoId" maxlength="10" name="bronco_id" class="form-control form-control-lg" required/>
			                  <label for="broncoId" class="form-label">Bronco Id</label>
			                </div>
			
			              </div>
			              
			              <div class="col-md-6 mb-4 pb-2">
			
			                <div class="form-outline">
			                  <input type="tel" id="phoneNumber" maxlength="10" name="phone" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="phoneNumber">Phone Number</label>
			                </div>
			
			              </div>
			            </div>
			            
			            <div class="row">
			              <div class="col-md-6 mb-4 d-flex align-items-center">
			
			                <div class="form-outline datepicker w-100">
			                  <input type="password" id="password-1" maxlength="10" name="password" class="form-control form-control-lg" required/>
			                  <label for="password-1" class="form-label">Password</label>
			                </div>
			
			              </div>
			              
			              <div class="col-md-6 mb-4 pb-2">
			
			                <div class="form-outline">
			                  <input type="password" id="password-2" maxlength="10" name="passwordConfirm" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="password-2">Re-Type Password</label>
			                </div>
			
			              </div>
			            </div>
			
			            <div class="row">
			              <div class="col-md-12 mb-4 pb-2">
			
			                <div class="form-outline">
			                  <input type="text" id="address" maxlength="100" name="address" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="address">Address</label>
			                </div>
			
			              </div>
			            </div>
			
			            <div class="row">
			              <div class="col-12 mb-4">
			
			                <select class="select form-control-lg" onchange="handleUserTypeSelection()" name="user_type" id="user_type" required>
			                  <option value="none">Choose option</option>
			                  <option value="student">Student</option>
			                  <option value="staff">Staff</option>
			                </select>
			                <label class="form-label select-label">User Type</label>
			
			              </div>
			            </div>
			            
			            <div class="d-none" id="staff_fields">
				            <%@ include file="staff-fields.html" %>
			            </div>
			            
			            <div class="d-none" id="student_fields">
				            <%@ include file="student-fields.html" %>
			            </div>
			
			            <div class="mt-2 pt-2">
			              <input class="btn btn-primary btn-lg" type="button" value="Register" onClick="handleSubmit()" />
			              <p class="small fw-bold mt-2 pt-1 mb-0">Already have an account? 
			              	<a href="${pageContext.request.contextPath}/login/login.jsp" class="link-danger">Login</a>
				          </p>
			            </div>
			
			          </form>
			        </div>
			      </div>
			    </div>
			  </div>
			</div>
		</section>
		<!-- JavaScript Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	</body>
</html>