<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:useBean id="getFoodItems" class="mypackage.controller.GetFoodItems"/>
		<jsp:useBean id="foodItemInfoWrapper" class="mypackage.controller.FoodItemInfoWrapper"/>
		<meta charset="UTF-8">
		<title>Order Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	</head>
	<body>
		<!-- This helps load food items on page load, calls doGet method of the Servlet-->
		<jsp:include page="/GetFooditem.do" flush="true"></jsp:include>
		<c:set var="showError" value="${show_error_message}" scope="request"/>
		<c:set var="itemId_error" value="${item_id_error}" scope="request"/>
		<c:set var="orderTotals" value="${getFoodItems.getOrderTotals()}" scope="request"/>
		
		<section class="vh-100 gradient-custom">
			<div class="card">
			  <div class="card-header">
			  	<div class="row">
			  		<div class="col-11">
			  			<h1>Welcome <% String fname = (String)request.getSession(false).getAttribute("firstname"); out.print(fname); %> , Please select menu item</h1>
			  		</div>
			  		
			  		<div class="col-1 mt-2">
			  			<form method="get" action="${pageContext.request.contextPath}/Logout.do" id="logout_button">
			  				<input class="btn btn-warning" type="submit" value="Logout"/>
			  			</form>
			  		</div>
			  	</div>
			    
			  </div>
			  
			  <div class="card-body">
			  	<div class="row">
				  	<div class="col-6">
				      <div class="row">
				    	<c:forEach items="${getFoodItems.getFoodItems()}" var="foodInfo">
				    		<c:set var="item_id" value='${foodInfo.getFoodId()}' />
							<div class="col-sm-12 mb-2" id="${foodInfo.getFoodId()}">
							   <div class="card bg-light">
							     <div class="card-body">
							       <h5 class="card-title foodName"><c:out value="${foodInfo.getFoodName()}"/></h5>
							       
							       <c:if test="${foodInfo.getDescription() != null}">  
									  <p class="card-text foodDescription"><c:out value="${foodInfo.getDescription()}"/></p> 
								   </c:if>  
							       
							       <p class="card-tex foodPrice">$ <c:out value="${foodInfo.getPrice()}"/></p>
								   
								   <form method="post" action="${pageContext.request.contextPath}/AddItem.do" id="add_button">
									    <div class="row">
										    <div class="col-2">
										    	<input type="number" id="qunatity" name="qunatity" class="form-control foodQty" placeholder="Quantity" min="0" value="0"/>
									    	</div>
									    	<div class="col-2">
										    	<input class="btn btn-primary" type="submit" value="Add"/>
									    	</div>
										</div>
									   
								   		<div class="invisible h-25 d-none">
								   			<input class="form-control form-control-lg input-sm" type="number" value="${foodInfo.getFoodId()}" name="item_id" id="item_id"/>
								   		</div>
								   		
								   		<c:if test="${requestScope.itemId_error == foodInfo.getFoodId() && requestScope.showError == true}">  
									  		<span class="link-danger" id="quantity_error" autofocus="autofocus">Please provide valid quantity</span> 
								   		</c:if> 
								   </form>
							
							     </div>
							   </div>
							</div>
				    	</c:forEach>
					  </div>
				    </div>
				 
				  	<div class="col-6">
			    	  <div class="card bg-light h-25">
					    <div class="card-body">
					       <h5 class="card-title border-bottom mb-4">Item Summary</h5>
					        <table class="table table-sm">
							  <thead>
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">Item Name</th>
							      <th scope="col">Qty</th>
							      <th scope="col">Price</th>
							      <th scope="col">Tax</th>
							      <th scope="col">Total</th>
							    </tr>
							  </thead>
							  
							  <tbody>
								  <c:if test="${not empty getFoodItems.getCartItems()}">
									  <c:forEach items="${getFoodItems.getCartItems()}" var="carItem" varStatus="loop">
									  	<tr>
									      <th scope="row">${loop.index + 1}</th>
									      <td>${carItem.getFoodName()}</td>
									      <td>${carItem.getQuantity()}</td>
									      <td>$ ${carItem.getPrice()}</td>
									      <td>$ ${carItem.getTax()}</td>
									      <td>$ ${carItem.getTotal()}</td>
									    </tr>
									  </c:forEach>
								  </c:if>
							  </tbody>
							</table>
					    </div>
				   	  </div>
				   	  
				   	  <c:if test="${not empty getFoodItems.getCartItems()}">
				   	  	
				   	  </c:if>
				   	  <div style="height: 1000px;" class="pr-2">
						<div class="card bg-light h-25 mt-4 w-50 float-end">
						    <div class="card-body">
						       <h5 class="card-title border-bottom mb-4">Cost Summary</h5>
						        <div class="row">
								    <div class="col-4">
								    	<p>Subtotal <span class="float-right">:</span> </p> 
								    	<p>Tax <span class="float-right">:</span> </p> 
								    	<div class="border-bottom mb-1"></div>
								    	<p>Total <span class="float-right">:</span> </p> 
							    	</div>
							    	<div class="col-4">
							    		<p> $ <c:out value="${orderTotals.getSubtotal()}"/> </p>
							    		<p> $ <c:out value="${orderTotals.getTax()}"/> </p>
								    	<div class="border-bottom mb-1"></div>
								    	<p> $ <c:out value="${orderTotals.getTotal()}"/> </p> 
							    	</div>
							    	<div class="col-4">
								    	<input class="btn btn-primary" type="submit" value="Checkout"/>
							    	</div>
								</div>
						    </div>
				   	    </div>
					  </div>
				   	  
			    	</div>
			    	
				</div>
			  </div>
			</div>
		</section>
	</body>
</html>