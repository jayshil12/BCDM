<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/login/login_controller.js"></script>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	</head>
	<body>
		<section class="vh-100 gradient-custom">
			<div class="container py-5 h-100">
			  <div class="row justify-content-center align-items-center h-100">
			    <div class="col-12 col-lg-9 col-xl-7">
			      <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
			        <div class="card-body p-4 p-md-5">
			          <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Login</h3>
			          <form method="get" action="${pageContext.request.contextPath}/LoginUser.do" id="login_form">
			          
			            <div class="row">
			              <div class="col-md-12 mb-4 pb-2">
			
			                <div class="form-outline">
			                  <input type="text" id="bronco_id" maxlength="100" name="bronco_id" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="bronco_id">Bronco Id</label>
			                </div>
			
			              </div>
			            </div>
			            
			            <div class="row">
			              <div class="col-md-12 mb-2 pb-2">
			
			                <div class="form-outline">
			                  <input type="password" id="password" maxlength="100" name="password" class="form-control form-control-lg" required/>
			                  <label class="form-label" for="password">Password</label>
			                  <%	
								String error_message = (String) request.getAttribute("error_message");
			                		  
								if (error_message == "Password incorrect") { 
									%>
								      <p class="link-danger">Password Incorrect, please try again</p>
								    <%
								} else if (error_message == "User does not exists") {
									%>
								      <p class="link-danger">User does not exists, please click below link to register</p>
								    <%
								}
							  %>
			                </div>
			
			              </div>
			            </div>
			
			            <div class="pt-2">
			              <input class="btn btn-primary btn-lg" type="button" value="Login" onClick="handleLoginSubmit()" />
			              <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? 
			              	<a href="${pageContext.request.contextPath}/registration/createuser.jsp" class="link-danger">Register</a>
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