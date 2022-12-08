package mypackage.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.service.ServiceRegistry;

import mypackage.controller.FoodItemInfoWrapper;
import mypackage.model.Category;
import mypackage.model.Fooditem;

/**
 * Servlet implementation class GetFoodItems
 */
@WebServlet(description = "This class gets the food item from database", urlPatterns = { "/GetFooditem.do", "/AddItem.do", "/Logout.do"})
public class GetFoodItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static List<FoodItemInfoWrapper> foodItemInfos;
	public static List<CartItemInfoWrapper> currentCartItems;
	public static Map<Integer, FoodItemInfoWrapper> foodItemInfoById;
	public static Map<Integer, CartItemInfoWrapper> currentCartItemById;
	public static OrderInfoWrapper order;
	
	//Class constants
	private static final Double TAX_RATE_CALIFORNIA = 0.065;
       
    /**
     * Constructor
     */
    public GetFoodItems() {
        super();
    }

	/**
	 * @description This method is getting called on page load to get menu items
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("User with Id: " + request.getSession(false).getAttribute(Constants.USER_ID) + " is logged in.");
		
		if (request.getServletPath() == "/Logout.do") {
			request.getSession(false).invalidate();
            request.getRequestDispatcher("./login/login.jsp").forward(request, response);	
        } else {
        	try {
    			setFoodItems(request, response);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
	
	/**
	 * @description This method is getting called when user add items to the cart
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer quantity = Integer.parseInt(request.getParameter(Constants.QUANTITY));
			Boolean Is_Executed = (Boolean) request.getSession(false).getAttribute(Constants.IS_EXECUTED);
			
			System.out.println("Is Executed: " + request.getSession(false).getAttribute(Constants.IS_EXECUTED));
			System.out.println("Show Error Message: " + request.getSession(false).getAttribute("show_error_message"));
			System.out.println("Item Id: " + request.getSession(false).getAttribute("item_id_error"));
			
			if (Is_Executed == null || !Is_Executed) {
				System.out.println("I am called 1");
				if ((quantity == null || quantity == 0)) { 
					System.out.println("I am called 2");
					request.getSession(false).setAttribute("item_id_error", request.getParameter(Constants.ITEM_ID));
					request.getSession(false).setAttribute("show_error_message", true);
					request.getSession(false).setAttribute(Constants.IS_EXECUTED, true);
					request.getRequestDispatcher("/dashboard/dashboard_user.jsp").forward(request, response);
				} else {
					System.out.println("I am called 3");
					request.getSession(false).setAttribute("show_error_message", false);
					request.getSession(false).setAttribute("item_id_error", "");
					setCartItems(request, response);
				}
				
				System.out.println("I am called 4");
			}else if (Is_Executed) {
				System.out.println("I am called 5");
				request.getSession(false).setAttribute(Constants.IS_EXECUTED, false);
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * @description This method get food items from the database to show it on menu
	 */
	public void setFoodItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		foodItemInfos = FoodDao.getFoodItemInfos(String.valueOf(request.getSession(false).getAttribute(Constants.USER_ID)));
		foodItemInfoById = new HashMap<Integer, FoodItemInfoWrapper>();
		
		for (FoodItemInfoWrapper foodItemInfo : foodItemInfos) {
			foodItemInfoById.put(foodItemInfo.foodId, foodItemInfo);	
		}
		
		request.getSession(false).setAttribute(Constants.FOOD_ITEM_INFO_BY_ID, foodItemInfoById);
	}
	
	/**
	 * @description This method helps show foodItems on the page
	 */
	public List<FoodItemInfoWrapper> getFoodItems() {
		return foodItemInfos;
	}
	
	/**
	 * @description This method builds data for the Order Summary section of UI
	 */
	@SuppressWarnings("unchecked")
	public void setCartItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer selectedItemId = Integer.parseInt(request.getParameter(Constants.ITEM_ID));
		Integer quantity = Integer.parseInt(request.getParameter(Constants.QUANTITY));
		foodItemInfoById = (Map<Integer, FoodItemInfoWrapper>) request.getSession(false).getAttribute(Constants.FOOD_ITEM_INFO_BY_ID);
		
		if (currentCartItemById == null) {
			currentCartItemById = new HashMap<Integer, CartItemInfoWrapper>();
		} else if (request.getSession(false).getAttribute(Constants.CURRENT_CART_ITEM_BY_ID) != null) {
			currentCartItemById = (Map<Integer, CartItemInfoWrapper>) request.getSession(false).getAttribute(Constants.CURRENT_CART_ITEM_BY_ID);
		}
		
		if (currentCartItemById != null && currentCartItemById.containsKey(selectedItemId)) {;
			CartItemInfoWrapper cartItem = currentCartItemById.get(selectedItemId);
			cartItem.quantity = cartItem.quantity + quantity;
			
			//making sure that $$ amount is rounded
			BigDecimal price = new BigDecimal(cartItem.price * cartItem.quantity).setScale(2, RoundingMode.HALF_UP);
			cartItem.total = price.doubleValue();
			
			//making sure that $$ amount is rounded
			BigDecimal tax = new BigDecimal(cartItem.price * cartItem.quantity * TAX_RATE_CALIFORNIA).setScale(2, RoundingMode.HALF_UP);
			cartItem.tax = tax.doubleValue(); 
			
			currentCartItemById.put(cartItem.foodId, cartItem);
		} else if(foodItemInfoById.containsKey(selectedItemId)) {
			FoodItemInfoWrapper foodItemInfo = foodItemInfoById.get(selectedItemId);
			
			CartItemInfoWrapper cartItem = new CartItemInfoWrapper();
			cartItem.foodName = foodItemInfo.foodName;
			cartItem.quantity = quantity;
			cartItem.price = foodItemInfo.price;
			
			//making sure that $$ amount is rounded
			BigDecimal price = new BigDecimal(foodItemInfo.price * quantity).setScale(2, RoundingMode.HALF_UP);
			cartItem.total = price.doubleValue(); 
			
			//making sure that $$ amount is rounded
			BigDecimal tax = new BigDecimal(foodItemInfo.price * quantity * TAX_RATE_CALIFORNIA).setScale(2, RoundingMode.HALF_UP);
			System.out.println(tax);
			cartItem.tax = tax.doubleValue(); 
			
			cartItem.foodId = foodItemInfo.foodId;
			cartItem.userId = (Integer) request.getSession(false).getAttribute(Constants.USER_ID);
			
			currentCartItemById.put(foodItemInfo.foodId, cartItem);
		}
		
		request.getSession(false).setAttribute(Constants.CURRENT_CART_ITEM_BY_ID, currentCartItemById);
		request.getSession(false).setAttribute(Constants.IS_EXECUTED, true);
		setOrderTotals(currentCartItemById);
		
    	request.getRequestDispatcher("/dashboard/dashboard_user.jsp").forward(request, response);
	}
	
	public void setOrderTotals(Map<Integer, CartItemInfoWrapper> currentCartItemById) {
		order = new OrderInfoWrapper();
		Double subtotal = 0.00;
		Double totaltax = 0.00;
		Double total = 0.00;
		
		for (CartItemInfoWrapper item : currentCartItemById.values()) {
			subtotal = subtotal + item.total;
			totaltax = totaltax + item.tax;
		}
		
		//making sure that $$ amount is rounded
		BigDecimal subtotal_final = new BigDecimal(subtotal).setScale(2, RoundingMode.HALF_UP);
		BigDecimal tax_final = new BigDecimal(totaltax).setScale(2, RoundingMode.HALF_UP);

		BigDecimal total_final = new BigDecimal(subtotal + totaltax).setScale(2, RoundingMode.HALF_UP);
		
		order.subtotal = subtotal_final.doubleValue();
		order.totaltax = tax_final.doubleValue();
		order.total = total_final.doubleValue();
	}
	
	public OrderInfoWrapper getOrderTotals() {
		return order;
	}
	
	/**
	 * @description This method gets data for the Order Summary section of UI
	 */
	public List<CartItemInfoWrapper> getCartItems() {
		currentCartItems = new ArrayList<CartItemInfoWrapper>();
		
		if (currentCartItemById != null) {
			for (Integer foodId : currentCartItemById.keySet()) {
				currentCartItems.add(currentCartItemById.get(foodId));
			}
			
			return currentCartItems;
		}
		
		return null;
	}
	
	public void logOut() {
		
	}
}
