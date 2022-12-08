<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New User Created</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	</head>
	<body>
		<section class="vh-100 gradient-custom">
			<div class="container py-5 h-100">
			  <div class="row justify-content-center align-items-center h-100">
			    <div class="col-12 col-lg-9 col-xl-7">
			      <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
			        <div class="card-body p-4 p-md-5">
			          	<h3 class="mb-4 pb-2 pb-md-0 mb-md-5">New User Created</h3>
			            <div class="row">
			              <div class="col-md-12 pb-2">
			
			                <div class="form-outline">
			                  First Name: <% String fname = request.getParameter("firstname"); out.print(fname); %>
			                </div>
			
			              </div>
			            </div>
			            
			            <div class="row">
			              <div class="col-md-12 pb-2">
			
			                <div class="form-outline">
			                  Last Name: <% String lname = request.getParameter("lastname"); out.print(lname); %>
			                </div>
			
			              </div>
			            </div>
			
			            <div class="pt-2">
			              <a class="btn btn-primary btn-lg" href="./login/login.jsp">Login</a>
			            </div>
			        </div>
			      </div>
			    </div>
			  </div>
			</div>
		</section>
	</body>
</html>